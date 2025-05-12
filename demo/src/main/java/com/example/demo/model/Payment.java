package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @Column(name = "amount", nullable = false)
    private Float amount;

    @Column(name = "paymentDate")
    private LocalDate paymentDate;

    @Column(name = "paymentStatus", nullable = false)
    private String paymentStatus;

    @Column(name = "receiptNumber", nullable = false)
    private String receiptNumber;

    @Column(name = "paymentMethod", nullable = false)
    private String paymentMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    @NotNull(message = "Membership plan is required.")
    private MembershipPlan membershipPlan;

    public MembershipPlan getMembershipPlan() {
        return membershipPlan;
    }

    public void setMembershipPlan(MembershipPlan membershipPlan) {
        this.membershipPlan = membershipPlan;
    }

    @PrePersist
    public void generateReceiptNumber() {
        if (this.receiptNumber == null || this.receiptNumber.isEmpty()) {

            this.receiptNumber = "RCPT-" + LocalDate.now() + "-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        }

        if (this.paymentDate == null) {
            this.paymentDate = LocalDate.now();
        }
    }


    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Payment() {
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
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
