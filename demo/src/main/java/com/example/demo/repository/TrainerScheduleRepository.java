package com.example.demo.repository;

import com.example.demo.model.TrainerSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainerScheduleRepository extends JpaRepository<TrainerSchedule, Long> {

    List<TrainerSchedule> findByTrainer_Id(Long trainerId);
}
