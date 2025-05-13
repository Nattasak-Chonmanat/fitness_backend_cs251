package com.example.demo.service.trainerReview;

import com.example.demo.DTOs.TrainerReviewDTO;
import com.example.demo.Query;
import com.example.demo.exception.TrainerNotFoundException;
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
        if(trainerRepository.findById(id).isEmpty()) throw new TrainerNotFoundException(id);
        List<TrainerReviewDTO> trainerReviewDTOS = tRatingRepository.findByTrainer_Id(id).stream().map(TrainerReviewDTO::new).toList();

        return ResponseEntity.ok().body(trainerReviewDTOS);
    }
}
