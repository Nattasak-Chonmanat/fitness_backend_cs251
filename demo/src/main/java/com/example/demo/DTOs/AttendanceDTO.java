package com.example.demo.DTOs;

import com.example.demo.model.Attendance;

import java.time.Duration;
import java.time.LocalDateTime;

public class AttendanceDTO {

    private Long id;
    private LocalDateTime checkinTime;
    private LocalDateTime checkoutTime;
    private String duration;

    public AttendanceDTO(Attendance a) {
        this.id = a.getAttendance_id();
        this.checkoutTime = a.getCheckoutTime();
        this.checkinTime = a.getCheckinTime();
        if(a.getCheckinTime() != null && a.getCheckoutTime() != null) {
            this.duration = Duration.between(a.getCheckinTime(), a.getCheckoutTime()).toMinutes() + " minutes";
        } else  {
            this.duration = Duration.ZERO.toMinutes() + " minutes";
        }
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCheckoutTime() {
        return checkoutTime;
    }

    public void setCheckoutTime(LocalDateTime checkoutTime) {
        this.checkoutTime = checkoutTime;
    }

    public LocalDateTime getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(LocalDateTime checkinTime) {
        this.checkinTime = checkinTime;
    }
}
