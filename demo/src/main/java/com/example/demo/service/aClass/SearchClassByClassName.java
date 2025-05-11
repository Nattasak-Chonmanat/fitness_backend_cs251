package com.example.demo.service.aClass;

import com.example.demo.DTOs.ClassSummaryDTO;
import com.example.demo.Query;
import com.example.demo.repository.ClassRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchClassByClassName implements Query<String, List<ClassSummaryDTO>> {

    private final ClassRepository classRepository;

    public SearchClassByClassName(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    @Override
    public ResponseEntity<List<ClassSummaryDTO>> execute(String className) {
        List<ClassSummaryDTO> classDTOS = classRepository.findByClassNameContaining(className).stream().map(ClassSummaryDTO::new).toList();
        return ResponseEntity.ok().body(classDTOS);
    }
}
