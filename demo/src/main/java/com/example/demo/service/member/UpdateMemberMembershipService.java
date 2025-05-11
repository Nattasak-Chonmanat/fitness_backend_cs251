package com.example.demo.service.member;

import com.example.demo.UpdateCommand;
import com.example.demo.exception.MemberNotFoundException;
import com.example.demo.exception.MembershipNotFoundException;
import com.example.demo.model.Member;
import com.example.demo.model.MembershipPlan;
import com.example.demo.model.Response;
import com.example.demo.model.UpdateMemberMembershipRequest;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MembershipPlanRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UpdateMemberMembershipService implements UpdateCommand<Long, UpdateMemberMembershipRequest, Response> {

    private final MemberRepository memberRepository;
    private final MembershipPlanRepository membershipPlanRepository;

    public UpdateMemberMembershipService(MemberRepository memberRepository, MembershipPlanRepository membershipPlanRepository) {
        this.memberRepository = memberRepository;
        this.membershipPlanRepository = membershipPlanRepository;
    }

    @Override
    public ResponseEntity<Response> execute(Long id, UpdateMemberMembershipRequest request) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new MemberNotFoundException(id));
        MembershipPlan membershipPlan = membershipPlanRepository.findById(request.getPlanId()).orElseThrow(() -> new MembershipNotFoundException(request.getPlanId()));

        member.setMembershipPlan(membershipPlan);
        member.setMemberStatus("Active");
        member.setExpireDate(LocalDate.now().plusDays(membershipPlan.getDuration()));
        member.setPlanName(membershipPlan.getPlanName());
        memberRepository.save(member);
        return ResponseEntity.ok(new Response("Update member's plan successful.", HttpStatus.OK));
    }
}
