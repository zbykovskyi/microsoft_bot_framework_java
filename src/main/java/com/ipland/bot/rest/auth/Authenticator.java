package com.ipland.bot.rest.auth;

import com.ipland.azure.bot.model.Activity;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;

/**
 * Created by Ivan Zbykovskyi on 2/15/18.
 */
@Slf4j
@Component
public class Authenticator {

    @Inject
    private OAuthTokenStore oauthTokenStore;

    public void doAuthenticate(String authorization, Activity activity) throws AuthenticationException {
        String channel = activity != null ? (StringUtils.isNotBlank(activity.getChannelId()) ? activity.getChannelId() : "unknown") : "unknown";
        log.trace("Authenticating request from {} channel. Authorization token = {}", channel, authorization);
        if (StringUtils.isNotBlank(authorization)) {
            try {
                String accessToken = authorization.substring(6, authorization.length());
                SignedJWT jwt = SignedJWT.parse(accessToken);
                RSAPublicKey publicKey = oauthTokenStore.getKeys().get(channel + "->" + jwt.getHeader().getKeyID());
                if (publicKey == null) {
                    log.error("Unable to authenticate incoming request for Activity ({}). Authorization token = {},\nError -> Signing key for JWT token not found (kid = {}, channel = {}).\nKeymap = {}", activity, authorization, jwt.getHeader().getKeyID(), channel, oauthTokenStore.getKeys() != null ? oauthTokenStore.getKeys().keySet() : "null");
                    throw new AuthenticationException("Signing key for JWT token not found");
                } else if (!jwt.verify(new RSASSAVerifier(publicKey))) {
                    log.error("Unable to authenticate incoming request for Activity ({}). Authorization token = {}, Error -> {}", activity, authorization, "Signature for JWT token is invalid");
                    throw new AuthenticationException("Signature for JWT token is invalid");
                }
            } catch (ParseException | JOSEException | RuntimeException e) {
                log.error("Unable to authenticate incoming request for Activity ({}). Authorization token = {}, Error -> {}", activity, authorization, ExceptionUtils.getStackTrace(e));
            }
        } else {
            log.error("Unable to authenticate incoming request for Activity ({}). Authorization token = {}, Error -> {}", activity, authorization, "Authorization header is blank");
            throw new AuthenticationException("Authorization header is blank");
        }
        log.trace("Authenticated request from {} channel. Authorization token = {}", channel, authorization);
    }
}
