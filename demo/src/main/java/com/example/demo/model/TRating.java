package com.example.demo.model;

import com.example.demo.DTOs.TrainerReviewDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;


@Entity
@Table(name = "t_rating" ,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"member_id", "trainer_id"})
        })
public class TRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rate")
    @DecimalMin(value = "0.0", message = "Rating must be between 0.0 to 5.0.")
    @DecimalMax(value = "5.0", message = "Rating must be between 0.0 to 5.0.")
    @NotNull
    private Float rate;

    @Column(name = "reviewDate" , nullable = false)
    private LocalDate reviewDate = LocalDate.now();

    @Column(name = "review")
    @NotBlank(message = "Review must not be empty.")
    private String review;

    @ManyToOne
    @NotNull(message = "Trainer id is required.")
    @JoinColumn(name = "trainer_id", nullable = false)
    private Trainer trainerId;

    @ManyToOne
    @NotNull(message = "Member id is required.")
    @JoinColumn(name = "member_id", nullable = false)
    private Member memberId;


    public @NotNull Trainer getTrainer() {
        return trainerId;
    }

    public void setTrainer(@NotNull Trainer trainerId) {
        this.trainerId = trainerId;
    }

    public @NotNull Member getMember() {
        return memberId;
    }

    public void setMember(@NotNull Member memberId) {
        this.memberId = memberId;
    }

    public TRating() {
    }

    public TRating(Long id, String review, LocalDate reviewDate, Float rate) {
        this.id = id;
        this.review = review;
        this.reviewDate = reviewDate;
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Review must not be blank.") String getReview() {
        return review;
    }

    public void setReview(@NotBlank(message = "Review must not be blank.") String review) {
        this.review = review;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }

    public @DecimalMin(value = "0.0", message = "Rating must be between 0.0 to 5.0.") @DecimalMax(value = "5.0", message = "Rating must be between 0.0 to 5.0.") @NotNull Float getRate() {
        return rate;
    }

    public void setRate(@DecimalMin(value = "0.0", message = "Rating must be between 0.0 to 5.0.") @DecimalMax(value = "5.0", message = "Rating must be between 0.0 to 5.0.") @NotNull Float rate) {
        this.rate = rate;
    }
}
