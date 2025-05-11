package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TrainerInvalidException extends RuntimeException {
    public TrainerInvalidException(String message) {
        super(message);
    }
}
