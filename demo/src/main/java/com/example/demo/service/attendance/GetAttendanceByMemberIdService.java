package com.example.demo.service.attendance;


import com.example.demo.DTOs.AttendanceDTO;
import com.example.demo.Query;
import com.example.demo.exception.MemberNotFoundException;
import com.example.demo.repository.AttendanceRepository;
import com.example.demo.repository.MemberRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAttendanceByMemberIdService implements Query<Long, List<AttendanceDTO>> {

    private final AttendanceRepository attendanceRepository;
    private final MemberRepository memberRepository;

    public GetAttendanceByMemberIdService(AttendanceRepository attendanceRepository, MemberRepository memberRepository) {
        this.attendanceRepository = attendanceRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public ResponseEntity<List<AttendanceDTO>> execute(Long id) {
        if(!memberRepository.existsById(id)) throw new MemberNotFoundException(id);
        List<AttendanceDTO>  attendanceDTOS = attendanceRepository.findByMember_Id(id).stream().map(AttendanceDTO::new).toList();
        return ResponseEntity.ok(attendanceDTOS);
    }
}
