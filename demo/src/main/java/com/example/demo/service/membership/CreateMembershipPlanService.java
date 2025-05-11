package com.example.demo.service.membership;

import com.example.demo.Command;
import com.example.demo.DTOs.MemberShipplanDTO;
import com.example.demo.model.MembershipPlan;
import com.example.demo.repository.MembershipPlanRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateMembershipPlanService implements Command<MembershipPlan, MemberShipplanDTO> {

    private final MembershipPlanRepository membershipPlanRepository;

    public CreateMembershipPlanService(MembershipPlanRepository membershipPlanRepository) {
        this.membershipPlanRepository = membershipPlanRepository;
    }

    @Override
    public ResponseEntity<MemberShipplanDTO> execute(MembershipPlan m) {
        membershipPlanRepository.save(m);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MemberShipplanDTO(m));
    }
}
