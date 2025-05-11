package com.example.demo.service.branch;

import com.example.demo.Command;
import com.example.demo.DTOs.BranchDTO;
import com.example.demo.DTOs.BranchSummaryDTO;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.model.Branch;
import com.example.demo.model.Employee;
import com.example.demo.repository.BranchRepository;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateBranchService implements Command<BranchSummaryDTO, BranchDTO> {
    private final BranchRepository branchRepository;
    private final EmployeeRepository employeeRepository;

    public CreateBranchService(BranchRepository branchRepository, EmployeeRepository employeeRepository) {
        this.branchRepository = branchRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public ResponseEntity<BranchDTO> execute(BranchSummaryDTO branchSummaryDTO) {
        Branch branch = new Branch();
        branch.setBranchName(branchSummaryDTO.getBranchName());
        branch.setLocation(branchSummaryDTO.getLocation());
        branch.setOperationHours(branchSummaryDTO.getOperationHours());
        branch.setContactNumber(branchSummaryDTO.getContactNumber());
        if(branchSummaryDTO.getManagerId() != null) {
            Employee employee = employeeRepository.findById(branchSummaryDTO.getManagerId()).orElseThrow(() ->
                    new EmployeeNotFoundException(branchSummaryDTO.getManagerId()));
            branch.setManager(employee);
            branch.getEmployees().add(employee);
            employee.setBranch(branch);
        }
        branchRepository.save(branch);
        return ResponseEntity.status(HttpStatus.CREATED).body(new BranchDTO(branch));
    }
}
