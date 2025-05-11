package com.example.demo.DTOs;

import com.example.demo.model.Employee;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class EmployeeDTO {

    @Schema(description = "Id of employee.", example = "101")
    private Long id;

    @NotBlank(message = "Name is required.")
    @Schema(description = "Employee's full name.", example = "นางสมหมาย หายไปไหน")
    private String fullName;

    @NotBlank(message = "Role is required.")
    @Schema(description = "Employee's role", example = "Employee")
    private String role;

    @NotBlank(message = "You must provide a bank account.")
    @Schema(description = "Employee's bank account.", example = "123-456-789")
    private String bankAccount;

    @NotNull(message = "Salary is required.")
    @PositiveOrZero(message = "Salary can not be negative.")
    @Schema(description = "Employee's salary" , example = "200000")
    private Float salary;

    @Schema(description = "Branch's id that employee works on.", example = "102")
    private Long branchId;

    public EmployeeDTO(Employee e) {
        this.id = e.getId();
        this.fullName = e.getFullName();
        this.role = e.getRole();
        this.bankAccount = e.getBankAccount();
        this.salary = e.getSalary();
        this.branchId = e.getBranch() != null ? e.getBranch().getId() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public @NotNull(message = "Salary is required.") @PositiveOrZero(message = "Salary can not be negative.") Float getSalary() {
        return salary;
    }

    public void setSalary(@NotNull(message = "Salary is required.") @PositiveOrZero(message = "Salary can not be negative.") Float salary) {
        this.salary = salary;
    }

    public @NotBlank(message = "You must provide a bank account.") String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(@NotBlank(message = "You must provide a bank account.") String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public @NotBlank(message = "Role is required.") String getRole() {
        return role;
    }

    public void setRole(@NotBlank(message = "Role is required.") String role) {
        this.role = role;
    }

    public @NotBlank(message = "Name is required.") String getFullName() {
        return fullName;
    }

    public void setFullName(@NotBlank(message = "Name is required.") String fullName) {
        this.fullName = fullName;
    }
}
