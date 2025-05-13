package com.example.demo.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "CReview")
public class CReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(hidden = true)
    private Long id;

    @Column(name = "rate", nullable = false)
    @DecimalMin(value = "0.0", message = "Rating must be between 0.0 to 5.0")
    @DecimalMax(value = "5.0", message = "Rating must be between 0.0 to 5.0")
    @NotNull(message = "Rating is required")
    @Schema(description = "Review' rate.", example = "5.0")
    private Float rate;

    @Column(name = "reviewDate")
    @Schema(hidden = true)
    private LocalDate reviewDate = LocalDate.now();

    @Column(name = "review")
    @Schema(description = "New review.", example = "Great session!")
    private String review = null;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id", nullable = false)
    private Class aClass;

    public Long getId() {
        return id;
    }

    public CReview() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
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

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }


}
