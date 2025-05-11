package com.example.demo.service.aClass;

import com.example.demo.Command;
import com.example.demo.model.Class;
import com.example.demo.model.Response;
import com.example.demo.model.Trainer;
import com.example.demo.repository.CReviewRepository;
import com.example.demo.repository.ClassRepository;
import com.example.demo.exception.ClassNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteClassByIdService implements Command<Long, Response> {

    private final ClassRepository classRepository;
    private final CReviewRepository cReviewRepository;

    public DeleteClassByIdService(ClassRepository classRepository, CReviewRepository cReviewRepository) {
        this.classRepository = classRepository;
        this.cReviewRepository = cReviewRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<Response> execute(Long classId) {
       Class aClass = classRepository.findById(classId).orElseThrow(() -> new ClassNotFoundException(classId));

       for (Trainer trainer : aClass.getTrainers()) {
            trainer.getClasses().remove(aClass);
       }
       cReviewRepository.deleteByAClassId(aClass.getId());
       classRepository.delete(aClass);
       classRepository.delete(aClass);
       return ResponseEntity.ok().body(new Response("Delete class successful.", HttpStatus.OK));
    }
}
