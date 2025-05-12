package com.example.demo.service.promotion;

import com.example.demo.DTOs.PromotionDTO;
import com.example.demo.Query;
import com.example.demo.model.GetPromotionStatus;
import com.example.demo.repository.PromotionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GetAllActivePromotionService implements Query<Void, List<PromotionDTO>> {

    private final PromotionRepository promotionRepository;

    public GetAllActivePromotionService(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    @Override
    public ResponseEntity<List<PromotionDTO>> execute(Void input) {
        LocalDate today = LocalDate.now();

        List<PromotionDTO> promotionDTOS = promotionRepository.findAll().stream()
                .filter(promo -> !today.isBefore(promo.getStartDate()) && !today.isAfter(promo.getEndDate()))
                .map(promotion -> {
                    String status = GetPromotionStatus.execute(promotion);
                    return new PromotionDTO(promotion, status);
                })
                .toList();

        return ResponseEntity.ok(promotionDTOS);
    }
}
