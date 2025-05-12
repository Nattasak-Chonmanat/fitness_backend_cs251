package com.example.demo.service.promotion;

import com.example.demo.Command;
import com.example.demo.exception.PromotionNotFoundException;
import com.example.demo.model.Response;
import com.example.demo.repository.PromotionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeletePromotionByIdService implements Command<Long, Response> {

    private final PromotionRepository promotionRepository;

    public DeletePromotionByIdService(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    @Override
    public ResponseEntity<Response> execute(Long id) {
        if(!promotionRepository.existsById(id)) throw new PromotionNotFoundException("Promotion not found with an ID: " + id);
        promotionRepository.deleteById(id);
        return ResponseEntity.ok(new Response("Delete promotion successful.", HttpStatus.OK));
    }
}
