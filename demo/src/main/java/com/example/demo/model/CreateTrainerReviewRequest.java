package com.example.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CreateTrainerReviewRequest {
    @Schema(example = "Great session!")
    private String review;
    @DecimalMin(value = "0.0", message = "Rating must be between 0.0 to 5.0.")
    @DecimalMax(value = "5.0", message = "Rating must be between 0.0 to 5.0.")
    @NotNull
    @Schema(example = "4.5")
    private Float rate;

    @NotNull(message = "Trainer id is required.")
    @Schema(example = "101")
    private Long trainerId;
    @NotNull(message = "Member id is required.")
    @Schema(example = "101")
    private Long memberId;

    public CreateTrainerReviewRequest() {
    }

    public CreateTrainerReviewRequest(String review, Float rate, Long trainerId, Long memberId) {
        this.review = review;
        this.rate = rate;
        this.trainerId = trainerId;
        this.memberId = memberId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public @NotNull(message = "Member id is required.") Long getMemberId() {
        return memberId;
    }

    public void setMemberId(@NotNull(message = "Member id is required.") Long memberId) {
        this.memberId = memberId;
    }

    public @NotNull(message = "Trainer id is required.") Long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(@NotNull(message = "Trainer id is required.") Long trainerId) {
        this.trainerId = trainerId;
    }





    public @DecimalMin(value = "0.0", message = "Rating must be between 0.0 to 5.0.") @DecimalMax(value = "5.0", message = "Rating must be between 0.0 to 5.0.") @NotNull Float getRate() {
        return rate;
    }

    public void setRate(@DecimalMin(value = "0.0", message = "Rating must be between 0.0 to 5.0.") @DecimalMax(value = "5.0", message = "Rating must be between 0.0 to 5.0.") @NotNull Float rate) {
        this.rate = rate;
    }
}
