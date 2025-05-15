package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member", uniqueConstraints = @UniqueConstraint(columnNames = "userName"))
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(hidden = true)
    private Long id;

    @Column(name = "userName", unique = true, nullable = false)
    @NotBlank(message = "User name is required.")
    @Schema(description = "Username for new member.", example = "ไม่บอก")
    private String userName;

    @Column(name = "fname", nullable = false)
    @NotBlank(message = "First name must not be blank or Null")
    @Schema(description = "First name for new member.", example = "สมชาย")
    private String fname;

    @Column(name = "lname", nullable = false)
    @NotBlank(message = "Last name must not be blank or Null")
    @Schema(description = "Last name for new member", example = "ใจถึงพึ่งได้")
    private String lname;

    @Column(name = "email", nullable = false)
    @Email
    @NotBlank(message = "Email must not be blank or Null")
    @Schema(description = "Email for new member", example = "Somchai@gmail.com")
    private String email;

    @Column(name = "password")
    @Size(min = 8, message = "Password must be at least 8 characters long.")
    @NotBlank(message = "Password must not be blank or Null")
    @Schema(description = "Password for new member", example = "password123 (ไม่ต้อง hash มาเดี๋ยว backend ทําให้)")
    private String password;

    @Column(name = "phoneNumber", nullable = false)
    @NotBlank(message = "Phone number must not be blank or Null")
    @Schema(description = "Phone number for new member.", example = "123-456-7890")
    private String phoneNumber;

    @Column(name = "address")
    @NotBlank(message = "Address must not be blank or Null")
    @Schema(description = "Address for new member.", example = "ดาวอังคาร")
    private String address;

    @Column(name = "memberStatus")
    @Schema(hidden = true)
    private String memberStatus = null;

    @Column(name = "regisDate")
    @Schema(hidden = true)
    private LocalDate regisDate = null;

    @Column(name = "expireDate")
    @Schema(hidden = true)
    private LocalDate expireDate = null;

    @Column(name = "planName")
    @Schema(hidden = true)
    private String planName = null;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "membershipPlan",
            foreignKey = @ForeignKey(name = "fk_member_plan", foreignKeyDefinition = "FOREIGN KEY (membership_plan) REFERENCES membership_plan(id) ON DELETE SET NULL")
    )
    @Schema(hidden = true)
    private MembershipPlan membershipPlan = null;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(hidden = true)
    private List<Attendance> attendances = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(hidden = true)
    private List<ClassBooking> classBookingList = new ArrayList<>();


    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(hidden = true)
    private List<CReview> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(hidden = true)
    private List<TRating> trainerRatings = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(hidden = true)
    @JsonIgnore
    private List<Payment> payments = new ArrayList<>();

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public Member() {
    }

    public Member(Long id, List<TRating> trainerRatings, List<ClassBooking> classBookingList,
                  List<CReview> reviews, List<Attendance> attendances, MembershipPlan membershipPlan,
                  String planName, LocalDate expireDate, LocalDate regisDate, String memberStatus,
                  String address, String phoneNumber, String password, String email, String lname,
                  String fname, String userName) {
        this.id = id;
        this.trainerRatings = trainerRatings;
        this.classBookingList = classBookingList;
        this.reviews = reviews;
        this.attendances = attendances;
        this.membershipPlan = membershipPlan;
        this.planName = planName;
        this.expireDate = expireDate;
        this.regisDate = regisDate;
        this.memberStatus = memberStatus;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.email = email;
        this.lname = lname;
        this.fname = fname;
        this.userName = userName;
    }

    public List<TRating> getTrainerRatings() {
        return trainerRatings;
    }

    public void setTrainerRatings(List<TRating> trainerRatings) {
        this.trainerRatings = trainerRatings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public List<ClassBooking> getClassBookingList() {
        return classBookingList;
    }

    public void setClassBookingList(List<ClassBooking> classBookingList) {
        this.classBookingList = classBookingList;
    }

    public List<CReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<CReview> reviews) {
        this.reviews = reviews;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(String memberStatus) {
        this.memberStatus = memberStatus;
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

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public MembershipPlan getMembershipPlan() {
        return membershipPlan;
    }

    public void setMembershipPlan(MembershipPlan membershipPlan) {
        this.membershipPlan = membershipPlan;
    }
}
