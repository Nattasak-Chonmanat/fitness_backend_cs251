package com.example.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class UpdateClassReviewRequest {
    @NotNull
    @DecimalMin(value = "0.0", message = "Rating must be between 0.0 to 5.0")
    @DecimalMax(value = "5.0", message = "Rating must be between 0.0 to 5.0")
    @Schema(example = "4.5", description = "New rate.")
    private Float rate;

    @Schema(hidden = true)
    private LocalDate reviewDate = LocalDate.now();

    @Schema(example = "Great session!", description = "New review.")
    private String review;


    public @NotNull @DecimalMin(value = "0.0",
            message = "Rating must be between 0.0 to 5.0")
           @DecimalMax(value = "5.0", message = "Rating must be between 0.0 to 5.0") Float getRate() {
        return rate;
    }

    public void setRate(@NotNull @DecimalMin(value = "0.0",
            message = "Rating must be between 0.0 to 5.0")
                        @DecimalMax(value = "5.0", message = "Rating must be between 0.0 to 5.0") Float rate) {
        this.rate = rate;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }
}
