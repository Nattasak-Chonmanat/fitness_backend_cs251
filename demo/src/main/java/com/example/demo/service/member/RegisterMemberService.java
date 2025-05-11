package com.example.demo.service.member;

import com.example.demo.Command;
import com.example.demo.DTOs.MemberDTO;
import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.exception.UsernameNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RegisterMemberService implements Command<Member, MemberDTO> {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterMemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<MemberDTO> execute(Member m) {
        if(memberRepository.findByUserName(m.getUserName()).isPresent()) {
            throw new UsernameNotValidException();
        }
        m.setRegisDate(LocalDate.now());
        String encodedPassword = passwordEncoder.encode(m.getPassword());
        m.setPassword(encodedPassword);
        memberRepository.save(m);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MemberDTO(m));
    }
}
