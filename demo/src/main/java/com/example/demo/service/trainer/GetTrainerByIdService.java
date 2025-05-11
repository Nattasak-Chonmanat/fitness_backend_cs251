package com.example.demo.service.trainer;

import com.example.demo.DTOs.TrainerDTO;
import com.example.demo.model.Trainer;
import com.example.demo.Query;
import com.example.demo.repository.TrainerRepository;
import com.example.demo.exception.TrainerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetTrainerByIdService implements Query<Long, TrainerDTO> {

    private final TrainerRepository trainerRepository;

    public GetTrainerByIdService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Override
    public ResponseEntity<TrainerDTO> execute(Long id) {
        Optional<Trainer> trainer = trainerRepository.findById(id);
        if(trainer.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new TrainerDTO(trainer.get()));
        }
        throw new TrainerNotFoundException(id);
    }
}
