package com.mycompany.online_shop_backend.exceptions;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }

}
