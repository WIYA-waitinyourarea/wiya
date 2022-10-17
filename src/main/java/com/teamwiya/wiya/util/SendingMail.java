package com.teamwiya.wiya.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
@Slf4j
@Component
@RequiredArgsConstructor
public class SendingMail {

    private final JavaMailSender javaMailSender;

    public String MailCheck(String mail) {

        //난수발생
        String check = createCheck();
        log.info("난수= {}", check);
        // MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        try {
            //발송 메일을 담는 메세지 객체 ㅅㅇ성
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            //발신 메일(설정에 있는 메일)
            mimeMessageHelper.setFrom("bkh3030@gmail.com"); // 보내는사람 이메일 여기선 google 메일서버 사용하는 아이디를 작성하면됨
            //수신 메일
            mimeMessageHelper.setTo(mail);
            //메일 제목
            mimeMessageHelper.setSubject("[WIYA] 인증번호 발송입니다" ); // 메일제목
            mimeMessageHelper.setText("text/html","<p>안녕하세요.</p><p>인증번호는"+ check + "입니다.</p><p>감사합니다</p>"); // 메일 내용

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return check;
    }

    private String createCheck() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append((char)((int)(Math.random()*26)+65));
        }
        String check = sb.toString();
        return check;
    }
}
