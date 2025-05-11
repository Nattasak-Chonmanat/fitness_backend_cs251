package com.example.demo.service.employee;

import com.example.demo.DTOs.EmployeeDTO;
import com.example.demo.Query;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetEmployeesService implements Query<Void, List<EmployeeDTO>> {

    private final EmployeeRepository employeeRepository;

    public GetEmployeesService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public ResponseEntity<List<EmployeeDTO>> execute(Void input) {
        return ResponseEntity.ok().body(employeeRepository.findAll().stream().map(EmployeeDTO::new).toList());
    }
}
