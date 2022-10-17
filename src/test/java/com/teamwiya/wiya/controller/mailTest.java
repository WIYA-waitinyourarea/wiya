package com.teamwiya.wiya.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.ArrayList;

@SpringBootTest
public class mailTest {

    @Autowired
    private JavaMailSender javaMailSender;

    @Test
    void mailTest() throws Exception {

        // 수신 대상을 담을 ArrayList 생성
        ArrayList<String> toUserList = new ArrayList<>();

        // 수신 대상 추가
        toUserList.add("bkh3030@naver.com");

        // 수신 대상 개수
        int toUserSize = toUserList.size();

        // SimpleMailMessage (단순 텍스트 구성 메일 메시지 생성할 때 이용)
        SimpleMailMessage simpleMessage = new SimpleMailMessage();

        // 수신자 설정
        simpleMessage.setTo((String[]) toUserList.toArray(new String[toUserSize]));

        // 메일 제목
        simpleMessage.setSubject("Subject Sample");

        // 메일 내용
        simpleMessage.setText("Text Sample");

        // 메일 발송
        javaMailSender.send(simpleMessage);
    }
}
