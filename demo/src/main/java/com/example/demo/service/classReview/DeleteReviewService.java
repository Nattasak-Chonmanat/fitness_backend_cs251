package com.example.demo.service.classReview;

import com.example.demo.Command;
import com.example.demo.exception.ReviewNotFoundException;
import com.example.demo.model.CReview;
import com.example.demo.model.Response;
import com.example.demo.repository.CReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteReviewService implements Command<Long, Response> {

    private final CReviewRepository cReviewRepository;

    public DeleteReviewService(CReviewRepository cReviewRepository) {
        this.cReviewRepository = cReviewRepository;
    }

    @Override
    public ResponseEntity<Response> execute(Long reviewId) {
        CReview review = cReviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException(reviewId));
        cReviewRepository.delete(review);
        return ResponseEntity.ok().body(new Response("Delete review successful.", HttpStatus.OK));
    }
}
