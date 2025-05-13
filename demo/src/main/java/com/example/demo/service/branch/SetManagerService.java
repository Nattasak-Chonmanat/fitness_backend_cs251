package com.example.demo.service.branch;

import com.example.demo.UpdateCommand;
import com.example.demo.exception.BranchNotFoundException;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.model.Branch;
import com.example.demo.model.Employee;
import com.example.demo.model.Response;
import com.example.demo.model.SetManagerRequest;
import com.example.demo.repository.BranchRepository;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class SetManagerService implements UpdateCommand<Long, SetManagerRequest, Response> {

    private final BranchRepository branchRepository;
    private final EmployeeRepository employeeRepository;

    public SetManagerService(BranchRepository branchRepository, EmployeeRepository employeeRepository) {
        this.branchRepository = branchRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public ResponseEntity<Response> execute(Long id, SetManagerRequest request) {
        Branch branch = branchRepository.findById(id).orElseThrow(()-> new BranchNotFoundException(id));
        Employee employee = employeeRepository.findById(request.getEmployeeId()).orElseThrow(()-> new EmployeeNotFoundException(request.getEmployeeId()));

        employee.setBranch(branch);
        employee.setRole("Manager");
        branch.getEmployees().add(employee);
        branch.setManager(employee);
        branchRepository.save(branch);
        employeeRepository.save(employee);

        return ResponseEntity.ok(new Response("Set manger successful.", HttpStatus.OK));
    }
}
