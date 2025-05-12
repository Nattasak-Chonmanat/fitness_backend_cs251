package com.example.demo.DTOs;

import com.example.demo.model.Promotion;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "ข้อมูลที่สร้างโปรโมชั่นใหม่")
public class PromotionDTO {

    @Schema(description = "Promotion's Id", examples = "1")
    private Long promotionId;

    @Schema(examples = "SAVE25", description = "Promotion's code")
    private String code;

    @Schema(examples = "25", description = "Discount value")
    private Float discountValue;

    @Schema(examples = "Percentage", description = "Discount type")
    private String discountType;


    @Schema(examples = "2025-10-20", description = "Promotion's start date")
    private LocalDate startDate;

    @Schema(examples = "2025-11-20",  description = "Promotion's end date")
    private LocalDate endDate;

    @Schema(examples = "Active", description = "Promotion's status")
    private String status;

    public PromotionDTO(Promotion promotion, String status) {
        this.endDate = promotion.getEndDate();
        this.startDate = promotion.getStartDate();
        this.discountValue = promotion.getDiscountValue();
        this.discountType = promotion.getDiscountType();
        this.code = promotion.getCode();
        this.promotionId = promotion.getPromotionId();
        this.status = status;
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
