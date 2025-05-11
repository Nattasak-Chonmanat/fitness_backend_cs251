package com.example.demo.DTOs;

import com.example.demo.model.Branch;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

public class BranchDTO {

    private Long id;

    @NotBlank(message = "Branch name is required.")
    private String branchName;

    private Long managerId;

    @NotBlank(message = "Location is required.")
    private String location;

    @NotBlank(message = "Contact number is required.")
    private String contactNumber;

    @NotBlank(message = "Operation hours is required.")
    private String operationHours;

    private List<EmployeeSummaryDTO> employees = new ArrayList<>();

    public BranchDTO(Branch b) {
        this.id = b.getId();
        this.location = b.getLocation();
        this.branchName = b.getBranchName();
        this.managerId = b.getManager() != null ? b.getManager().getId() : null;
        this.contactNumber = b.getContactNumber();
        this.operationHours = b.getOperationHours();
        this.employees = b.getEmployees().stream().map(EmployeeSummaryDTO::new).toList();
    }


    public List<EmployeeSummaryDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeSummaryDTO> employees) {
        this.employees = employees;
    }

    public String getOperationHours() {
        return operationHours;
    }

    public void setOperationHours(String operationHours) {
        this.operationHours = operationHours;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

}
