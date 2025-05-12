package com.example.demo.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

@Entity
@Table
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long promotionId;

    @NotBlank(message = "Code is required.")
    @Column(name = "code", nullable = false, unique = true)
    @Schema(description = "Code ของโปรโมชัน", examples = "SAVE25")
    private String code;

    @Column(name = "discountType", nullable = false)
    private String discountType;

    @Positive(message = "Discount values must be positive.")
    @Column(name = "discountValue", nullable = false)
    private Float discountValue;

    @Column(name = "startDate", nullable = false)
    @NotNull(message = "Start date is required.")
    private LocalDate startDate;

    @Column(name = "endDate", nullable = false)
    @NotNull(message = "End date is required.")
    private LocalDate endDate;


    public Long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }


    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public @Positive(message = "Discount values must be positive.") Float getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(@Positive(message = "Discount values must be positive.") Float discountValue) {
        this.discountValue = discountValue;
    }

    public @NotBlank(message = "Code is required.") String getCode() {
        return code;
    }

    public void setCode(@NotBlank(message = "Code is required.") String code) {
        this.code = code;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }
}
