package com.example.demo.service.membership;

import com.example.demo.Query;
import com.example.demo.UpdateCommand;
import com.example.demo.exception.MembershipNotFoundException;
import com.example.demo.model.MembershipPlan;
import com.example.demo.model.Response;
import com.example.demo.repository.MembershipPlanRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateMembershipPlanService implements UpdateCommand<Long, MembershipPlan, Response> {

    private final MembershipPlanRepository membershipPlanRepository;

    public UpdateMembershipPlanService(MembershipPlanRepository membershipPlanRepository) {
        this.membershipPlanRepository = membershipPlanRepository;
    }

    @Override
    public ResponseEntity<Response> execute(Long id, MembershipPlan m) {
        MembershipPlan  membershipPlan = membershipPlanRepository.findById(id).orElseThrow(() -> new MembershipNotFoundException(id));
        membershipPlan.setDescription(m.getDescription());
        membershipPlan.setDuration(m.getDuration());
        membershipPlan.setPrice(m.getPrice());
        membershipPlanRepository.save(membershipPlan);
        return ResponseEntity.ok().body(new Response("Update membership plan successful.", HttpStatus.OK));
    }

}
