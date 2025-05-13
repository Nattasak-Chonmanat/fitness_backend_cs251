package com.example.demo.model;

import jakarta.validation.constraints.NotNull;

public class SetManagerRequest {

    @NotNull(message = "employeeId is required.")
    private Long employeeId;

    public @NotNull(message = "managerId is required.") Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(@NotNull(message = "managerId is required.") Long managerId) {
        this.employeeId = managerId;
    }
}
