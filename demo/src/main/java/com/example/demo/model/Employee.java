package com.example.demo.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;


@Entity
@Table(name = "employee")
@Inheritance(strategy = InheritanceType.JOINED)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(hidden = true)
    private Long id;

    @Column(name = "fullName", nullable = false)
    @NotBlank(message = "Name is required.")
    @Schema(example = "นางสมหมาย หายไปไหน")
    private String fullName;

    @Column(name = "role", nullable = false)
    @NotBlank(message = "Role is required.")
    @Schema(example = "Employee")
    private String role;

    @Column(name = "bankAccount")
    @NotBlank(message = "You must provide a bank account.")
    @Schema(example = "123-456-789")
    private String bankAccount;

    @Column(name = "salary")
    @NotNull(message = "Salary is required.")
    @PositiveOrZero(message = "Salary can not be negative.")
    @Schema(example = "20000")
    private Float salary;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branchId) {
        this.branch = branchId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
