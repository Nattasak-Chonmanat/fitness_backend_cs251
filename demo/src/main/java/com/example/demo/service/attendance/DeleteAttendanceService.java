package com.example.demo.service.attendance;


import com.example.demo.Command;
import com.example.demo.model.Response;
import com.example.demo.repository.AttendanceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteAttendanceService implements Command<Long, Response> {

    private final AttendanceRepository attendanceRepository;

    public DeleteAttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public ResponseEntity<Response> execute(Long id) {
        attendanceRepository.deleteById(id);
        return ResponseEntity.ok(new Response("Delete attendance successful.", HttpStatus.OK));
    }
}
