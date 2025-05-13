package com.example.demo.service.classReview;

import com.example.demo.UpdateCommand;
import com.example.demo.exception.ReviewNotFoundException;
import com.example.demo.model.CReview;
import com.example.demo.model.Class;
import com.example.demo.model.Response;
import com.example.demo.model.UpdateClassReviewRequest;
import com.example.demo.repository.CReviewRepository;
import com.example.demo.repository.ClassBookingRepository;
import com.example.demo.repository.ClassRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateReviewService implements UpdateCommand<Long, UpdateClassReviewRequest, Response> {

    private final CReviewRepository cReviewRepository;
    private final ClassRepository classRepository;

    public UpdateReviewService(CReviewRepository cReviewRepository, ClassRepository classRepository) {
        this.cReviewRepository = cReviewRepository;
        this.classRepository = classRepository;
    }

    @Override
    public ResponseEntity<Response> execute(Long id, UpdateClassReviewRequest request) {
        CReview review = cReviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException(id));
        Class clazz = review.getaClass();
        if(request.getReview() != null) review.setReview(request.getReview());
        review.setReviewDate(request.getReviewDate());
        review.setRate(request.getRate());
        cReviewRepository.save(review);
        double classRating = clazz.getReviews().stream().mapToDouble(CReview::getRate).average().orElse(0.0f);
        clazz.setRating((float) classRating);
        classRepository.save(clazz);
        return ResponseEntity.ok().body(new Response("Update review successful.", HttpStatus.OK));
    }
}
