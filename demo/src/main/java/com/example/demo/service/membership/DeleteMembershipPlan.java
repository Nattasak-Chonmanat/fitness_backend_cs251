package com.example.demo.service.membership;

import com.example.demo.Command;
import com.example.demo.exception.MembershipNotFoundException;
import com.example.demo.model.Member;
import com.example.demo.model.MembershipPlan;
import com.example.demo.model.Response;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MembershipPlanRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@Service
public class DeleteMembershipPlan implements Command<Long, Response> {

    private final MembershipPlanRepository membershipPlanRepository;
    private final MemberRepository memberRepository;

    public DeleteMembershipPlan(MembershipPlanRepository membershipPlanRepository, MemberRepository memberRepository) {
        this.membershipPlanRepository = membershipPlanRepository;
        this.memberRepository = memberRepository;
    }


    @Override
    public ResponseEntity<Response> execute(Long id) {
        MembershipPlan membershipPlan = membershipPlanRepository.findById(id).orElseThrow(() -> new MembershipNotFoundException(id));
        for (Member member : memberRepository.findByMembershipPlan_Id(id)) {
            member.setPlanName(null);
            member.setMemberStatus("Inactive");
            member.setExpireDate(null);
            member.setMembershipPlan(null);
            memberRepository.save(member);
        }
        membershipPlanRepository.deleteById(id);
        return ResponseEntity.ok(new Response("Delete membership plan success.", HttpStatus.OK));
    }
}
