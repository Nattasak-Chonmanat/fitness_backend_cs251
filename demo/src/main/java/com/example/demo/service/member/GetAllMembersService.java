package com.example.demo.service.member;

import com.example.demo.DTOs.MemberDTO;
import com.example.demo.Query;
import com.example.demo.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllMembersService implements Query<Void, List<MemberDTO>> {
    private final MemberRepository memberRepository;

    public GetAllMembersService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public ResponseEntity<List<MemberDTO>> execute(Void input) {
        List<MemberDTO> memberDTOS = memberRepository.findAll().stream().map(MemberDTO::new).toList();
        return ResponseEntity.status(HttpStatus.OK).body(memberDTOS);
    }
}
