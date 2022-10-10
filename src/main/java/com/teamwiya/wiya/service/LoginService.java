package com.teamwiya.wiya.service;

import com.teamwiya.wiya.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

/*    public Member login(String loginMail, String password) {
        return memberRepository.findByLoginMail(loginMail)
                .stream().filter(m -> m.getMemPwd().equals(password))
                .orElse(null);


    }*/
}
