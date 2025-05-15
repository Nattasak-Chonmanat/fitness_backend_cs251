package com.example.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UpdateMemberRequest {


    @NotBlank(message = "First name must not be blank or Null")
    @Schema(description = "First name for new member.", example = "สมชาย")
    private String fname;

    @NotBlank(message = "Last name must not be blank or Null")
    @Schema(description = "Last name for new member", example = "ใจถึงพึ่งได้")
    private String lname;

    @Email
    @NotBlank(message = "Email must not be blank or Null")
    @Schema(description = "Email for new member", example = "Somchai@gmail.com")
    private String email;



    @NotBlank(message = "Phone number must not be blank or Null")
    @Schema(description = "Phone number for new member.", example = "123-456-7890")
    private String phoneNumber;

    @NotBlank(message = "Address must not be blank or Null")
    @Schema(description = "Address for new member.", example = "ดาวอังคาร")
    private String address;

    public @NotBlank(message = "First name must not be blank or Null") String getFname() {
        return fname;
    }

    public void setFname(@NotBlank(message = "First name must not be blank or Null") String fname) {
        this.fname = fname;
    }

    public @NotBlank(message = "Address must not be blank or Null") String getAddress() {
        return address;
    }

    public void setAddress(@NotBlank(message = "Address must not be blank or Null") String address) {
        this.address = address;
    }

    public @NotBlank(message = "Phone number must not be blank or Null") String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotBlank(message = "Phone number must not be blank or Null") String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public @Email @NotBlank(message = "Email must not be blank or Null") String getEmail() {
        return email;
    }

    public void setEmail(@Email @NotBlank(message = "Email must not be blank or Null") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Last name must not be blank or Null") String getLname() {
        return lname;
    }

    public void setLname(@NotBlank(message = "Last name must not be blank or Null") String lname) {
        this.lname = lname;
    }
}
