package com.teamwiya.wiya.service;

import com.teamwiya.wiya.model.Member;
import com.teamwiya.wiya.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor //생성자 자동 생성
@Transactional //에러 발생 시 이전상태로 콜백
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public Member saveMember(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByMemMail(member.getMemMail());
        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다");
        }
    }
}
