package com.example.demo.repository;

import com.example.demo.model.TRating;
import com.example.demo.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TRatingRepository extends JpaRepository<TRating, Long> {

    @Query("SELECT t FROM TRating t WHERE t.memberId.id = :memberId")
    List<TRating> findByMemberId(@Param("memberId") Long memberId);

    @Query("SELECT t FROM TRating t WHERE t.trainerId.id = :trainerId")
    List<TRating> findByTrainerId(@Param("trainerId") Long trainerId);

    List<TRating> findByTrainerId(Trainer trainer);
}
