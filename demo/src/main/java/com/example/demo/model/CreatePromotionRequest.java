package com.example.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Schema(description = "ข้อมูลที่ใช้สำหรับสร้างโปรโมชั่นใหม่")
public class CreatePromotionRequest {

    @NotBlank(message = "Promotion code is required.")
    @Schema(examples = "SAVE25", description = "Promotion's code")
    private String code;

    @PositiveOrZero(message = "Discount type must not be negative.")
    @Schema(examples = "25", description = "Discount value")
    private Float discountValue;

    @NotBlank(message = "Discount type is required. (Fixed Amount or Percentage)")
    @Schema(examples = "Percentage", description = "Discount type")
    private String discountType;


    @FutureOrPresent(message = "Start date must be today or in the future")
    @Schema(examples = "2025-10-20", description = "Promotion's start date")
    private LocalDate startDate;

    @Schema(examples = "2025-11-20",  description = "Promotion's end date")
    @FutureOrPresent(message = "End date must be today or in the future")
    private LocalDate endDate;


    public @NotBlank(message = "Promotion code is required.") String getCode() {
        return code;
    }

    public void setCode(@NotBlank(message = "Promotion code is required.") String code) {
        this.code = code;
    }

    public @FutureOrPresent(message = "End date must be today or in the future") LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(@FutureOrPresent(message = "End date must be today or in the future") LocalDate endDate) {
        this.endDate = endDate;
    }

    public @NotBlank(message = "Discount type is required. (Fixed Amount or Percentage)") String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(@NotBlank(message = "Discount type is required. (Fixed Amount or Percentage)") String discountType) {
        this.discountType = discountType;
    }

    public @FutureOrPresent(message = "Start date must be today or in the future") LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(@FutureOrPresent(message = "Start date must be today or in the future") LocalDate startDate) {
        this.startDate = startDate;
    }

    public @PositiveOrZero(message = "Discount type must not be negative.") Float getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(@PositiveOrZero(message = "Discount type must not be negative.") Float discountValue) {
        this.discountValue = discountValue;
    }
}
