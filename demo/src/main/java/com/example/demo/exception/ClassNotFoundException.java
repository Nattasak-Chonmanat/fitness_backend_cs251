package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClassNotFoundException extends RuntimeException {
    private Long classId;

    public ClassNotFoundException(Long classId) {
        super(ErrorMessages.CLASS_NOT_FOUND.getMessage() + classId);
    }


}
