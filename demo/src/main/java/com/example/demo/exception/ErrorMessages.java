package com.example.demo.exception;

public enum ErrorMessages {
    CLASS_NOT_FOUND("Class not found with an ID: "),
    BRANCH_NOT_FOUND("Branch not found with an ID: "),
    TRAINER_NOT_FOUND("Trainer not found with an ID: "),
    MEMBER_NOT_FOUND("Member not found with an ID: "),
    REQUEST_INVALID(" field can not be updated."),
    PASSWORD_INVALID("Pass word must be at least 8 characters long and not blank."),
    BOOKING_ALREADY_EXIST("Booking is already exist."),
    BOOKING_NOT_FOUND("Booking not found with an ID: "),
    REVIEW_NOT_FOUND("Review not found with an ID: "),
    EMPLOYEE_NOT_FOUND("Employee not found with an ID: "),
    MEMBERSHIP_NOT_FOUND("Membership plan not found with an ID: "),
    ATTENDANCE_NOT_FOUND("Attendance not found with an ID: "),
    SCHEDULE_NOT_FOUND("Schedule not found with an ID: "),
    PAYMENT_NOT_FOUND("Payment not found with an ID: ");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {return this.message;}

}
