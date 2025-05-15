package com.example.demo.service.member;


import com.example.demo.DTOs.MemberDTO;
import com.example.demo.model.Member;
import com.example.demo.model.Response;
import com.example.demo.model.UpdateMemberRequest;
import com.example.demo.repository.MemberRepository;
import com.example.demo.UpdateCommand;
import com.example.demo.exception.MemberNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateMemberService implements UpdateCommand<Long, UpdateMemberRequest, Response> {

    private final MemberRepository memberRepository;

    public UpdateMemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public ResponseEntity<Response> execute(Long memberId, UpdateMemberRequest request) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberNotFoundException(memberId));
        member.setAddress(request.getAddress());
        member.setFname(request.getFname());
        member.setLname(request.getLname());
        member.setEmail(request.getEmail());
        member.setPhoneNumber(request.getPhoneNumber());
        memberRepository.save(member);
        return ResponseEntity.status(HttpStatus.OK).body(new Response("Update member success", HttpStatus.OK));
    }
}
