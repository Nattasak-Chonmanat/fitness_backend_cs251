package com.example.demo.service.trainerReview;

import com.example.demo.UpdateCommand;
import com.example.demo.exception.ReviewNotFoundException;
import com.example.demo.exception.TrainerNotFoundException;
import com.example.demo.model.Response;
import com.example.demo.model.TRating;
import com.example.demo.model.Trainer;
import com.example.demo.model.UpdateTrainerReviewRequest;
import com.example.demo.repository.TRatingRepository;
import com.example.demo.repository.TrainerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UpdateTrainerReviewService implements UpdateCommand<Long, UpdateTrainerReviewRequest, Response> {

    private final TRatingRepository tRatingRepository;
    private final TrainerRepository trainerRepository;

    public UpdateTrainerReviewService(TRatingRepository tRatingRepository, TrainerRepository trainerRepository) {
        this.tRatingRepository = tRatingRepository;
        this.trainerRepository = trainerRepository;
    }

    @Override
    public ResponseEntity<Response> execute(Long id, UpdateTrainerReviewRequest request) {

        TRating rating = tRatingRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException(id));
        boolean isUpdated = false;

        if(request.getReview() != null && !request.getReview().isBlank()) {
            rating.setReview(request.getReview());
            isUpdated = true;
        }
        if(request.getRate() != null) {
            rating.setRate(request.getRate());
            isUpdated = true;
        }
        if (isUpdated) {
            rating.setReviewDate(LocalDate.now());
            tRatingRepository.save(rating);

            Trainer trainer = trainerRepository.findById(rating.getTrainer().getId())
                    .orElseThrow(() -> new TrainerNotFoundException(rating.getTrainer().getId()));

            List<TRating> ratings = tRatingRepository.findByTrainer_Id(trainer.getId());
            float avg = (float) ratings.stream()
                    .mapToDouble(TRating::getRate)
                    .average()
                    .orElse(0.0);

            trainer.setRating(avg);
            trainerRepository.save(trainer);
        }

        return ResponseEntity.ok().body(new Response("Update review successful.", HttpStatus.OK));
    }
}
