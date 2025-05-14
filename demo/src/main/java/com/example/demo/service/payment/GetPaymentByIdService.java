package com.example.demo.service.payment;

import com.example.demo.DTOs.PaymentDTO;
import com.example.demo.Query;
import com.example.demo.exception.PaymentNotFoundException;
import com.example.demo.model.Payment;
import com.example.demo.repository.PaymentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetPaymentByIdService implements Query<Long, PaymentDTO> {

    private  final PaymentRepository paymentRepository;

    public GetPaymentByIdService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public ResponseEntity<PaymentDTO> execute(Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new PaymentNotFoundException(id));
        return ResponseEntity.ok(new PaymentDTO(payment));
    }
}
