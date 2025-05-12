package com.example.demo.service.trainerSchedule;

import com.example.demo.Command;
import com.example.demo.exception.TrainerScheduleNotFoundException;
import com.example.demo.model.Response;
import com.example.demo.repository.TrainerScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteTrainerScheduleService implements Command<Long, Response> {

    private final TrainerScheduleRepository trainerScheduleRepository;

    public DeleteTrainerScheduleService(TrainerScheduleRepository trainerScheduleRepository) {
        this.trainerScheduleRepository = trainerScheduleRepository;
    }

    @Override
    public ResponseEntity<Response> execute(Long id) {
        if(!trainerScheduleRepository.existsById(id)) throw new TrainerScheduleNotFoundException(id);
        trainerScheduleRepository.deleteById(id);
        return ResponseEntity.ok(new Response("Delete schedule successful.", HttpStatus.OK));
    }
}
