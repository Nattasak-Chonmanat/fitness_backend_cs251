package com.example.demo.service.trainerReview;


import com.example.demo.DTOs.TrainerReviewDTO;
import com.example.demo.Query;
import com.example.demo.exception.MemberNotFoundException;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.TRatingRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetTrainerReviewByMemberIdService implements Query<Long, List<TrainerReviewDTO>> {

    private final TRatingRepository tRatingRepository;
    private final MemberRepository memberRepository;

    public GetTrainerReviewByMemberIdService(TRatingRepository tRatingRepository, MemberRepository memberRepository) {
        this.tRatingRepository = tRatingRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public ResponseEntity<List<TrainerReviewDTO>> execute(Long memberId) {
        if(memberRepository.findById(memberId).isEmpty()) throw new MemberNotFoundException(memberId);
        List<TrainerReviewDTO> reviewDTOS = tRatingRepository.findByMemberId(memberId).stream().map(TrainerReviewDTO::new).toList();
        return ResponseEntity.ok().body(reviewDTOS);
    }
}
