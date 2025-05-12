package com.example.demo.service.trainerSchedule;

import com.example.demo.Command;
import com.example.demo.DTOs.TrainerScheduleDTO;
import com.example.demo.exception.TrainerScheduleNotFoundException;
import com.example.demo.model.Response;
import com.example.demo.model.TrainerSchedule;
import com.example.demo.repository.TrainerScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class UpdateTrainerScheduleService implements Command<Long, Response> {

    private final TrainerScheduleRepository trainerScheduleRepository;

    public UpdateTrainerScheduleService(TrainerScheduleRepository trainerScheduleRepository) {
        this.trainerScheduleRepository = trainerScheduleRepository;
    }

    @Override
    public ResponseEntity<Response> execute(Long id) {
        TrainerSchedule trainerSchedule = trainerScheduleRepository.findById(id).orElseThrow(() -> new TrainerScheduleNotFoundException(id));
        trainerSchedule.setEndTime(LocalTime.now());
        trainerScheduleRepository.save(trainerSchedule);

        return ResponseEntity.ok(new Response("Update schedule successful.", HttpStatus.OK));
    }
}
