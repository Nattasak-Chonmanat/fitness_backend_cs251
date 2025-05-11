package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MemberInvalidException extends RuntimeException {
    public MemberInvalidException(String field) {
        super(field + ErrorMessages.REQUEST_INVALID.getMessage());
    }
}
