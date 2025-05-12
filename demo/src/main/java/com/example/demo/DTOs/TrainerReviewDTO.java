package com.example.demo.DTOs;

import com.example.demo.model.TRating;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Schema(description = "TrainerReviewDTO")
public class TrainerReviewDTO {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Great session!")
    private String review;

    @Schema(hidden = true)
    private LocalDate reviewDate;

    @DecimalMin(value = "0.0", message = "Rating must be between 0.0 to 5.0.")
    @DecimalMax(value = "5.0", message = "Rating must be between 0.0 to 5.0.")
    @NotNull(message = "Rate is required.")
    @Schema(example = "4.5")
    private Float rate;

    @NotNull(message = "Trainer id is required.")
    @Schema(example = "101")
    private Long trainerId;
    @NotNull(message = "Member id is required.")
    @Schema(example = "101")
    private Long memberId;

    public TrainerReviewDTO(TRating tRating) {
        this.id = tRating.getId();
        this.memberId = tRating.getMember().getId();
        this.trainerId = tRating.getTrainer().getId();
        this.rate = tRating.getRate();
        this.reviewDate = tRating.getReviewDate();
        this.review = tRating.getReview();
    }


    public TrainerReviewDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Long trainerId) {
        this.trainerId = trainerId;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
