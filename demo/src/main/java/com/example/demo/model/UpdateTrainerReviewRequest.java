package com.example.demo.model;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateTrainerReviewRequest {

    @NotBlank(message = "New review is required.")
    private String review;

    @DecimalMin(message = "Rating must be between 0.0 to 5.0", value = "0.0")
    @DecimalMax(message = "Rating must be between 0.0 to 5.0", value = "5.0")
    @NotNull(message = "New rate is required.")
    private Float rate;

    public UpdateTrainerReviewRequest() {
    }

    public UpdateTrainerReviewRequest(Float rate, String review) {
        this.rate = rate;
        this.review = review;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public @DecimalMin(message = "Rating must be between 0.0 to 5.0", value = "0.0") @DecimalMax(message = "Rating must be between 0.0 to 5.0", value = "5.0") Float getRate() {
        return rate;
    }

    public void setRate(@DecimalMin(message = "Rating must be between 0.0 to 5.0", value = "0.0") @DecimalMax(message = "Rating must be between 0.0 to 5.0", value = "5.0") Float rate) {
        this.rate = rate;
    }
}
