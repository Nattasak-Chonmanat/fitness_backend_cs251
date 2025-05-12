package com.example.demo.service.attendance;

import com.example.demo.Command;
import com.example.demo.exception.AttendanceNotFoundException;
import com.example.demo.exception.RequestInvalidException;
import com.example.demo.model.Attendance;
import com.example.demo.model.Response;
import com.example.demo.repository.AttendanceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class UpdateAttendanceService implements Command<Long, Response> {
    private final AttendanceRepository attendanceRepository;

    public UpdateAttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public ResponseEntity<Response> execute(Long id) {
        Attendance attendance = attendanceRepository.findById(id).orElseThrow(() -> new AttendanceNotFoundException(id));
        if(attendance.getCheckoutTime() == null) attendance.setCheckoutTime(LocalDateTime.now());
        else throw new RequestInvalidException("This member is already checkout.");
        attendanceRepository.save(attendance);
        return ResponseEntity.ok(new Response("Update attendance successful.", HttpStatus.OK));
    }

}
