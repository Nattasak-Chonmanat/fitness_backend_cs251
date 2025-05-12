package com.example.demo.service.payment;

import com.example.demo.Command;
import com.example.demo.DTOs.PaymentDTO;
import com.example.demo.exception.MemberNotFoundException;
import com.example.demo.exception.MembershipNotFoundException;
import com.example.demo.exception.PromotionNotFoundException;
import com.example.demo.exception.RequestInvalidException;
import com.example.demo.model.*;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MembershipPlanRepository;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.PromotionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CreatePaymentService implements Command<CreatePaymentRequest, PaymentDTO> {

    private final PaymentRepository paymentRepository;
    private final MemberRepository memberRepository;
    private final PromotionRepository promotionRepository;
    private final MembershipPlanRepository membershipPlanRepository;

    public CreatePaymentService(PaymentRepository paymentRepository, MemberRepository memberRepository, PromotionRepository promotionRepository, MembershipPlanRepository membershipPlanRepository) {
        this.paymentRepository = paymentRepository;
        this.memberRepository = memberRepository;
        this.promotionRepository = promotionRepository;
        this.membershipPlanRepository = membershipPlanRepository;
    }

    @Override
    public ResponseEntity<PaymentDTO> execute(CreatePaymentRequest request) {
        Member member = memberRepository.findById(request.getMemberId()).orElseThrow(() -> new MemberNotFoundException(request.getMemberId()));
        MembershipPlan plan = membershipPlanRepository.findById(request.getPlanId()).orElseThrow(() -> new MembershipNotFoundException(request.getPlanId()));


        Payment payment = new Payment();

        if(request.getPromotionCode() != null) {
            String code = request.getPromotionCode();
            Promotion promotion = promotionRepository.findByCode(code).orElseThrow(() ->
                    new PromotionNotFoundException("Promotion not found with code " + request.getPromotionCode()));
            LocalDate today = LocalDate.now();
            if(today.isBefore(promotion.getStartDate()) || today.isAfter(promotion.getEndDate())) throw new RequestInvalidException("Promotion is already expired.");
            float discountValue;
            if(promotion.getDiscountType().equalsIgnoreCase("percentage")) {
                discountValue = (100f - promotion.getDiscountValue()) / 100;
                payment.setAmount(request.getAmount() * discountValue);
            } else if (promotion.getDiscountType().equalsIgnoreCase("fixed amount")) {
                discountValue = promotion.getDiscountValue();
                payment.setAmount(request.getAmount() - discountValue);
            }
        } else {
            payment.setAmount(request.getAmount());
        }
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setPaymentStatus("Pending");
        payment.setMember(member);
        payment.setMembershipPlan(plan);
        Float amount = request.getAmount();

        paymentRepository.save(payment);
        return ResponseEntity.status(HttpStatus.CREATED).body(new PaymentDTO(payment));
    }
}
