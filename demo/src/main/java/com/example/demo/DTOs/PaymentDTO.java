package com.example.demo.DTOs;

import com.example.demo.model.Payment;


import java.time.LocalDate;

public class PaymentDTO {

    private Long paymentId;
    private Float amount;
    private LocalDate paymentDate;
    private String paymentStatus;
    private String receiptNumber;
    private String paymentMethod;
    private Long memberId;
    private String planName;

    public PaymentDTO(Payment payment) {
        this.paymentId = payment.getPaymentId();
        this.memberId = payment.getMember().getId();
        this.paymentMethod = payment.getPaymentMethod();
        this.receiptNumber = payment.getReceiptNumber();
        this.paymentStatus = payment.getPaymentStatus();
        this.paymentDate = payment.getPaymentDate();
        this.amount = payment.getAmount();
        this.planName = payment.getMembershipPlan().getPlanName();
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public PaymentDTO() {
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}
