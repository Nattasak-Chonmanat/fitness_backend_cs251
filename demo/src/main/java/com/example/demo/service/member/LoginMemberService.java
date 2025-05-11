package com.example.demo.service.member;

import com.example.demo.model.Member;
import com.example.demo.model.MemberLoginRequest;
import com.example.demo.model.Response;
import com.example.demo.Query;
import com.example.demo.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginMemberService implements Query<MemberLoginRequest, Response> {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginMemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public ResponseEntity<Response> execute(MemberLoginRequest loginRequest) {
        Optional<Member> optionalMember = memberRepository.findByUserName(loginRequest.getUserName());
        if(optionalMember.isPresent()) {
            Member member = optionalMember.get();
            boolean isMatch = passwordEncoder.matches(loginRequest.getPassword(), member.getPassword());
            if(isMatch) {
                return ResponseEntity.ok().body(new Response("Login successful", HttpStatus.OK));
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Response("Invalid username or password"
        , HttpStatus.UNAUTHORIZED));
    }
}
