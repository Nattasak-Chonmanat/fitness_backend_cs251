package com.example.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Schema(name = "Update promotion request.")
public class UpdatePromotionRequest {

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

    public @FutureOrPresent(message = "End date must be today or in the future") LocalDate getEndDate() {
        return endDate;
    }

    public @FutureOrPresent(message = "Start date must be today or in the future") LocalDate getStartDate() {
        return startDate;
    }

    public @NotBlank(message = "Discount type is required. (Fixed Amount or Percentage)") String getDiscountType() {
        return discountType;
    }

    public @PositiveOrZero(message = "Discount type must not be negative.") Float getDiscountValue() {
        return discountValue;
    }
}
