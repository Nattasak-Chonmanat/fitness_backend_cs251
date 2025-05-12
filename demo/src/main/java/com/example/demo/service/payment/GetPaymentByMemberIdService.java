package com.example.demo.service.payment;

import com.example.demo.DTOs.PaymentDTO;
import com.example.demo.Query;
import com.example.demo.exception.MemberNotFoundException;
import com.example.demo.model.Payment;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.PaymentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetPaymentByMemberIdService implements Query<Long, List<PaymentDTO>> {

    private final MemberRepository memberRepository;
    private final PaymentRepository paymentRepository;

    public GetPaymentByMemberIdService(MemberRepository memberRepository, PaymentRepository paymentRepository) {
        this.memberRepository = memberRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public ResponseEntity<List<PaymentDTO>> execute(Long id) {
        if(!memberRepository.existsById(id)) throw new MemberNotFoundException(id);
        List<PaymentDTO> paymentDTOS = paymentRepository.findByMember_Id(id).stream().map(PaymentDTO::new).toList();
        return ResponseEntity.ok(paymentDTOS);
    }
}
