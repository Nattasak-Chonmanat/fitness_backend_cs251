package com.example.demo.service.classReview;

import com.example.demo.DTOs.CReviewDTO;
import com.example.demo.Query;
import com.example.demo.model.CReview;
import com.example.demo.repository.CReviewRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class GetAllReviewsService implements Query<Void, List<CReviewDTO>> {

    private final CReviewRepository cReviewRepository;

    public GetAllReviewsService(CReviewRepository cReviewRepository) {
        this.cReviewRepository = cReviewRepository;
    }

    @Override
    public ResponseEntity<List<CReviewDTO>> execute(Void input) {
        List<CReviewDTO> cReviewDTOS = cReviewRepository.findAll().stream().map(CReviewDTO::new).toList();
        return ResponseEntity.ok(cReviewDTOS);
    }
}
