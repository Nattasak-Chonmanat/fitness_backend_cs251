package com.example.demo.service.membership;

import com.example.demo.DTOs.MemberShipplanDTO;
import com.example.demo.Query;
import com.example.demo.exception.MembershipNotFoundException;
import com.example.demo.model.MembershipPlan;
import com.example.demo.repository.MembershipPlanRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetMembershipPlanByIdService implements Query<Long, MemberShipplanDTO> {

    private final MembershipPlanRepository membershipPlanRepository;

    public GetMembershipPlanByIdService(MembershipPlanRepository membershipPlanRepository) {
        this.membershipPlanRepository = membershipPlanRepository;
    }

    @Override
    public ResponseEntity<MemberShipplanDTO> execute(Long id) {
        MembershipPlan membershipPlan = membershipPlanRepository.findById(id).orElseThrow(() -> new MembershipNotFoundException(id));
        return ResponseEntity.ok(new MemberShipplanDTO(membershipPlan));
    }
}
