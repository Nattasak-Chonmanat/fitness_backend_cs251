package com.example.demo.service.payment;

import com.example.demo.Command;
import com.example.demo.DTOs.PaymentDTO;
import com.example.demo.exception.MemberNotFoundException;
import com.example.demo.exception.PromotionNotFoundException;
import com.example.demo.exception.RequestInvalidException;
import com.example.demo.model.CreatePaymentRequest;
import com.example.demo.model.Member;
import com.example.demo.model.Payment;
import com.example.demo.model.Promotion;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.PromotionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreatePaymentService implements Command<CreatePaymentRequest, PaymentDTO> {

    private final PaymentRepository paymentRepository;
    private final MemberRepository memberRepository;
    private final PromotionRepository promotionRepository;

    public CreatePaymentService(PaymentRepository paymentRepository, MemberRepository memberRepository, PromotionRepository promotionRepository) {
        this.paymentRepository = paymentRepository;
        this.memberRepository = memberRepository;
        this.promotionRepository = promotionRepository;
    }

    @Override
    public ResponseEntity<PaymentDTO> execute(CreatePaymentRequest request) {
        Member member = memberRepository.findById(request.getMemberId()).orElseThrow(() -> new MemberNotFoundException(request.getMemberId()));
        Payment payment = new Payment();
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setPaymentStatus("Pending");
        payment.setMember(member);
        if(request.getPromotionCode() != null) {
            String code = request.getPromotionCode();
            Promotion promotion = promotionRepository.findByCode(code).orElseThrow(() -> new PromotionNotFoundException(code));
            if(!promotion.getStatus().equalsIgnoreCase("Active")) throw new RequestInvalidException("Promotion is already expired.");
            float discountValue;
            if(promotion.getDiscountType().equalsIgnoreCase("percentage")) {
                discountValue = (100f - promotion.getDiscountValue()) / 100;
                payment.setAmount(request.getAmount() * discountValue);
            } else if (promotion.getDiscountType().equalsIgnoreCase("fixed amount")) {
                discountValue = promotion.getDiscountValue();
                payment.setAmount(request.getAmount() - discountValue);
            }
        }
        paymentRepository.save(payment);
        return ResponseEntity.status(HttpStatus.CREATED).body(new PaymentDTO(payment));
    }
}
