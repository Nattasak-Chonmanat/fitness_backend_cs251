package com.example.demo.service.employee;

import com.example.demo.Command;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.model.Response;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteEmployeeService implements Command<Long, Response> {

    private final EmployeeRepository employeeRepository;

    public DeleteEmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public ResponseEntity<Response> execute(Long id) {
        if(!employeeRepository.existsById(id)) throw new EmployeeNotFoundException(id);
        employeeRepository.deleteById(id);
        return ResponseEntity.ok().body(new Response("Delete employee successful.", HttpStatus.OK));
    }
}
