package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "classBooking")
public class ClassBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookingId")
    private Long bookingId;

    @Column(name = "bookingDate")
    private LocalDateTime bookingDate;

    @Column(name = "status")
    private String status = "Booked";

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    @NotNull(message = "MemberId is required.")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    @NotNull(message = "Class id is required.")
    private Class aClass;

    public Long getBookingId() {
        return bookingId;
    }


    public ClassBooking() {
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }


    @PrePersist
    public void prePersist() {
        if (bookingDate == null) {
            bookingDate = LocalDateTime.now();
        }
    }
}
