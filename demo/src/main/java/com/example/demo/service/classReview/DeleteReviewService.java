package com.example.demo.service.classReview;

import com.example.demo.Command;
import com.example.demo.exception.ReviewNotFoundException;
import com.example.demo.model.CReview;
import com.example.demo.model.Class;
import com.example.demo.model.Response;
import com.example.demo.repository.CReviewRepository;
import com.example.demo.repository.ClassRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteReviewService implements Command<Long, Response> {

    private final CReviewRepository cReviewRepository;
    private final ClassRepository classRepository;

    public DeleteReviewService(CReviewRepository cReviewRepository, ClassRepository classRepository) {
        this.cReviewRepository = cReviewRepository;
        this.classRepository = classRepository;
    }

    @Override
    public ResponseEntity<Response> execute(Long reviewId) {
        CReview review = cReviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException(reviewId));
        Class clazz = review.getaClass();
        cReviewRepository.delete(review);
        double classRating = clazz.getReviews().stream().mapToDouble(CReview::getRate).average().orElse(0.0);
        clazz.setRating((float) classRating);
        classRepository.save(clazz);
        return ResponseEntity.ok().body(new Response("Delete review successful.", HttpStatus.OK));
    }
}
