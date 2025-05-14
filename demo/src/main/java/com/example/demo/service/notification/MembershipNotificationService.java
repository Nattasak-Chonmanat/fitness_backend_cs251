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
        notifyExpiringInSevenDaysMemberships();
        notifyExpiringTodayMemberships();
    }



    public void notifyExpiringInSevenDaysMemberships() {
        LocalDate targetDate = LocalDate.now().plusDays(7);

        List<Member> expiringMembers = memberRepository.findByExpireDate(targetDate);

        for (Member member : expiringMembers) {
            String subject = "แพ็คเกจสมาชิกของคุณกำลังจะหมดอายุ!";
            String text = String.format("สวัสดีคุณ %s %s,\nแพ็คเกจสมาชิกของคุณจะหมดอายุในวันที่ %s\nกรุณาต่ออายุเพื่อใช้งานได้อย่างต่อเนื่อง",
                    member.getFname(), member.getLname(), member.getExpireDate());

            emailService.sendEmail(member.getEmail(), subject, text);
        }
    }

    public void notifyExpiringTodayMemberships() {
        LocalDate today = LocalDate.now();

        List<Member> expiringMembers = memberRepository.findByExpireDate(today);
        for (Member member : expiringMembers) {
            String subject = "แพ็คเกจสมาชิกของคุณกำลังจะหมดอายุ!";
            String text = String.format("""
                            สวัสดีคุณ %s %s,
                            แพ็คเกจสมาชิกของคุณจะหมดอายุในวันที่ %s
                            คุณจะยังสามารถใช้งานบริการของเราได้จนกว่าจะถึง 0:00 ของวันที่ %s
                            กรุณาต่ออายุเพื่อใช้งานได้อย่างต่อเนื่อง""",
                    member.getFname(), member.getLname(), member.getExpireDate(), LocalDate.now().plusDays(1));
            emailService.sendEmail(member.getEmail(), subject, text);
        }
    }
}
