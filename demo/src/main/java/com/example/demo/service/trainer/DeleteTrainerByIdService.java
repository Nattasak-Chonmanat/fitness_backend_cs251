package com.example.demo.service.trainer;

import com.example.demo.Command;
import com.example.demo.model.Response;
import com.example.demo.model.Trainer;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.TrainerRepository;
import com.example.demo.exception.TrainerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteTrainerByIdService implements Command<Long, Response> {

    private final TrainerRepository trainerRepository;
    private final EmployeeRepository employeeRepository;

    public DeleteTrainerByIdService(TrainerRepository trainerRepository, EmployeeRepository employeeRepository) {
        this.trainerRepository = trainerRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public ResponseEntity<Response> execute(Long id) {
        Trainer trainer = trainerRepository.findById(id).orElseThrow(() -> new TrainerNotFoundException(id));
        trainer.getClasses().clear();
        trainer.getTrainerRatings().clear();

        trainerRepository.delete(trainer); // ลบจาก trainer table
        employeeRepository.delete(trainer); // ลบจาก employee table
        return ResponseEntity.ok().body(new Response("Delete trainer successful.", HttpStatus.OK));
    }
}
