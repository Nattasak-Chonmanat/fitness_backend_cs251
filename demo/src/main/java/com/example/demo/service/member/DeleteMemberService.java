package com.example.demo.service.member;

import com.example.demo.Command;
import com.example.demo.model.CReview;
import com.example.demo.model.Member;
import com.example.demo.model.Response;
import com.example.demo.repository.CReviewRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.exception.MemberNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteMemberService implements Command<Long, Response> {

    private final MemberRepository memberRepository;
    private final CReviewRepository cReviewRepository;

    public DeleteMemberService(MemberRepository memberRepository, CReviewRepository cReviewRepository) {
        this.memberRepository = memberRepository;
        this.cReviewRepository = cReviewRepository;
    }

    @Override
    public ResponseEntity<Response> execute(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberNotFoundException(memberId));

        List<CReview> reviews = member.getReviews();
        cReviewRepository.deleteAll(reviews);

        member.getAttendances().clear();
        member.getClassBookingList().clear();
        member.getReviews().clear();
        member.getTrainerRatings().clear();
        memberRepository.save(member);
        memberRepository.deleteById(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(new Response("Delete member successful", HttpStatus.OK));
    }
}
