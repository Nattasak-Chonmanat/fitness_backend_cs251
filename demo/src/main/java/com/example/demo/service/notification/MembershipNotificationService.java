package com.example.demo.service.notification;

import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MembershipNotificationService {

    private final MemberRepository memberRepository;
    private final EmailService emailService;

    public MembershipNotificationService(MemberRepository memberRepository, EmailService emailService) {
        this.memberRepository = memberRepository;
        this.emailService = emailService;
    }

    @Scheduled(cron = "0 0 8 * * ?", zone = "Asia/Bangkok")
    public void scheduledMembershipCheck() {
        notifyExpiringMemberships();
    }

    public void notifyExpiringMemberships() {
        LocalDate today = LocalDate.now();
        LocalDate targetDate = today.plusDays(7);

        List<Member> expiringMembers = memberRepository.findMembersExpiringSoon(today, targetDate);

        for (Member member : expiringMembers) {
            String subject = "แพ็คเกจสมาชิกของคุณกำลังจะหมดอายุ!";
            String text = String.format("สวัสดีคุณ %s %s,\n\nแพ็คเกจสมาชิกของคุณจะหมดอายุในวันที่ %s\n\nกรุณาต่ออายุเพื่อใช้งานได้อย่างต่อเนื่อง",
                    member.getFname(), member.getLname(), member.getExpireDate());

            emailService.sendEmail(member.getEmail(), subject, text);
        }
    }
}
