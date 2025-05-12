package com.example.demo.model;

public enum DiscountType {
    FIXED_AMOUNT("Fixed Amount"),
    PERCENTAGE("Percentage");

    private final String message;

    DiscountType(String message) {
        this.message = message;
    }

    public String getMessage() {return this.message;}
}