package com.example.demo.repository;

import com.example.demo.model.TRating;
import com.example.demo.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TRatingRepository extends JpaRepository<TRating, Long> {



    List<TRating> findByMember_Id(Long memberId);
    List<TRating> findByTrainer_Id(Long trainerId);
}
