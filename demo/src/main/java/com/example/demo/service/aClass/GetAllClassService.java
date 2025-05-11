package com.example.demo.service.aClass;


import com.example.demo.DTOs.ClassSummaryDTO;
import com.example.demo.Query;
import com.example.demo.repository.ClassRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllClassService implements Query<Void, List<ClassSummaryDTO>> {

    private final ClassRepository classRepository;

    public GetAllClassService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }


    @Override
    public ResponseEntity<List<ClassSummaryDTO>> execute(Void input) {
        List<ClassSummaryDTO> classSummaryDTOS = classRepository.findAll().stream().map(ClassSummaryDTO::new).toList();
        return ResponseEntity.status(HttpStatus.OK).body(classSummaryDTOS);
    }
}
