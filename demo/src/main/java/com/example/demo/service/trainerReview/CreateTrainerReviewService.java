package com.example.demo.service.trainerReview;

import com.example.demo.Command;
import com.example.demo.DTOs.TrainerReviewDTO;
import com.example.demo.exception.MemberNotFoundException;
import com.example.demo.exception.TrainerNotFoundException;
import com.example.demo.model.CreateTrainerReviewRequest;
import com.example.demo.model.Member;
import com.example.demo.model.TRating;
import com.example.demo.model.Trainer;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.TRatingRepository;
import com.example.demo.repository.TrainerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class CreateTrainerReviewService implements Command<CreateTrainerReviewRequest, TrainerReviewDTO> {

    private final TRatingRepository tRatingRepository;
    private final MemberRepository memberRepository;
    private final TrainerRepository trainerRepository;

    public CreateTrainerReviewService(TRatingRepository tRatingRepository, MemberRepository memberRepository, TrainerRepository trainerRepository) {
        this.tRatingRepository = tRatingRepository;
        this.memberRepository = memberRepository;
        this.trainerRepository = trainerRepository;
    }

    @Override
    public ResponseEntity<TrainerReviewDTO> execute(CreateTrainerReviewRequest dto) {
        Member member = memberRepository.findById(dto.getMemberId()).orElseThrow(() -> new MemberNotFoundException(dto.getMemberId()));
        Trainer trainer = trainerRepository.findById(dto.getTrainerId()).orElseThrow(() -> new TrainerNotFoundException(dto.getTrainerId()));

        TRating rating = new TRating();
        rating.setTrainer(trainer);
        rating.setMember(member);
        rating.setReview(dto.getReview());
        rating.setRate(dto.getRate());
        rating.setReviewDate(LocalDate.now());

        tRatingRepository.save(rating);

        List<TRating> ratings = tRatingRepository.findByTrainer_Id(trainer.getId());
        float avg = (float) ratings.stream().mapToDouble(TRating::getRate).average().orElse(0.0);

        trainer.setRating(avg);
        trainerRepository.save(trainer);

        return ResponseEntity.status(HttpStatus.CREATED).body(new TrainerReviewDTO(rating, trainer.getFullName()));
    }
}
