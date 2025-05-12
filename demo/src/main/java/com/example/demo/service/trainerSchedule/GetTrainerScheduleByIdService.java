package com.example.demo.service.trainerSchedule;

import com.example.demo.DTOs.TrainerScheduleDTO;
import com.example.demo.Query;
import com.example.demo.exception.TrainerScheduleNotFoundException;
import com.example.demo.model.TrainerSchedule;
import com.example.demo.repository.TrainerScheduleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetTrainerScheduleByIdService implements Query<Long, TrainerScheduleDTO> {

    private final TrainerScheduleRepository trainerScheduleRepository;

    public GetTrainerScheduleByIdService(TrainerScheduleRepository trainerScheduleRepository) {
        this.trainerScheduleRepository = trainerScheduleRepository;
    }

    @Override
    public ResponseEntity<TrainerScheduleDTO> execute(Long id) {
        TrainerSchedule trainerSchedule = trainerScheduleRepository.findById(id).orElseThrow(() -> new TrainerScheduleNotFoundException(id));
        return ResponseEntity.ok(new TrainerScheduleDTO(trainerSchedule));
    }
}
