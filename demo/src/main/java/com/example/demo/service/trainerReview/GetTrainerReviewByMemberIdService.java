package com.example.demo.service.trainerReview;


import com.example.demo.DTOs.TrainerReviewDTO;
import com.example.demo.Query;
import com.example.demo.exception.MemberNotFoundException;
import com.example.demo.model.Trainer;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.TRatingRepository;
import com.example.demo.repository.TrainerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetTrainerReviewByMemberIdService implements Query<Long, List<TrainerReviewDTO>> {

    private final TRatingRepository tRatingRepository;
    private final MemberRepository memberRepository;
    private final TrainerRepository trainerRepository;

    public GetTrainerReviewByMemberIdService(TRatingRepository tRatingRepository, MemberRepository memberRepository, TrainerRepository trainerRepository) {
        this.tRatingRepository = tRatingRepository;
        this.memberRepository = memberRepository;
        this.trainerRepository = trainerRepository;
    }

    @Override
    public ResponseEntity<List<TrainerReviewDTO>> execute(Long memberId) {
        if(memberRepository.findById(memberId).isEmpty()) throw new MemberNotFoundException(memberId);
        List<TrainerReviewDTO> reviewDTOS = tRatingRepository.findByMember_Id(memberId).stream().map(review -> {
            Trainer trainer = review.getTrainer();
            String fullName = trainer.getFullName();
            return new TrainerReviewDTO(review, fullName);
        }).toList();
        return ResponseEntity.ok().body(reviewDTOS);
    }
}
