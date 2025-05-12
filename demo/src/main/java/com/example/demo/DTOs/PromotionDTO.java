package com.example.demo.DTOs;

import com.example.demo.model.Promotion;

import java.time.LocalDate;

public class PromotionDTO {

    private Long promotionId;
    private String code;
    private String discountType;
    private Float discountValue;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    public PromotionDTO(Promotion promotion) {
        this.status = promotion.getStatus();
        this.endDate = promotion.getEndDate();
        this.startDate = promotion.getStartDate();
        this.discountValue = promotion.getDiscountValue();
        this.discountType = promotion.getDiscountType();
        this.code = promotion.getCode();
        this.promotionId = promotion.getPromotionId();
    }

    public PromotionDTO() {
    }

    public Long getPromotionId() {
        return promotionId;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Float getDiscountValue() {
        return discountValue;
    }

    public String getDiscountType() {
        return discountType;
    }

    public String getCode() {
        return code;
    }
}
