package com.example.demo;

import org.springframework.http.ResponseEntity;

public interface UpdateCommand<Long, I, O>{
    ResponseEntity<O> execute(Long id, I input);
}
