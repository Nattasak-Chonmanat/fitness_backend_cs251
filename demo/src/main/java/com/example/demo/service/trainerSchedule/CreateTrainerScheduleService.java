package com.example.demo.service.trainerSchedule;

import com.example.demo.Command;
import com.example.demo.DTOs.TrainerScheduleDTO;
import com.example.demo.exception.TrainerNotFoundException;
import com.example.demo.model.Trainer;
import com.example.demo.model.TrainerSchedule;
import com.example.demo.repository.TrainerRepository;
import com.example.demo.repository.TrainerScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class CreateTrainerScheduleService implements Command<TrainerScheduleDTO, TrainerScheduleDTO> {

    private final TrainerScheduleRepository trainerScheduleRepository;
    private final TrainerRepository trainerRepository;

    public CreateTrainerScheduleService(TrainerScheduleRepository trainerScheduleRepository, TrainerRepository trainerRepository) {
        this.trainerScheduleRepository = trainerScheduleRepository;
        this.trainerRepository = trainerRepository;
    }

    @Override
    public ResponseEntity<TrainerScheduleDTO> execute(TrainerScheduleDTO dto) {
        Trainer trainer = trainerRepository.findById(dto.getTrainerId()).orElseThrow(() -> new TrainerNotFoundException(dto.getTrainerId()));
        TrainerSchedule trainerSchedule = new TrainerSchedule();
        trainerSchedule.setWorkDate(LocalDate.now());
        trainerSchedule.setTrainer(trainer);
        trainerSchedule.setStartTime(LocalTime.now());
        trainerScheduleRepository.save(trainerSchedule);

        return ResponseEntity.status(HttpStatus.CREATED).body(new TrainerScheduleDTO(trainerSchedule));
    }
}
