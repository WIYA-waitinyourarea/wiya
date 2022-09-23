package com.teamwiya.wiya.service;

import com.teamwiya.wiya.model.Member;
import com.teamwiya.wiya.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@RequiredArgsConstructor //생성자 자동 생성
//@Transactional(readOnly = true) //에러 발생 시 이전상태로 콜백
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService  {

    private final MemberRepository memberRepository;
    @Transactional
    public Long register(Member member) {
        duplicateMemberCheck(member);//중복회원검증
        memberRepository.save(member);
        return member.getId();
    }

    private void duplicateMemberCheck(Member member) {
        List<Member> findMembers = memberRepository.findByEmail(member.getMemMail());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("같은 이메일을 가진 회원이 존재합니다.");
        }
    }

/*
    public int memMailCheck() {
        memberRepository.findByEmail();
        return 0;
    }
*/

    // public boolean overlappedMail(String memMail){
   //     return memberRepository.existsByEmail(memMail);
  //  }


}
