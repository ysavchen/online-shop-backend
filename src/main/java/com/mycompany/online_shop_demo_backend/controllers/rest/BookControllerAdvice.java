package com.mycompany.online_shop_demo_backend.controllers.rest;

import com.mycompany.online_shop_demo_backend.exceptions.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BookControllerAdvice {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntityNotFoundException.class)
    ApiError entityNotFoundException(EntityNotFoundException ex) {
        return new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}
