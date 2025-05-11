package com.example.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "branch")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "branchName", nullable = false)
    @Schema(hidden = true)
    private String branchName;

    @Column(name = "location", nullable = false)
    @Schema(hidden = true)
    private String location;

    @Column(name = "contactNumber")
    @Schema(hidden = true)
    private String contactNumber;

    @Column(name = "operationHours")
    @Schema(hidden = true)
    private String operationHours;


    @Schema(hidden = true)
    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
    private List<Attendance> attendances = new ArrayList<>();


    @OneToMany(mappedBy = "branch", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = false)
    @Schema(hidden = true)
    private List<Employee> employees = new ArrayList<>();

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Schema(hidden = true)
    private Employee manager;

    public List<Employee> getEmployees() {
        return employees;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
