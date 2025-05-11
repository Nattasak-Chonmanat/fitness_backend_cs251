package com.example.demo.service.employee;

import com.example.demo.Command;
import com.example.demo.DTOs.EmployeeDTO;
import com.example.demo.exception.BranchNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.model.Trainer;
import com.example.demo.repository.BranchRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.TrainerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateEmployeeService implements Command<Employee, EmployeeDTO> {

    private final EmployeeRepository employeeRepository;
    private final BranchRepository branchRepository;
    private final TrainerRepository trainerRepository;

    public CreateEmployeeService(EmployeeRepository employeeRepository, BranchRepository branchRepository, TrainerRepository trainerRepository) {
        this.employeeRepository = employeeRepository;
        this.branchRepository = branchRepository;
        this.trainerRepository = trainerRepository;
    }

    @Override
    public ResponseEntity<EmployeeDTO> execute(Employee employee) {
        if (employee.getBranch() != null) {
            Long branchId = employee.getBranch().getId();
            if (!branchRepository.existsById(branchId)) {
                throw new BranchNotFoundException(branchId);
            }
        }
        employeeRepository.save(employee);

        if ("trainer".equalsIgnoreCase(employee.getRole()) && employee instanceof Trainer) {
            trainerRepository.save((Trainer) employee);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new EmployeeDTO(employee));
    }

}
