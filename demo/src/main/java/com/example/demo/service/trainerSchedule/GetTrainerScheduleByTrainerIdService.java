package com.example.demo.service.trainerSchedule;

import com.example.demo.DTOs.TrainerScheduleDTO;
import com.example.demo.Query;
import com.example.demo.repository.TrainerScheduleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetTrainerScheduleByTrainerIdService implements Query<Long, List<TrainerScheduleDTO>> {

    private final TrainerScheduleRepository trainerScheduleRepository;

    public GetTrainerScheduleByTrainerIdService(TrainerScheduleRepository trainerScheduleRepository) {
        this.trainerScheduleRepository = trainerScheduleRepository;
    }

    @Override
    public ResponseEntity<List<TrainerScheduleDTO>> execute(Long id) {
        List<TrainerScheduleDTO> trainerScheduleDTOS = trainerScheduleRepository.findByTrainer_Id(id).stream().map(TrainerScheduleDTO::new).toList();
        return ResponseEntity.ok(trainerScheduleDTOS);
    }
}
