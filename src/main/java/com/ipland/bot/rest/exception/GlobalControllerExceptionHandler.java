package com.ipland.bot.rest.exception;

import com.ipland.bot.rest.auth.AuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * Created by Ivan Zbykovskyi on 2/21/18.
 */
@ControllerAdvice
@Slf4j
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(value = AuthenticationException.class)
    public HttpEntity<?> handleUserNotFound(AuthenticationException e) {
        return ResponseEntity.status(UNAUTHORIZED).header("msg", e.getLocalizedMessage()).build();
    }
}