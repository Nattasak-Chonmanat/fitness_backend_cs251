package com.example.demo.service.promotion;

import com.example.demo.DTOs.PromotionDTO;
import com.example.demo.Query;
import com.example.demo.exception.PromotionNotFoundException;
import com.example.demo.model.GetPromotionStatus;
import com.example.demo.model.Promotion;
import com.example.demo.repository.PromotionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetPromotionByIdService implements Query<Long, PromotionDTO> {

    private final PromotionRepository promotionRepository;

    public GetPromotionByIdService(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    @Override
    public ResponseEntity<PromotionDTO> execute(Long id) {
        Promotion promotion = promotionRepository.findById(id).orElseThrow(() -> new PromotionNotFoundException("Promotion not found with an ID: " + id));
        String status = GetPromotionStatus.execute(promotion);
        return ResponseEntity.ok(new PromotionDTO(promotion, status));
    }
}
