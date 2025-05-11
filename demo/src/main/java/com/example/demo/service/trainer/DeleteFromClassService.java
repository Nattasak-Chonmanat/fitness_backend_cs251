package com.example.demo.service.trainer;

import com.example.demo.Command;
import com.example.demo.model.Class;
import com.example.demo.model.Intendant;
import com.example.demo.model.Response;
import com.example.demo.model.Trainer;
import com.example.demo.repository.ClassRepository;
import com.example.demo.repository.TrainerRepository;
import com.example.demo.exception.ClassNotFoundException;
import com.example.demo.exception.IntendantNotFoundException;
import com.example.demo.exception.TrainerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteFromClassService implements Command<Intendant, Response> {

    private final TrainerRepository trainerRepository;
    private final ClassRepository classRepository;

    public DeleteFromClassService(TrainerRepository trainerRepository, ClassRepository classRepository) {
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
        if(trainerRepository.existsTrainerInClass(intendance.getTrainerId(), intendance.getClassId()) > 0) {
            trainer.getClasses().remove(aClass);
            trainerRepository.save(trainer);
            String className = aClass.getClassName();
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Remove Trainer from " + className +
                    " successful.", HttpStatus.OK));
        }
        throw new IntendantNotFoundException(intendance.getTrainerId(), intendance.getClassId());
    }
}
