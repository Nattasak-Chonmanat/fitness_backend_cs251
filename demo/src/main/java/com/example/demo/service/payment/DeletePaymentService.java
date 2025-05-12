package com.example.demo.service.payment;

import com.example.demo.Command;
import com.example.demo.exception.PaymentNotFoundException;
import com.example.demo.model.Payment;
import com.example.demo.model.Response;
import com.example.demo.repository.PaymentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeletePaymentService implements Command<Long, Response> {
    private final PaymentRepository paymentRepository;

    public DeletePaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public ResponseEntity<Response> execute(Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new PaymentNotFoundException(id));
        paymentRepository.delete(payment);
        return ResponseEntity.ok(new Response("Delete payment successful.", HttpStatus.OK));
    }
}
