package com.teamwiya.wiya.repository;

import com.teamwiya.wiya.model.Member;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원 조회 테스트")
    void save() {
        Long id =10L;

        Optional<Member> result = memberRepository.findById(id);
        System.out.println("=======");
        if(result.isPresent()){
            Member member = result.get();
            System.out.println(member);

        }
    }
}