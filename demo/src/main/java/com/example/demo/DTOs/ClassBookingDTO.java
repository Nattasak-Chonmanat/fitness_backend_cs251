package com.example.demo.DTOs;

import com.example.demo.model.ClassBooking;

import java.time.LocalDateTime;

public class ClassBookingDTO {
    private Long bookingId;
    private LocalDateTime bookingDate;
    private String status;
    private Long userId;
    private Long classId;
    private String className;

    public ClassBookingDTO(ClassBooking booking) {
        this.bookingId = booking.getBookingId();
        this.bookingDate  = booking.getBookingDate();
        this.status = booking.getStatus();
        this.userId = booking.getMember().getId();
        this.classId = booking.getaClass().getId();
        this.className = booking.getaClass().getClassName();
    }

    public ClassBookingDTO() {
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Long getBookingId() {
        return bookingId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    // getters and setters
}