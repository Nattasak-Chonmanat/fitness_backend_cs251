package com.example.demo.service.trainerReview;

import com.example.demo.Command;
import com.example.demo.exception.ReviewNotFoundException;
import com.example.demo.model.Response;
import com.example.demo.model.TRating;
import com.example.demo.repository.TRatingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@Service
public class DeleteTrainerReviewService implements Command<Long, Response> {

    private final TRatingRepository tRatingRepository;

    public DeleteTrainerReviewService(TRatingRepository tRatingRepository) {
        this.tRatingRepository = tRatingRepository;
    }

    @Override
    public ResponseEntity<Response> execute(Long id) {
        TRating rating = tRatingRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException(id));
        tRatingRepository.delete(rating);
        return ResponseEntity.ok().body(new Response("Delete review successful.", HttpStatus.OK));
    }
}
