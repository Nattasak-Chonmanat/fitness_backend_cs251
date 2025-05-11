package com.example.demo.repository;


import com.example.demo.model.ClassBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassBookingRepository extends JpaRepository<ClassBooking, Long> {


    Optional<ClassBooking> findByMemberIdAndAClassIdAndStatus(Long id, Long id1, String booked);
    List<ClassBooking> findByMemberId(Long id);
}
