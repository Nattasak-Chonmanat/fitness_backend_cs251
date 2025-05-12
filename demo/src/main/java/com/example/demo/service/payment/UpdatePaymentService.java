package com.example.demo.service.payment;

import com.example.demo.UpdateCommand;
import com.example.demo.exception.PaymentNotFoundException;
import com.example.demo.exception.RequestInvalidException;
import com.example.demo.model.Payment;
import com.example.demo.model.Response;
import com.example.demo.model.UpdatePaymentRequest;
import com.example.demo.repository.PaymentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdatePaymentService implements UpdateCommand<Long, UpdatePaymentRequest, Response> {

    private final PaymentRepository paymentRepository;
//    private final

    public UpdatePaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public ResponseEntity<Response> execute(Long id, UpdatePaymentRequest request) {
        if(!request.getPaymentStatus().equals("Complete") && !request.getPaymentStatus().equals("Canceled")) throw new RequestInvalidException(
                "Payment status is not valid (Must be Complete or Canceled) ");
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new PaymentNotFoundException(id));
        payment.setPaymentStatus(request.getPaymentStatus());
//        if(request.getPaymentStatus().equals("Complete")) {
//
//        }
        paymentRepository.save(payment);
        return ResponseEntity.ok(new Response("Update payment successful", HttpStatus.OK));
    }
}
