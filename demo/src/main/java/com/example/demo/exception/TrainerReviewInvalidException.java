package com.example.demo.exception;

public class TrainerReviewInvalidException extends RuntimeException {
    public TrainerReviewInvalidException(String field) {
        super(field + ErrorMessages.REQUEST_INVALID);
    }
}
