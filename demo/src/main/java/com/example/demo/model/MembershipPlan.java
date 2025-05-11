package com.example.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "membershipPlan")
public class MembershipPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(hidden = true)
    private Long id;

    @Column(name = "price", nullable = false)
    @PositiveOrZero(message = "Price can not be negative.")
    @Schema(description = "Price for this plan.", example = "500")
    private Float price;

    @Column(name = "duration", nullable = false)
    @PositiveOrZero(message = "Duration can not be negative.")
    @NotNull(message = "Duration must not be null")
    @Schema(description = "Duration for this plan", example = "30")
    private Integer duration;

    @Column(name = "description", nullable = false)
    @Size(min = 20, message = "Description must be at least 20 characters long.")
    @NotBlank(message = "Description must not empty.")
    @Schema(description = "Description for this plan", example = "This class is about Cardio and some weight training.")
    private String description;

    @Column(name = "planName")
    @NotBlank(message = "Plan name is required.")
    @Schema(description = "Name of membership plan", example = "Gold")
    private String planName;

    public String getPlanName() {
        return planName;
    }

    public void setPlanName (String planName) {
        this.planName = planName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
