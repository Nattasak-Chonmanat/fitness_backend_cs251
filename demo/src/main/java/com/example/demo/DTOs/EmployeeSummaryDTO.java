package com.example.demo.DTOs;

import com.example.demo.model.Employee;

public class EmployeeSummaryDTO {
    private Long id;
    private String fullName;
    private String role;

    public EmployeeSummaryDTO(Employee e) {
        this.id = e.getId();
        this.role = e.getRole();
        this.fullName = e.getFullName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
