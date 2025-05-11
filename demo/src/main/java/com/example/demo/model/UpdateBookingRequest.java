package com.example.demo.model;

import jakarta.validation.constraints.NotNull;

public class UpdateBookingRequest {

    @NotNull(message = "Status is required.")
    private String status;

    public UpdateBookingRequest(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
