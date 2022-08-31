package com.teamwiya.wiya.service;

import com.teamwiya.wiya.model.Member;
import com.teamwiya.wiya.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor //생성자 자동 생성
@Transactional //에러 발생 시 이전상태로 콜백
@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public void register(Member member) {
        memberRepository.save(member);
    }

/*

    public Member join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member;
    }

    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByMemMail(member.getMemMail());
        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다");
        }
    }
*/


}
