package com.example.demo.service.trainer;

import com.example.demo.Command;
import com.example.demo.DTOs.TrainerDTO;
import com.example.demo.exception.BranchNotFoundException;
import com.example.demo.exception.FieldNotAcceptException;
import com.example.demo.model.Branch;
import com.example.demo.model.Employee;
import com.example.demo.model.Trainer;
import com.example.demo.repository.BranchRepository;
import com.example.demo.repository.ClassRepository;
import com.example.demo.repository.TrainerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateTrainerService implements Command<Trainer, TrainerDTO> {

    private final TrainerRepository trainerRepository;
    private final BranchRepository branchRepository;

    public CreateTrainerService(TrainerRepository trainerRepository, BranchRepository branchRepository) {
        this.trainerRepository = trainerRepository;
        this.branchRepository = branchRepository;
    }

    @Override
    public ResponseEntity<TrainerDTO> execute(Trainer t) {
        t.setBranch(null);
        if(!t.getClasses().isEmpty()) throw new FieldNotAcceptException("Classes");
        if(!t.getTrainerRatings().isEmpty()) throw new FieldNotAcceptException("Trainer ratings");
        if(t.getRating() != null) throw new FieldNotAcceptException("Rating");
        if(t.getBranch() != null) {
            Branch branch = branchRepository.findById(t.getBranch().getId()).orElseThrow(() -> new BranchNotFoundException(
                    t.getBranch().getId()
            ));
            t.setBranch(branch);
        }
        t.setRating(0.0f);
        trainerRepository.save(t);
        return ResponseEntity.status(HttpStatus.CREATED).body(new TrainerDTO(t));
    }
}
