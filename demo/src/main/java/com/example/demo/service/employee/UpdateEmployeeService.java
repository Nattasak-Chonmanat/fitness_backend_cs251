package com.example.demo.service.employee;

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
public class UpdateEmployeeService implements UpdateCommand<Long, Employee, Response> {

    private final EmployeeRepository employeeRepository;
    private final BranchRepository branchRepository;

    public UpdateEmployeeService(EmployeeRepository employeeRepository, BranchRepository branchRepository) {
        this.employeeRepository = employeeRepository;
        this.branchRepository = branchRepository;
    }

    @Override
    public ResponseEntity<Response> execute(Long id, Employee employee) {
        if(!employeeRepository.existsById(id)) throw new EmployeeNotFoundException(id);
        if(employee.getBranch() != null) {
            Branch branch = branchRepository.findById(employee.getBranch().getId()).orElseThrow(() -> new BranchNotFoundException(employee.getBranch().getId()));
            employee.setBranch(branch);
        }
        employeeRepository.save(employee);
        return ResponseEntity.ok().body(new Response("Update employee successful.", HttpStatus.OK));
    }
}
