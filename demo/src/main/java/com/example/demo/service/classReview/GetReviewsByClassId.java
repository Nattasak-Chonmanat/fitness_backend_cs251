package com.example.demo.service.classReview;

import com.example.demo.DTOs.CReviewDTO;
import com.example.demo.DTOs.CReviewSummaryDTO;
import com.example.demo.Query;
import com.example.demo.exception.ClassNotFoundException;
import com.example.demo.model.CReview;
import com.example.demo.model.Class;
import com.example.demo.model.Member;
import com.example.demo.repository.CReviewRepository;
import com.example.demo.repository.ClassRepository;
import com.example.demo.repository.MemberRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetReviewsByClassId implements Query<Long, List<CReviewSummaryDTO>> {

    private final CReviewRepository cReviewRepository;
    private final MemberRepository memberRepository;
    private final ClassRepository classRepository;

    public GetReviewsByClassId(CReviewRepository cReviewRepository, MemberRepository memberRepository, ClassRepository classRepository) {
        this.cReviewRepository = cReviewRepository;
        this.memberRepository = memberRepository;
        this.classRepository = classRepository;
    }

    @Override
    public ResponseEntity<List<CReviewSummaryDTO>> execute(Long id) {
        List<CReview> cReviews = cReviewRepository.findByAClass_Id(id);
        List<CReviewSummaryDTO> reviewDTOs = cReviews.stream().map(cReview -> {
            String userName = memberRepository.findById(cReview.getMember().getId())
                    .map(Member::getUserName)
                    .orElse("Unknown");
            String className = classRepository.findById(cReview.getaClass().getId())
                    .map(Class::getClassName)
                    .orElse("Unknown");
            return new CReviewSummaryDTO(cReview, userName, className);
        }).toList();

        return ResponseEntity.ok().body(reviewDTOs);
    }
}
