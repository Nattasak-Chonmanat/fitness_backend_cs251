package com.example.demo.service.trainer;

import com.example.demo.DTOs.TrainerDTO;
import com.example.demo.Query;
import com.example.demo.repository.TrainerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetTrainersService implements Query<Void, List<TrainerDTO>> {

    private final TrainerRepository trainerRepository;

    public GetTrainersService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Override
    public ResponseEntity<List<TrainerDTO>> execute(Void input) {
        List<TrainerDTO> trainerDTOS = trainerRepository.findAll().stream().map(TrainerDTO::new).toList();
        return ResponseEntity.status(HttpStatus.OK).body(trainerDTOS);
    }
}
