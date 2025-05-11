package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IntendantNotFoundException extends RuntimeException {
    public IntendantNotFoundException(Long trainerId, Long classId) {
        super("Intendant not found with trainer ID : " + trainerId + " , Class ID : " + classId);
    }


}
