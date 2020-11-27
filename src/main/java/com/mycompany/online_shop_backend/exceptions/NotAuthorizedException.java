package com.mycompany.online_shop_backend.exceptions;

public class NotAuthorizedException extends RuntimeException {

    public NotAuthorizedException(String message) {
        super(message);
    }
}
