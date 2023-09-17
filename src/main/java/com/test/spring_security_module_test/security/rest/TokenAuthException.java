package com.test.spring_security_module_test.security.rest;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

/**
 * @author Kiselev Oleg
 */
@SuppressWarnings("PMD.MutableException")
@Getter
public class TokenAuthException extends AuthenticationException {
    private HttpStatus httpStatus;

    public TokenAuthException(final String msg) {
        super(msg);
    }

    public TokenAuthException(final String msg, final HttpStatus httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }
}
