package com.example.demo.service.payment;


import com.example.demo.DTOs.PaymentDTO;
import com.example.demo.Query;
import com.example.demo.repository.PaymentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetPaymentsService implements Query<Void, List<PaymentDTO>> {

    private final PaymentRepository paymentRepository;

    public GetPaymentsService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public ResponseEntity<List<PaymentDTO>> execute(Void input) {
        List<PaymentDTO> paymentDTOS = paymentRepository.findAll().stream().map(PaymentDTO::new).toList();
        return ResponseEntity.ok(paymentDTOS);
    }
}
