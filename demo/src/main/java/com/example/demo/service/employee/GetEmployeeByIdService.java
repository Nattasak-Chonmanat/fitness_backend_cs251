package com.example.demo.service.employee;

import com.example.demo.DTOs.EmployeeDTO;
import com.example.demo.Query;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetEmployeeByIdService implements Query<Long, EmployeeDTO> {

    private final EmployeeRepository employeeRepository;

    public GetEmployeeByIdService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public ResponseEntity<EmployeeDTO> execute(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        return ResponseEntity.ok().body(new EmployeeDTO(employee));
    }
}
