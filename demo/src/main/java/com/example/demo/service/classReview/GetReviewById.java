package com.example.demo.service.classReview;

import com.example.demo.DTOs.CReviewDTO;
import com.example.demo.Query;
import com.example.demo.exception.ReviewNotFoundException;
import com.example.demo.model.CReview;
import com.example.demo.repository.CReviewRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetReviewById implements Query<Long, CReviewDTO> {

    private final CReviewRepository cReviewRepository;

    public GetReviewById(CReviewRepository cReviewRepository) {
        this.cReviewRepository = cReviewRepository;
    }


    @Override
    public ResponseEntity<CReviewDTO> execute(Long id) {
        CReview review = cReviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException(id));
        return ResponseEntity.ok().body(new CReviewDTO(review));
    }
}
