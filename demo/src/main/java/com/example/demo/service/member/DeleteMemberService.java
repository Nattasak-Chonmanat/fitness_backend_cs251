package com.example.demo.service.member;

import com.example.demo.Command;
import com.example.demo.model.*;
import com.example.demo.model.Class;
import com.example.demo.repository.*;
import com.example.demo.exception.MemberNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DeleteMemberService implements Command<Long, Response> {

    private final MemberRepository memberRepository;
    private final ClassRepository classRepository;
    private final TrainerRepository trainerRepository;

    public DeleteMemberService(MemberRepository memberRepository, ClassRepository classRepository, TrainerRepository trainerRepository) {
        this.memberRepository = memberRepository;
        this.classRepository = classRepository;
        this.trainerRepository = trainerRepository;
    }

    @Override
    public ResponseEntity<Response> execute(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));

        List<ClassBooking> bookings = member.getClassBookingList();
        List<TRating> trainerReviews = member.getTrainerRatings();
        List<CReview> reviews = member.getReviews();
        memberRepository.delete(member);


        // find affected classes
        Set<Class> affectedClasses = new HashSet<>();
        for (CReview review : reviews) {
            Class clazz = review.getaClass();
            if(clazz != null) {
                affectedClasses.add(clazz);
            }
        }

        // find affected trainers
        Set<Trainer> affectedTrainers = new HashSet<>();
        for (TRating review : trainerReviews) {
            Trainer trainer = review.getTrainer();
            if(trainer != null) {
                affectedTrainers.add(trainer);
            }
        }

        // Update class's capacity
        for(ClassBooking booking : bookings) {
            if(booking.getaClass() != null && "booked".equalsIgnoreCase(booking.getStatus())) {
                Class clazz = booking.getaClass();
                clazz.setCapacity(clazz.getCapacity() + 1);
                classRepository.save(clazz);
            }
        }

        // Update trainer's rating
        for (Trainer trainer : affectedTrainers) {
            if(!trainer.getTrainerRatings().isEmpty()) {
                double newRating = trainer.getTrainerRatings().stream().mapToDouble(TRating::getRate).average().orElse(0.0);
                trainer.setRating((float)newRating);
            } else {
                trainer.setRating(0.0f);
            }
            trainerRepository.save(trainer);
        }


        // Update class's rating
        for(Class clazz : affectedClasses) {
            List<CReview> remainingReviews = clazz.getReviews();
            if(!remainingReviews.isEmpty()) {
                double newRating = remainingReviews.stream().mapToDouble(CReview::getRate).average().orElse(0.0);
                clazz.setRating((float)newRating);
            } else {
                clazz.setRating(0.0f);
            }
            classRepository.save(clazz);
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response("Delete member successful", HttpStatus.OK));
    }

}
