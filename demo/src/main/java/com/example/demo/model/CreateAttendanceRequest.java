package com.example.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class CreateAttendanceRequest {

    @Schema(hidden = true)
    private Long attendanceId;

    @NotNull(message = "Member id is required.")
    @Schema(examples = "101")
    private Long memberId;

    @NotNull(message = "Branch id is required.")
    @Schema(examples = "2")
    private Long branchId;

    @Schema(hidden = true)
    private LocalDateTime checkInTime;

    @Schema(hidden = true)
    private LocalDateTime checkOutTime;

    public CreateAttendanceRequest(Long attendanceId) {
        this.attendanceId = attendanceId;
    }

    public Long getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Long attendanceId) {
        this.attendanceId = attendanceId;
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(LocalDateTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(LocalDateTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public @NotNull(message = "Branch id is required.") Long getBranchId() {
        return branchId;
    }

    public void setBranchId(@NotNull(message = "Branch id is required.") Long branchId) {
        this.branchId = branchId;
    }

    public @NotNull(message = "Member id is required.") Long getMemberId() {
        return memberId;
    }

    public void setMemberId(@NotNull(message = "Member id is required.") Long memberId) {
        this.memberId = memberId;
    }
}
