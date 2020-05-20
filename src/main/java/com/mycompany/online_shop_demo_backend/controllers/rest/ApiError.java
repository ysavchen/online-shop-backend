package com.mycompany.online_shop_demo_backend.controllers.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ApiError {

    private HttpStatus status;
    private String message;

}
