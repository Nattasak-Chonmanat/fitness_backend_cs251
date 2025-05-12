package com.example.demo.exception;

public class PromotionNotFoundException extends RuntimeException {
    public PromotionNotFoundException(String code) {
        super("Promotion not found with " + code);
    }
}
