package com.example.demo.service.aClass;


import com.example.demo.Command;
import com.example.demo.DTOs.ClassSummaryDTO;
import com.example.demo.exception.FieldNotAcceptException;
import com.example.demo.model.Class;
import com.example.demo.repository.ClassRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateClassService implements Command<Class, ClassSummaryDTO> {

    private final ClassRepository classRepository;

    public CreateClassService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    @Override
    public ResponseEntity<ClassSummaryDTO> execute(Class c) {
        if(!c.getClassBookingList().isEmpty()) throw new FieldNotAcceptException("Booking List");
        if(!c.getTrainers().isEmpty()) throw new FieldNotAcceptException("Trainer list");
        if(!c.getReviews().isEmpty()) throw new FieldNotAcceptException("Review list");
        if(c.getRating() != null) throw new FieldNotAcceptException("Rating");
        c.setRating(0.0f);
        ClassSummaryDTO classSummaryDTO = new ClassSummaryDTO(classRepository.save(c));
        return ResponseEntity.status(HttpStatus.CREATED).body(classSummaryDTO);
    }
}
