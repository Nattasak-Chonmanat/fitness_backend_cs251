package com.example.demo.service.member;

import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UpdateMembershipAlreadyExpiredService {

    private final MemberRepository memberRepository;

    public UpdateMembershipAlreadyExpiredService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Scheduled(cron = "0 0 0 * * ?" , zone = "Asia/Bangkok")
    public void setMembershipScheduled() {}

    public void setMemberExpiredMembership() {
        List<Member> members = memberRepository.findByExpireDateBefore(LocalDate.now());
        for(Member member : members) {
            member.setMemberStatus("Inactive");
            member.setMembershipPlan(null);
            member.setExpireDate(null);
            member.setPlanName(null);
            memberRepository.save(member);
        }
    }


}
