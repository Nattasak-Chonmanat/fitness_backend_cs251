package com.example.demo.service.member;


import com.example.demo.DTOs.MemberDTO;
import com.example.demo.model.Member;
import com.example.demo.model.Response;
import com.example.demo.repository.MemberRepository;
import com.example.demo.UpdateCommand;
import com.example.demo.exception.MemberInvalidException;
import com.example.demo.exception.MemberNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UpdateMemberService implements UpdateCommand<Long, Member, Response> {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public UpdateMemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<Response> execute(Long memberId, Member m) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberNotFoundException(memberId));
        if(m.getRegisDate() != null) throw new MemberInvalidException("RegisDate");
        if(!m.getAttendances().isEmpty()) throw new MemberInvalidException("Attendance");
        if(m.getMembershipPlan() != null) throw new MemberInvalidException("MembershipPlan");
        if(!m.getClassBookingList().isEmpty()) throw new MemberInvalidException("Class booking");
        if(m.getExpireDate() != null) throw new MemberInvalidException("Expire date");
        if(m.getPlanName() != null) throw new MemberInvalidException("Plan name");
        if(!m.getReviews().isEmpty()) throw new MemberInvalidException("Review");
        if(m.getMemberStatus() != null) throw new MemberInvalidException("Member Status");
        String encodedPassword = passwordEncoder.encode(m.getPassword());
        member.setPassword(encodedPassword);
        member.setAddress(m.getAddress());
        member.setUserName(m.getUserName());
        member.setFname(m.getFname());
        member.setLname(m.getLname());
        member.setEmail(m.getEmail());
        member.setPhoneNumber(m.getPhoneNumber());
        MemberDTO memberDTO = new MemberDTO(memberRepository.save(member));
        return ResponseEntity.status(HttpStatus.OK).body(new Response("Update member success", HttpStatus.OK));
    }
}
