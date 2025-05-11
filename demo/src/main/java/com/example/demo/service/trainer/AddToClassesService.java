package com.example.demo.service.trainer;

import com.example.demo.Command;
import com.example.demo.DTOs.TrainerDTO;
import com.example.demo.exception.ErrorMessages;
import com.example.demo.model.Class;
import com.example.demo.model.Intendant;
import com.example.demo.model.Response;
import com.example.demo.model.Trainer;
import com.example.demo.repository.ClassRepository;
import com.example.demo.repository.TrainerRepository;
import com.example.demo.exception.ClassNotFoundException;
import com.example.demo.exception.TrainerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class AddToClassesService implements Command<Intendant, Response> {

    private final TrainerRepository trainerRepository;
    private final ClassRepository classRepository;

    public AddToClassesService(TrainerRepository trainerRepository, ClassRepository classRepository) {
        this.trainerRepository = trainerRepository;
        this.classRepository = classRepository;
    }


    @Override
    public ResponseEntity<Response> execute(Intendant intendance) {
        Trainer trainer = trainerRepository.findById(intendance.getTrainerId()).orElseThrow((
                ) -> new TrainerNotFoundException(intendance.getTrainerId())
        );
        Class aClass = classRepository.findById(intendance.getClassId()).orElseThrow(
                () -> new ClassNotFoundException(intendance.getClassId())
        );

        if (trainerRepository.existsTrainerInClass(trainer.getId(), aClass.getId()) > 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(
                    "Trainer is already assigned to this class.", HttpStatus.BAD_REQUEST));
        } else {
            trainer.getClasses().add(aClass);
            trainerRepository.save(trainer);
            return ResponseEntity.ok().body(new Response("Assign trainer to class successful.", HttpStatus.OK));
        }
    }
}
