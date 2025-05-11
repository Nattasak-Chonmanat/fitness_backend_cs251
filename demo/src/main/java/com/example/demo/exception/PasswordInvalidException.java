package com.example.demo.exception;

public class PasswordInvalidException extends RuntimeException {
    public PasswordInvalidException() {
        super(ErrorMessages.PASSWORD_INVALID.getMessage());
    }
}
