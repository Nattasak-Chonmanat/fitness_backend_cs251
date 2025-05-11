package com.example.demo.service.classBooking;

import com.example.demo.DTOs.ClassBookingDTO;
import com.example.demo.Query;
import com.example.demo.exception.MemberNotFoundException;
import com.example.demo.model.Member;
import com.example.demo.repository.ClassBookingRepository;
import com.example.demo.repository.MemberRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetBookingByMemberIdService implements Query<Long, List<ClassBookingDTO>> {

    private final ClassBookingRepository classBookingRepository;
    private final MemberRepository memberRepository;

    public GetBookingByMemberIdService(ClassBookingRepository classBookingRepository, MemberRepository memberRepository) {
        this.classBookingRepository = classBookingRepository;
        this.memberRepository = memberRepository;
    }


    @Override
    public ResponseEntity<List<ClassBookingDTO>> execute(Long id) {
        if(!memberRepository.existsById(id)) throw new MemberNotFoundException(id);
        List<ClassBookingDTO> classBookingDTOS = classBookingRepository.findByMemberId(id).stream().map(ClassBookingDTO::new).toList();
        return ResponseEntity.ok().body(classBookingDTOS);
    }
}
