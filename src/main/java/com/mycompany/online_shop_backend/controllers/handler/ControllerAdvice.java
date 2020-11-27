package com.mycompany.online_shop_backend.controllers.handler;

import com.mycompany.online_shop_backend.exceptions.EntityNotFoundException;
import com.mycompany.online_shop_backend.exceptions.NotAuthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    ResponseError entityNotFoundException(EntityNotFoundException ex) {
        return new ResponseError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({NotAuthorizedException.class, AuthenticationException.class})
    ResponseError notAuthorizedException(RuntimeException ex) {
        return new ResponseError(HttpStatus.UNAUTHORIZED, ex.getLocalizedMessage());
    }
}
