package com.example.demo.service.trainer;

import com.example.demo.DTOs.TrainerDTO;
import com.example.demo.model.Response;
import com.example.demo.model.Trainer;
import com.example.demo.repository.TrainerRepository;
import com.example.demo.UpdateCommand;
import com.example.demo.exception.TrainerNotFoundException;
import com.example.demo.exception.TrainerInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@Service
public class UpdateTrainerService implements UpdateCommand<Long, Trainer, Response> {

    private final TrainerRepository trainerRepository;

    @Autowired
    public UpdateTrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Override
    public ResponseEntity<Response> execute(Long id, Trainer t) {
        Trainer trainer = trainerRepository.findById(id).orElseThrow(
                () -> new TrainerNotFoundException(id)
        );
        trainer.setId(id);
        if(t.getFullName() != null) trainer.setFullName(t.getFullName());
        if(t.getBankAccount() != null) trainer.setBankAccount(t.getBankAccount());
        if(t.getBranch() != null) trainer.setBranch(t.getBranch());
        if(t.getSalary() != null) trainer.setSalary(t.getSalary());
        if(t.getRole() != null) trainer.setRole(t.getRole());
        if(t.getSpeciality() != null) trainer.setSpeciality(t.getSpeciality());
        if(t.getRating() != null) throw new TrainerInvalidException("Rating field can not be updated.");
        if(!t.getClasses().isEmpty()) throw new TrainerInvalidException("Classes field can not be updated.");
        TrainerDTO trainerDTO = new TrainerDTO(trainerRepository.save(trainer));
        return ResponseEntity.status(HttpStatus.OK).body(new Response("Update trainer success", HttpStatus.OK));
    }
}
