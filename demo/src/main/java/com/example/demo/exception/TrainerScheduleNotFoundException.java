package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TrainerScheduleNotFoundException extends RuntimeException {
  public TrainerScheduleNotFoundException(Long id) {
    super(ErrorMessages.SCHEDULE_NOT_FOUND.getMessage() + id);
  }
}
