package com.example.demo.DTOs;

public class MemberSummaryDTO {
    private String userName;
    private String fname;
    private String lname;
    private String email;

    public MemberSummaryDTO() {
    }

    public MemberSummaryDTO(String email, String lname, String fname, String userName) {
        this.email = email;
        this.lname = lname;
        this.fname = fname;
        this.userName = userName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
