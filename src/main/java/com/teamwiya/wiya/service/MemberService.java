package com.teamwiya.wiya.service;

import com.teamwiya.wiya.dto.MemberLoginDTO;
import com.teamwiya.wiya.model.Member;
import com.teamwiya.wiya.model.MemberRole;
import com.teamwiya.wiya.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

//@RequiredArgsConstructor //생성자 자동 생성
//@Transactional(readOnly = true) //에러 발생 시 이전상태로 콜백
@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService{

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

   public MemberLoginDTO login(MemberLoginDTO memberLoginDTO) {
        // db에서 해당 이메일 정보를 가져와서,
        // 입력받은 비밀번호와 db에서 조회한 비번의 일치 여부
        // 일치하면 로그인 성공, 불일치하면 실패
       log.info("입력된 아이디={}", memberLoginDTO.getMemMail());
        List<Member> checkMemMail = memberRepository.findByEmail(memberLoginDTO.getMemMail());
        if (!checkMemMail.isEmpty()) {
            Member loginEntity = checkMemMail.get(0);
            if (loginEntity.getMemPwd().equals(memberLoginDTO.getMemPwd())) {
                return MemberLoginDTO.toMemberDTO(loginEntity);
            }else{ //비번 틀릴때
                return null;
            }
        }else{ //조회된 결과 없을 때 (이메일 없음)
            return null;
        }

    }


/*
public MemberLoginDTO login(MemberLoginDTO memberLoginDTO) {
    // db에서 해당 이메일 정보를 가져와서,
    // 입력받은 비밀번호와 db에서 조회한 비번의 일치 여부
    // 일치하면 로그인 성공, 불일치하면 실패
    Member checkMemMail = memberRepository.checkEmail(memberLoginDTO.getMemMail());
    if (checkMemMail.getMemPwd().equals(memberLoginDTO.getMemPwd())) {
        return MemberLoginDTO.toMemberDTO(checkMemMail);
    }else{
        return null;
    }

}else{ //조회된 결과 없을 때 (이메일 없음)
        return null;
    }

*/


}
