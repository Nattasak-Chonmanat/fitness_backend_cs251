package com.example.demo.service.classReview;


import com.example.demo.DTOs.CReviewDTO;
import com.example.demo.DTOs.CReviewSummaryDTO;
import com.example.demo.Query;
import com.example.demo.exception.MemberNotFoundException;
import com.example.demo.exception.MembershipNotFoundException;
import com.example.demo.model.CReview;
import com.example.demo.model.Member;
import com.example.demo.repository.CReviewRepository;
import com.example.demo.repository.ClassRepository;
import com.example.demo.repository.MemberRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetReviewByMemberId implements Query<Long, List<CReviewSummaryDTO>> {

    private final CReviewRepository cReviewRepository;
    private final MemberRepository memberRepository;
    private final ClassRepository classRepository;

    public GetReviewByMemberId(CReviewRepository cReviewRepository, MemberRepository memberRepository, ClassRepository classRepository) {
        this.cReviewRepository = cReviewRepository;
        this.memberRepository = memberRepository;
        this.classRepository = classRepository;
    }

    @Override
    public ResponseEntity<List<CReviewSummaryDTO>> execute(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new MemberNotFoundException(id));
        List<CReviewSummaryDTO> cReviewDTOS = cReviewRepository.findByMember_Id(id).stream().map(review -> {
            String username = member.getUserName();
            String className = review.getaClass().getClassName();
            return new CReviewSummaryDTO(review, username, className);
        }).toList();
        return ResponseEntity.ok().body(cReviewDTOS);
    }
}
