package com.example.demo.service.trainerReview;

import com.example.demo.DTOs.TrainerReviewDTO;
import com.example.demo.Query;
import com.example.demo.exception.TrainerNotFoundException;
import com.example.demo.model.Trainer;
import com.example.demo.repository.TRatingRepository;
import com.example.demo.repository.TrainerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetTrainerReviewByTrainerIdService implements Query<Long, List<TrainerReviewDTO>> {

    private final TrainerRepository trainerRepository;
    private final TRatingRepository tRatingRepository;

    public GetTrainerReviewByTrainerIdService(TrainerRepository trainerRepository, TRatingRepository tRatingRepository) {
        this.trainerRepository = trainerRepository;
        this.tRatingRepository = tRatingRepository;
    }

    @Override
    public ResponseEntity<List<TrainerReviewDTO>> execute(Long id) {
        Trainer trainer = trainerRepository.findById(id).orElseThrow(() -> new TrainerNotFoundException(id));
        List<TrainerReviewDTO> trainerReviewDTOS = tRatingRepository.findByTrainer_Id(id).stream().map(review -> {
            String fullName = trainer.getFullName();
            return new TrainerReviewDTO(review, fullName);
        }).toList();

        return ResponseEntity.ok().body(trainerReviewDTOS);
    }
}
