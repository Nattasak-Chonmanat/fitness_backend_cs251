package com.example.demo.service.branch;

import com.example.demo.DTOs.BranchSummaryDTO;
import com.example.demo.UpdateCommand;
import com.example.demo.exception.BranchNotFoundException;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.model.Branch;
import com.example.demo.model.Employee;
import com.example.demo.model.Response;
import com.example.demo.repository.BranchRepository;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateBranchService implements UpdateCommand<Long, BranchSummaryDTO, Response> {

    private final BranchRepository branchRepository;
    private final EmployeeRepository employeeRepository;


    public UpdateBranchService(BranchRepository branchRepository, EmployeeRepository employeeRepository) {
        this.branchRepository = branchRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public ResponseEntity<Response> execute(Long id, BranchSummaryDTO branchSummaryDTO) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new BranchNotFoundException(id));

        if(branchSummaryDTO.getManagerId() != null) {
            Employee employee = employeeRepository.findById(branchSummaryDTO.getManagerId())
                    .orElseThrow(() -> new EmployeeNotFoundException(branchSummaryDTO.getManagerId()));
            branch.setManager(employee);
            employee.setBranch(branch);
        } else {
            branch.setManager(null);
        }

        branch.setBranchName(branchSummaryDTO.getBranchName());
        branch.setContactNumber(branchSummaryDTO.getContactNumber());
        branch.setLocation(branchSummaryDTO.getLocation());
        branch.setOperationHours(branchSummaryDTO.getOperationHours());

        branchRepository.save(branch);
        return ResponseEntity.ok().body(new Response("Update branch successful.", HttpStatus.OK));
    }

}
