package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PaymentNotFoundException extends RuntimeException {
    public PaymentNotFoundException(Long id) {
        super(ErrorMessages.PAYMENT_NOT_FOUND.getMessage() + id);
    }
}
