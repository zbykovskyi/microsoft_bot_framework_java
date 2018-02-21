package com.ipland.azure.bot.invoker.auth;

import com.ipland.bot.rest.auth.OAuthTokenStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import javax.inject.Inject;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-01-31T17:19:19.373+02:00")
@Slf4j
@Component
public class OAuth implements Authentication {

    @Inject
    private OAuthTokenStore store;

    public void setAccessToken(String accessToken) {

    }

    @Override
    public void applyToParams(MultiValueMap<String, String> queryParams, HttpHeaders headerParams) {
        String accessToken = store.getToken();

        if (accessToken != null) {
            headerParams.add(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
        } else {
            log.error("Access token not found");
        }
    }

}