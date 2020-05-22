package com.mycompany.online_shop_demo_backend.exceptions;

import org.springframework.security.core.AuthenticationException;

public class NotAuthorizedException extends AuthenticationException {

    public NotAuthorizedException(String message) {
        super(message);
    }
}
