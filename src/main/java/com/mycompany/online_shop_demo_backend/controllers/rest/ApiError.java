package com.mycompany.online_shop_demo_backend.controllers.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter
@AllArgsConstructor
public class ApiError {

    private HttpStatus status;
    private String message;

}
