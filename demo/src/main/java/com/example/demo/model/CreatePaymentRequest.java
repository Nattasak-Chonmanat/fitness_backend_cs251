package com.example.demo.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;


public class CreatePaymentRequest {

    @PositiveOrZero(message = "Amount must be positive.")
    @NotNull(message = "Amount is required.")
    private Float amount;

    @NotBlank(message = "Payment method is required.")
    private String paymentMethod;

    @NotNull(message = "Member id is required.")
    private Long memberId;


    @NotNull(message = "Plan id is required.")
    private Long planId;



    public @NotNull(message = "Plan id is required.") Long getPlanId() {
        return planId;
    }

    public void setPlanId(@NotNull(message = "Plan id is required.") Long planId) {
        this.planId = planId;
    }

    private String promotionCode;


    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public CreatePaymentRequest() {
    }


    public @NotNull(message = "Member id is required.") Long getMemberId() {
        return memberId;
    }

    public void setMemberId(@NotNull(message = "Member id is required.") Long memberId) {
        this.memberId = memberId;
    }

    public @NotBlank(message = "Payment method is required.") String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(@NotBlank(message = "Payment method is required.") String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public @PositiveOrZero(message = "Amount must be positive.") Float getAmount() {
        return amount;
    }

    public void setAmount(@PositiveOrZero(message = "Amount must be positive.") Float amount) {
        this.amount = amount;
    }
}
