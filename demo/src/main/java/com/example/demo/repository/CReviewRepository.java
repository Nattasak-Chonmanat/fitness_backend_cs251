package com.example.demo.repository;

import com.example.demo.model.CReview;
import com.example.demo.model.Class;
import com.example.demo.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CReviewRepository extends JpaRepository<CReview, Long> {

    List<CReview> findByMember_Id(Long memberId);

    List<CReview> findByMember_IdAndAClass_Id(Long memberId, Long classId);

    @Modifying
    @Transactional
    @Query("DELETE FROM CReview c WHERE c.aClass.id = :classId")
    void deleteByAClassId(@Param("classId") Long classId);

    List<CReview> findByAClass_Id(Long classId);
}

