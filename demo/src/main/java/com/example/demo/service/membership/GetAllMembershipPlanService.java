package com.example.demo.service.membership;

import com.example.demo.DTOs.MemberShipplanDTO;
import com.example.demo.Query;
import com.example.demo.repository.MembershipPlanRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllMembershipPlanService implements Query<Void, List<MemberShipplanDTO>> {

    private final MembershipPlanRepository membershipPlanRepository;

    public GetAllMembershipPlanService(MembershipPlanRepository membershipPlanRepository) {
        this.membershipPlanRepository = membershipPlanRepository;
    }

    @Override
    public ResponseEntity<List<MemberShipplanDTO>> execute(Void input) {
        List<MemberShipplanDTO> memberShipplanDTOS = membershipPlanRepository.findAll().stream()
                .map(MemberShipplanDTO::new).toList();
        return ResponseEntity.status(HttpStatus.OK).body(memberShipplanDTOS);
    }
}
