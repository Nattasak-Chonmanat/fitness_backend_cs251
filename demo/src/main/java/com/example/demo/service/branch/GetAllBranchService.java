package com.example.demo.service.branch;

import com.example.demo.DTOs.BranchSummaryDTO;
import com.example.demo.Query;
import com.example.demo.repository.BranchRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllBranchService implements Query<Void, List<BranchSummaryDTO>> {

    private final BranchRepository branchRepository;

    public GetAllBranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Override
    public ResponseEntity<List<BranchSummaryDTO>> execute(Void input) {
        List<BranchSummaryDTO> branchSummaryDTOS = branchRepository.findAll().stream().map(BranchSummaryDTO::new).toList();
        return ResponseEntity.ok().body(branchSummaryDTOS);
    }
}
