package com.example.demo.model;

public class CreateBookingRequest {
    private Long memberId;
    private Long classId;

    public CreateBookingRequest() {
    }

    public CreateBookingRequest(Long memberId, Long classId) {
        this.memberId = memberId;
        this.classId = classId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }
}
