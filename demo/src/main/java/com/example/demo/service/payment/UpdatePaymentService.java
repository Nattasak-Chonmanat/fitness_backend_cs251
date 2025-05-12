package com.example.demo.service.payment;

import com.example.demo.UpdateCommand;
import com.example.demo.exception.PaymentNotFoundException;
import com.example.demo.exception.RequestInvalidException;
import com.example.demo.model.*;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MembershipPlanRepository;
import com.example.demo.repository.PaymentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UpdatePaymentService implements UpdateCommand<Long, UpdatePaymentRequest, Response> {

    private final PaymentRepository paymentRepository;
    private final MemberRepository memberRepository;

    public UpdatePaymentService(PaymentRepository paymentRepository, MemberRepository memberRepository) {
        this.paymentRepository = paymentRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public ResponseEntity<Response> execute(Long id, UpdatePaymentRequest request) {
        if(!request.getPaymentStatus().equals("Complete") && !request.getPaymentStatus().equals("Canceled")) throw new RequestInvalidException(
                "Payment status is not valid (Must be Complete or Canceled) ");
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new PaymentNotFoundException(id));
        payment.setPaymentStatus(request.getPaymentStatus());
        Member member = payment.getMember();
        if(request.getPaymentStatus().equals("Complete")) {
            MembershipPlan plan = payment.getMembershipPlan();
            member.setMembershipPlan(plan);
            member.setMemberStatus("Active");
            member.setExpireDate(LocalDate.now().plusDays(plan.getDuration()));
            member.setPlanName(plan.getPlanName());
        }
        paymentRepository.save(payment);
        memberRepository.save(member);
        return ResponseEntity.ok(new Response("Update payment successful", HttpStatus.OK));
    }
}
