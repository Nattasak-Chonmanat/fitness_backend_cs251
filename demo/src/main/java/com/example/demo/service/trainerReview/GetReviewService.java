package com.example.demo.service.trainerReview;

import com.example.demo.DTOs.TrainerReviewDTO;
import com.example.demo.Query;
import com.example.demo.model.Trainer;
import com.example.demo.repository.TRatingRepository;
import com.example.demo.repository.TrainerRepository;
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
        List<TrainerReviewDTO> trainerReviewDTOS = tRatingRepository.findAll().stream().map(review -> {
            Trainer trainer = review.getTrainer();
            String fullName = trainer.getFullName();
            return new TrainerReviewDTO(review, fullName);
        }).toList();
        return ResponseEntity.ok().body(trainerReviewDTOS);
    }
}
