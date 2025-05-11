package com.example.demo.service.aClass;


import com.example.demo.DTOs.ClassDTO;
import com.example.demo.model.Class;
import com.example.demo.Query;
import com.example.demo.repository.ClassRepository;
import com.example.demo.exception.ClassNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetClassByIdService implements Query<Long, ClassDTO> {

    private final ClassRepository classRepository;

    public GetClassByIdService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    @Override
    public ResponseEntity<ClassDTO> execute(Long id) {
        Class aClass = classRepository.findById(id).orElseThrow( () -> new ClassNotFoundException(id));
        return ResponseEntity.status(HttpStatus.OK).body(new ClassDTO(aClass));
    }
}
