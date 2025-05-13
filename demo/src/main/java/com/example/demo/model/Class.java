package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "class")
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(hidden = true)
    private Long id;

    @Column(name = "className", nullable = false)
    @NotBlank(message = "Class name is required.")
    @Schema(description = "Class name.", example = "Yoga 101")
    private String className;

    @Column(name = "schedule")
    @NotBlank(message = "Schedule is required.")
    @Schema(description = "Class's schedule.", example = "Mon/Wed 6 PM")
    private String schedule;

    @Column(name = "capacity")
    @PositiveOrZero(message = "Capacity must be not be negative.")
    @NotNull(message = "Capacity is required.")
    @Schema(description = "Class's capacity.", example = "20")
    private Integer capacity;

    @Column(name = "rating")
    @DecimalMin(value = "0.0", inclusive = true, message = "Rating must be at least 0.0")
    @DecimalMax(value = "5.0", inclusive = true, message = "Rating must not over 5.0")
    @Schema(description = "Class's average rating.", example = "4.2 (ถ้าใช้ method post ไม่ต้องใส่ rating มา)")
    @JsonIgnore
    private Float rating;

    @OneToMany(mappedBy = "aClass", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(hidden = true)
    private List<ClassBooking> classBookingList = new ArrayList<>();

    @OneToMany(mappedBy = "aClass", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(hidden = true)
    private List<CReview> reviews= new ArrayList<>();

    @ManyToMany(mappedBy = "classes", cascade = CascadeType.MERGE)
    @Schema(hidden = true)
    private List<Trainer> trainers = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ClassBooking> getClassBookingList() {
        return classBookingList;
    }

    public void setClassBookingList(List<ClassBooking> classBookingList) {
        this.classBookingList = classBookingList;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public List<Trainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(List<Trainer> trainers) {
        this.trainers = trainers;
    }

    public List<CReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<CReview> reviews) {
        this.reviews = reviews;
    }
}
