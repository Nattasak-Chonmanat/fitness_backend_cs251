package com.example.demo.service.branch;

import com.example.demo.DTOs.BranchDTO;
import com.example.demo.DTOs.BranchSummaryDTO;
import com.example.demo.Query;
import com.example.demo.exception.BranchNotFoundException;
import com.example.demo.model.Branch;
import com.example.demo.repository.BranchRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetBranchByIdService implements Query<Long, BranchDTO> {

    private final BranchRepository branchRepository;


    public GetBranchByIdService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Override
    public ResponseEntity<BranchDTO> execute(Long id) {
        Branch branch = branchRepository.findById(id).orElseThrow(() -> new BranchNotFoundException(id));
        System.out.println(branch.getEmployees());
        return ResponseEntity.ok().body(new BranchDTO(branch));
    }
}
