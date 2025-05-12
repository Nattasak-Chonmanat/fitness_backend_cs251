package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class CreatePromotionRequest {

    private String code;

    @PositiveOrZero(message = "Discount type must not be negative.")
    private Float discountValue;
    
    private DiscountType discountType;

    @FutureOrPresent(message = "Start date must be today or in the future")
    private LocalDate startDate;

    @FutureOrPresent(message = "End date must be today or in the future")
    private LocalDate endDate;


}
