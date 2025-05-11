package com.example.demo.repository;

import com.example.demo.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {

    @Query("SELECT COUNT(t) FROM Trainer t JOIN t.classes c WHERE t.id = :trainerId AND c.id = :classId")
    Long existsTrainerInClass(@Param("trainerId") Long trainerId, @Param("classId") Long classId);

}
