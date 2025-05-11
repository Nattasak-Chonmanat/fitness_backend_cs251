package com.example.demo.service.trainerReview;

import com.example.demo.DTOs.TrainerReviewDTO;
import com.example.demo.Query;
import com.example.demo.repository.TRatingRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetReviewService implements Query<Void, List<TrainerReviewDTO>> {
    private final TRatingRepository tRatingRepository;

    public GetReviewService(TRatingRepository tRatingRepository) {
        this.tRatingRepository = tRatingRepository;
    }

    @Override
    public ResponseEntity<List<TrainerReviewDTO>> execute(Void input) {
        List<TrainerReviewDTO> trainerReviewDTOS = tRatingRepository.findAll().stream().map(TrainerReviewDTO::new).toList();
        return ResponseEntity.ok().body(trainerReviewDTOS);
    }
}
