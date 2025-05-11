package com.example.demo.service.aClass;

import com.example.demo.DTOs.ClassDTO;
import com.example.demo.model.Class;
import com.example.demo.model.Response;
import com.example.demo.repository.ClassRepository;
import com.example.demo.UpdateCommand;
import com.example.demo.exception.ClassNotFoundException;
import com.example.demo.exception.ClassUpdateRequestNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class UpdateClassService implements UpdateCommand<Long, Class, Response> {

    private final ClassRepository classRepository;

    public UpdateClassService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    @Override
    public ResponseEntity<Response> execute(Long id, Class c) {
        Class aClass = classRepository.findById(id).orElseThrow( () -> new ClassNotFoundException(id));
        if(c.getRating() != null) throw new ClassUpdateRequestNotValidException("Rating");
        aClass.setId(id);
        aClass.setClassName(c.getClassName());
        aClass.setSchedule(c.getSchedule());
        aClass.setCapacity(c.getCapacity());
        classRepository.save(aClass);
        return ResponseEntity.ok().body(new Response("Update class successful.", HttpStatus.OK));
    }
}
