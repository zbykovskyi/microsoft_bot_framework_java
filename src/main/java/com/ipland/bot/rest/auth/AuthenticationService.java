package com.ipland.bot.rest.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

/**
 * Created by Ivan Zbykovskyi on 2/15/18.
 */
@Slf4j
@Data
@Service
public class AuthenticationService {

    @Inject
    private OAuthTokenStore oauthTokenStore;

    @Inject
    private RestTemplate restTemplate;

    @Value("${azure.botframework.client.id}")
    private String clientId;

    @Value("${azure.botframework.client.secret}")
    private String clientSecret;

    @Async("threadPoolAppReadyAsyncTaskExecutor")
    public void handleApplicationReadyEventRefreshAccessToken() {
        while (true) {
            log.info("Refreshing botframework access token");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            String rbody = "grant_type=client_credentials&client_id=" + clientId + "&client_secret=" + clientSecret + "&scope=https%3A%2F%2Fapi.botframework.com%2F.default";
            HttpEntity<String> entityReq = new HttpEntity(rbody, headers);
            Map response = restTemplate.postForObject("https://login.microsoftonline.com/botframework.com/oauth2/v2.0/token", entityReq, Map.class);

            oauthTokenStore.setToken((String) response.get("access_token"));
            log.info("Refreshed botframework access token");
            try {
                Integer expiresIn = (Integer) response.get("expires_in") / 2;
                log.info("Sleeping to the next access token refresh for {} seconds", expiresIn);
                TimeUnit.SECONDS.sleep(expiresIn);
            } catch (InterruptedException e) {
                log.error("Token refresh service interrupted. Error --> {}", ExceptionUtils.getRootCauseMessage(e));
                continue;
            }
        }
    }

    @Async("threadPoolAppReadyAsyncTaskExecutor")
    public void handleApplicationReadyEventRefreshListSigningKeys() {
        while (true) {
            /*
              The OpenID metadata document specifies the location of a second document that lists the Bot Connector
              service's valid signing keys.
              To get the OpenID metadata document, issue this request via HTTPS:
              GET https://login.botframework.com/v1/.well-known/openidconfiguration
            */
            Map<String, RSAPublicKey> rsaSigningKeys = new HashMap<>();

            log.info("Getting openid configuration");
            Map response = restTemplate.getForObject("https://login.botframework.com/v1/.well-known/openidconfiguration", Map.class);
            String jwks_uri = (String) response.get("jwks_uri");
            String discoveryKeys_uri = "https://login.microsoftonline.com/common/discovery/keys";
            log.info("jwks_uri = {}", jwks_uri);
            if (StringUtils.isBlank(jwks_uri)) {
                log.error("jwks_uri parameter not returned from GET https://login.botframework.com/v1/.well-known/openidconfiguration");
                throw new IllegalStateException("jwks_uri parameter not returned from GET https://login.botframework.com/v1/.well-known/openidconfiguration");
            } else {
                if (!discoveryKeys_uri.equalsIgnoreCase(jwks_uri)) {
                    log.info("Getting the emulator`s list of valid signing keys");
                    log.info("jwks_discoveryKeys_uri = {}", discoveryKeys_uri); //emulator keys
                    Map keyset = restTemplate.getForObject(discoveryKeys_uri, Map.class);
                    if (keyset == null || keyset.size() == 0 || keyset.get("keys") == null) {
                        log.warn("Emulator`s keyset not returned from {}", discoveryKeys_uri);
                    } else {
                        List<Map<String, String>> keys = (List<Map<String, String>>) keyset.get("keys");
                        rsaSigningKeys.putAll(keys.stream().map(m -> Endorsement.convert(Collections.singletonList("emulator"), m)).flatMap(e -> e.stream()).collect(toMap(e -> e.getChannel() + "->" + e.getKey(), e -> e.getPublicKey())));
                    }
                }
                log.info("Getting the list of valid signing keys from jwks_uri = {}", jwks_uri);
                Map keyset = restTemplate.getForObject(jwks_uri, Map.class);
                if (keyset == null || keyset.size() == 0 || keyset.get("keys") == null) {
                    log.error("keyset not returned from {}", jwks_uri);
                    throw new IllegalStateException("BotFramework`s keyset not returned from " + jwks_uri);
                } else {
                    List<Map> keys = (List) keyset.get("keys");
                    Stream<Key> keyStream = keys.stream().map(k -> new Key((List<String>) k.get("endorsements"), k));
                    Stream<List<Endorsement>> endorsements = keyStream.map(key -> Endorsement.convert(key.getEndorsements(), key.getData()));
                    Map<String, RSAPublicKey> keyMap = endorsements.flatMap(e -> e.stream()).collect(toMap(e -> e.getChannel() + "->" + e.getKey(), e -> e.getPublicKey()));
                    rsaSigningKeys.putAll(keyMap);
                    log.info("Refreshed list of valid signing keys. Size = {}", keyMap.size());
                }
                oauthTokenStore.setKeys(rsaSigningKeys);
            }
            try {
                log.info("Sleeping to the next refresh list signing keys for {} days", 5);
                TimeUnit.DAYS.sleep(5);
            } catch (InterruptedException e) {
                log.error("Sign keys refresh service interrupted. Error --> {}", ExceptionUtils.getRootCauseMessage(e));
                continue;
            }
        }
    }

    @Getter
    @AllArgsConstructor
    static class Key {
        List<String> endorsements;
        Map<String, String> data;
    }

    @Getter
    @AllArgsConstructor
    @Slf4j
    static class Endorsement {
        String channel;
        String key;
        RSAPublicKey publicKey;

        public static List<Endorsement> convert(List<String> endorsements, Map<String, String> ext) {
            return endorsements.stream().map(e -> {
                try {
                    return new Endorsement(e, ext.get("kid"),
                            (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(
                                    new BigInteger(1, Base64.getUrlDecoder().decode(ext.get("n"))),
                                    new BigInteger(1, Base64.getUrlDecoder().decode(ext.get("e"))))));
                } catch (NoSuchAlgorithmException | InvalidKeySpecException | RuntimeException ex) {
                    log.warn("Unable to create RSA public key (kid = {}). Error -> {}", ext.get("kid"), ExceptionUtils.getStackTrace(ex));
                    return null;
                }
            }).filter(e -> e != null).collect(Collectors.toList());
        }
    }
}