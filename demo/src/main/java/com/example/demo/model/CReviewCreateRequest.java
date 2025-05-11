package com.example.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class CReviewCreateRequest {

    @DecimalMin(value = "0.0", message = "Rating must be between 0.0 to 5.0")
    @DecimalMax(value = "5.0", message = "Rating must be between 0.0 to 5.0")
    @NotNull(message = "Rating is required")
    @Schema(description = "Review' rate.", example = "5.0")
    private Float rate;

    @Schema(description = "New review.", example = "Great session!")
    @NotBlank(message = "Review is required.")
    private String review;

    @Schema(description = "Member' id.", example = "101")
    @NotNull(message = "Member id is required.")
    private Long memberId;

    @Schema(description = "Class' id.", example = "102")
    @NotNull(message = "Class id is required.")
    private Long classId;

    public CReviewCreateRequest() {
    }


    public CReviewCreateRequest(Float rate, Long classId, Long memberId, String review) {
        this.rate = rate;
        this.classId = classId;
        this.memberId = memberId;
        this.review = review;
    }


    public @NotNull(message = "Class id is required.") Long getClassId() {
        return classId;
    }

    public void setClassId(@NotNull(message = "Class id is required.") Long classId) {
        this.classId = classId;
    }

    public @DecimalMin(value = "0.0", message = "Rating must be between 0.0 to 5.0") @DecimalMax(value = "5.0", message = "Rating must be between 0.0 to 5.0") @NotNull(message = "Rating is required") Float getRate() {
        return rate;
    }

    public void setRate(@DecimalMin(value = "0.0", message = "Rating must be between 0.0 to 5.0") @DecimalMax(value = "5.0", message = "Rating must be between 0.0 to 5.0") @NotNull(message = "Rating is required") Float rate) {
        this.rate = rate;
    }



    public @NotNull(message = "Member id is required.") Long getMemberId() {
        return memberId;
    }

    public void setMemberId(@NotNull(message = "Member id is required.") Long memberId) {
        this.memberId = memberId;
    }

    public @NotBlank(message = "Review is required.") String getReview() {
        return review;
    }

    public void setReview(@NotBlank(message = "Review is required.") String review) {
        this.review = review;
    }
}
