package com.example.demo.DTOs;

import com.example.demo.model.CReview;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public class CReviewDTO {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "2025-05-11")
    private LocalDate reviewDate;

    @Schema(example = "Great session!")
    private String review;

    @Schema(example = "101")
    private Long memberId;

    @Schema(example = "102")
    private Long classId;

    @Schema(example = "5.0")
    private Float rate;


    public CReviewDTO(CReview cReview) {
        this.id = cReview.getId();
        this.reviewDate = cReview.getReviewDate();
        this.review = cReview.getReview();
        this.memberId = cReview.getMember().getId();
        this.classId = cReview.getaClass().getId();
        this.rate = cReview.getRate();
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
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
