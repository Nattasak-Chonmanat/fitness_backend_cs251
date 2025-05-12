package com.example.demo.service.promotion;

import com.example.demo.Command;
import com.example.demo.DTOs.PromotionDTO;
import com.example.demo.exception.RequestInvalidException;
import com.example.demo.model.CreatePromotionRequest;
import com.example.demo.model.GetPromotionStatus;
import com.example.demo.model.Promotion;
import com.example.demo.repository.PromotionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.time.LocalDate;

@Service
public class CreatePromotionService implements Command<CreatePromotionRequest, PromotionDTO> {

    private final PromotionRepository promotionRepository;

    public CreatePromotionService(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    @Override
    public ResponseEntity<PromotionDTO> execute(CreatePromotionRequest request) {

        if(!request.getDiscountType().equalsIgnoreCase("percentage") && !request.getDiscountType().equalsIgnoreCase("fixed amount")){
            throw new RequestInvalidException("Discount type must be only 'Percentage' or 'Fixed Amount'");
        }

        if(request.getDiscountType().equalsIgnoreCase("percentage")) {
            if(request.getDiscountValue() > 100) throw new RequestInvalidException("Discount value must less than or equal 100 for a percentage discount type.");
        }

        LocalDate today = LocalDate.now();

        if (request.getStartDate().isAfter(request.getEndDate())) {
            throw new RequestInvalidException("Start date must be before or equal to end date.");
        }
        Promotion promotion = new Promotion();
        promotion.setCode(request.getCode().toUpperCase());
        promotion.setDiscountType(request.getDiscountType());
        promotion.setDiscountValue(request.getDiscountValue());
        promotion.setStartDate(request.getStartDate());
        promotion.setEndDate(request.getEndDate());

        promotionRepository.save(promotion);
        String status = GetPromotionStatus.execute(promotion);
        return ResponseEntity.status(HttpStatus.CREATED).body(new PromotionDTO(promotion, status));
    }
}
