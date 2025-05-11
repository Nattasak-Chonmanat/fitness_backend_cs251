package com.example.demo.service.member;

import com.example.demo.DTOs.MemberDTO;
import com.example.demo.model.Member;
import com.example.demo.Query;
import com.example.demo.repository.MemberRepository;
import com.example.demo.exception.MemberNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetMemberByIdService implements Query<Long, MemberDTO> {

    private final MemberRepository memberRepository;

    public GetMemberByIdService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public ResponseEntity<MemberDTO> execute(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberNotFoundException(memberId));
        return ResponseEntity.status(HttpStatus.OK).body(new MemberDTO(member));
    }


}
