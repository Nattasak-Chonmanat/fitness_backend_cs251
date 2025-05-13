package com.example.demo.service.classReview;


import com.example.demo.Command;
import com.example.demo.DTOs.CReviewDTO;
import com.example.demo.exception.ClassNotFoundException;
import com.example.demo.exception.MemberNotFoundException;
import com.example.demo.exception.MembershipNotFoundException;
import com.example.demo.exception.ReviewAlreadyExistException;
import com.example.demo.model.*;
import com.example.demo.model.Class;
import com.example.demo.repository.CReviewRepository;
import com.example.demo.repository.ClassRepository;
import com.example.demo.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CreateReviewService implements Command<CReviewCreateRequest, CReviewDTO> {

    private final CReviewRepository cReviewRepository;
    private final ClassRepository classRepository;
    private final MemberRepository memberRepository;

    public CreateReviewService(CReviewRepository cReviewRepository, ClassRepository classRepository, MemberRepository memberRepository) {
        this.cReviewRepository = cReviewRepository;
        this.classRepository = classRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public ResponseEntity<CReviewDTO> execute(CReviewCreateRequest request) {

        if (!cReviewRepository.findByMember_IdAndAClass_Id(request.getMemberId(), request.getClassId()).isEmpty()) {
            throw new ReviewAlreadyExistException();
        }

        Member member = memberRepository.findById(request.getMemberId()).orElseThrow(() -> new MembershipNotFoundException(request.getMemberId()));
        Class clazz = classRepository.findById(request.getClassId()).orElseThrow(() -> new ClassNotFoundException(request.getClassId()));

        CReview cReview = new CReview();
        cReview.setReview(request.getReview());
        cReview.setaClass(clazz);
        cReview.setMember(member);
        cReview.setRate(request.getRate());
        cReview.setReviewDate(LocalDate.now());
        CReview review = cReviewRepository.save(cReview);

        List<CReview> reviewList = cReviewRepository.findByAClass_Id(cReview.getaClass().getId());
        float avg = (float) reviewList.stream().mapToDouble(CReview::getRate).average().orElse(0.0);
        clazz.setRating(avg);
        classRepository.save(clazz);

        return ResponseEntity.status(HttpStatus.CREATED).body(new CReviewDTO(review));
    }
}
