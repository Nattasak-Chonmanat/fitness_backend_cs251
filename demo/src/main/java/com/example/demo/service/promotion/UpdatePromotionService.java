package com.example.demo.service.promotion;

import com.example.demo.UpdateCommand;
import com.example.demo.exception.PromotionNotFoundException;
import com.example.demo.exception.RequestInvalidException;
import com.example.demo.model.Promotion;
import com.example.demo.model.Response;
import com.example.demo.model.UpdatePromotionRequest;
import com.example.demo.repository.PromotionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UpdatePromotionService implements UpdateCommand<Long, UpdatePromotionRequest, Response> {

    private final PromotionRepository promotionRepository;

    public UpdatePromotionService(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    @Override
    public ResponseEntity<Response> execute(Long id, UpdatePromotionRequest request) {
        Promotion promotion = promotionRepository.findById(id).orElseThrow(() -> new PromotionNotFoundException("Promotion not found with an ID: " + id));
        if(!request.getDiscountType().equals("Percentage") && !request.getDiscountType().equals("Fixed Amount")){
            throw new RequestInvalidException("Discount type must be only 'Percentage' or 'Fixed Amount'");
        }

        if(request.getDiscountType().equalsIgnoreCase("percentage")) {
            if(request.getDiscountValue() > 100) throw new RequestInvalidException("Discount value must less than or equal 100 for a percentage discount type.");
        }

        LocalDate today = LocalDate.now();

        if (request.getStartDate().isAfter(request.getEndDate())) {
            throw new RequestInvalidException("Start date must be before or equal to end date.");
        }

        promotion.setCode(request.getCode().toUpperCase());
        promotion.setDiscountType(request.getDiscountType());
        promotion.setDiscountValue(request.getDiscountValue());
        promotion.setStartDate(request.getStartDate());
        promotion.setEndDate(request.getEndDate());
        promotionRepository.save(promotion);
        return ResponseEntity.ok(new Response("Update promotion successful.", HttpStatus.OK));
    }
}
