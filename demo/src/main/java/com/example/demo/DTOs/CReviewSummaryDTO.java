package com.example.demo.DTOs;

import com.example.demo.model.CReview;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public class CReviewSummaryDTO {
    private Long id;
    private LocalDate reviewDate;
    private String review;
    private String userName;
    private Float rate;
    private String className;


    public CReviewSummaryDTO(CReview cReview, String userName, String className) {
        this.id = cReview.getId();;
        this.rate = cReview.getRate();
        this.userName = userName;
        this.review = cReview.getReview();
        this.reviewDate = cReview.getReviewDate();
        this.className = className;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public CReviewSummaryDTO() {
    }


    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }


    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
