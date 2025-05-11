package com.example.demo.DTOs;

import com.example.demo.model.Branch;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class BranchSummaryDTO {

    @Schema(hidden = true)
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

    public BranchSummaryDTO(Branch b) {
        this.id = b.getId();
        this.location = b.getLocation();
        this.branchName = b.getBranchName();
        this.managerId = b.getManager() != null ? b.getManager().getId() : null;
        this.contactNumber = b.getContactNumber();
        this.operationHours = b.getOperationHours();
    }

    public BranchSummaryDTO() {
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
