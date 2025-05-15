package com.example.demo.repository;

import com.example.demo.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUserName(String userName);

    List<Member> findByMembershipPlan_Id(Long membershipPlanId);

    List<Member> findByExpireDate(LocalDate date);

    List<Member> findByExpireDateBefore(LocalDate date);

}
