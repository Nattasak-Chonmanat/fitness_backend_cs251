package com.example.demo.service.attendance;

import com.example.demo.Command;
import com.example.demo.DTOs.AttendanceDTO;
import com.example.demo.exception.BranchNotFoundException;
import com.example.demo.exception.MemberNotFoundException;
import com.example.demo.model.Attendance;
import com.example.demo.model.Branch;
import com.example.demo.model.CreateAttendanceRequest;
import com.example.demo.model.Member;
import com.example.demo.repository.AttendanceRepository;
import com.example.demo.repository.BranchRepository;
import com.example.demo.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreateAttendanceService implements Command<CreateAttendanceRequest, AttendanceDTO> {

    private final AttendanceRepository attendanceRepository;
    private final MemberRepository memberRepository;
    private final BranchRepository branchRepository;

    public CreateAttendanceService(AttendanceRepository attendanceRepository, MemberRepository memberRepository, BranchRepository branchRepository) {
        this.attendanceRepository = attendanceRepository;
        this.memberRepository = memberRepository;
        this.branchRepository = branchRepository;
    }

    @Override
    public ResponseEntity<AttendanceDTO> execute(CreateAttendanceRequest request) {
        Member member = memberRepository.findById(request.getMemberId()).orElseThrow(() -> new MemberNotFoundException(request.getMemberId()));
        Branch branch = branchRepository.findById(request.getBranchId()).orElseThrow(() -> new BranchNotFoundException(request.getBranchId()));
        Attendance attendance = new Attendance();
        attendance.setBranch(branch);
        attendance.setMember(member);
        attendance.setCheckinTime(LocalDateTime.now());
        attendance.setCheckoutTime(null);
        attendanceRepository.save(attendance);
        return ResponseEntity.status(HttpStatus.CREATED).body(new AttendanceDTO(attendance));
    }
}
