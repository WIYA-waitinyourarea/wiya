package com.teamwiya.wiya.service;

import com.teamwiya.wiya.model.Member;
import com.teamwiya.wiya.model.MemberFormDTO;
import com.teamwiya.wiya.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void createMember() {
        Member member = Member.builder()
                .memMail("test@email.com")
                .build();

        Member member1 = memberRepository.save(member); //메모리상 멤버

        Optional<Member> result = memberRepository.findById(member1.getId());
        Assertions.assertThat(result.get().getMemMail()).isEqualTo(member1.getMemMail());

    }




}