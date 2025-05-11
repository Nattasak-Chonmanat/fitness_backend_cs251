package com.example.demo.DTOs;

import com.example.demo.model.Member;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public class MemberDTO {
    private Long id;
    private String userName;
    private String fname;
    private String lname;
    private String email;
    private LocalDate regisDate;
    private LocalDate expireDate;
    private String address;
    private String planName;
    private String phoneNumber;

    public MemberDTO(Member m) {
        this.id = m.getId();
        this.fname = m.getFname();
        this.lname = m.getLname();
        this.email = m.getEmail();
        this.regisDate = m.getRegisDate();
        this.expireDate = m.getExpireDate();
        this.planName = m.getPlanName();
        this.userName = m.getUserName();
        this.address = m.getAddress();
        this.phoneNumber = m.getPhoneNumber();

    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }




    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public LocalDate getRegisDate() {
        return regisDate;
    }

    public void setRegisDate(LocalDate regisDate) {
        this.regisDate = regisDate;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
}
