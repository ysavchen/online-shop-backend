package com.mycompany.online_shop_backend.controllers.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ResponseError implements Serializable {

    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;

    public ResponseError(HttpStatus status, String message) {
        this.status = status;
        this.timestamp = LocalDateTime.now();
        this.message = message;
    }
}
