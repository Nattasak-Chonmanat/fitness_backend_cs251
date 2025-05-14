package com.example.demo.service.member;

import com.example.demo.model.MemberLoginResponse;
import com.example.demo.model.Member;
import com.example.demo.model.MemberLoginRequest;
import com.example.demo.Query;
import com.example.demo.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginMemberService implements Query<MemberLoginRequest, MemberLoginResponse> {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginMemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public ResponseEntity<MemberLoginResponse> execute(MemberLoginRequest loginRequest) {
        Optional<Member> optionalMember = memberRepository.findByUserName(loginRequest.getUserName());
        if(optionalMember.isPresent()) {
            Member member = optionalMember.get();
            boolean isMatch = passwordEncoder.matches(loginRequest.getPassword(), member.getPassword());
            if(isMatch) {
                return ResponseEntity.ok().body(new MemberLoginResponse("Login successful", HttpStatus.OK, member));
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MemberLoginResponse("Invalid username or password"
        , HttpStatus.UNAUTHORIZED, null));
    }
}
