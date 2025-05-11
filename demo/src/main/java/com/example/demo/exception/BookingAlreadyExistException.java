package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookingAlreadyExistException extends RuntimeException {
  public BookingAlreadyExistException() {
    super(ErrorMessages.BOOKING_ALREADY_EXIST.getMessage());
  }
}
