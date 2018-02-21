package com.ipland.bot.rest.auth;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ivan Zbykovskyi on 2/14/18.
 */
@Repository
@Data
@Slf4j
public class OAuthTokenStore {
    private String token;
    private Map<String, RSAPublicKey> keys = new HashMap<>();
}
