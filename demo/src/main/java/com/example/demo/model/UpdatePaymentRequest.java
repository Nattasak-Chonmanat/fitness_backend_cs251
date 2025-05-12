package com.example.demo.model;


import jakarta.validation.constraints.NotBlank;

public class UpdatePaymentRequest {

    @NotBlank(message = "Payment status is required.")
    private String paymentStatus;

    public @NotBlank(message = "Payment status is required.") String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(@NotBlank(message = "Payment status is required.") String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
