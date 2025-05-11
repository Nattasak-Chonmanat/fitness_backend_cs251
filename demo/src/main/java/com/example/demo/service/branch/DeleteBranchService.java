package com.example.demo.service.branch;

import com.example.demo.Command;
import com.example.demo.exception.BranchNotFoundException;
import com.example.demo.model.Branch;
import com.example.demo.model.Employee;
import com.example.demo.model.Response;
import com.example.demo.repository.BranchRepository;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteBranchService implements Command<Long, Response> {

    private final BranchRepository branchRepository;
    private final EmployeeRepository employeeRepository;

    public DeleteBranchService(BranchRepository branchRepository, EmployeeRepository employeeRepository) {
        this.branchRepository = branchRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public ResponseEntity<Response> execute(Long id) {
        Branch branch = branchRepository.findById(id).orElseThrow(() -> new BranchNotFoundException(id));
        List<Employee> employees = employeeRepository.findByBranch(branch);
        for (Employee e : employees) {
            e.setBranch(null);
        }
        employeeRepository.saveAll(employees); // save ทีเดียว

        branchRepository.delete(branch);
        return ResponseEntity.ok().body(new Response("Delete branch successful.", HttpStatus.OK));
    }
}
