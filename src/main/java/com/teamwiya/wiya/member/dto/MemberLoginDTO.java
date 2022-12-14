package com.teamwiya.wiya.member.dto;

import com.teamwiya.wiya.member.model.Member;
import lombok.Data;

@Data
public class MemberLoginDTO {

    private String memMail;
    private String memPwd;

    public static MemberLoginDTO toMemberDTO(Member member) {
        MemberLoginDTO memberLoginDTO = new MemberLoginDTO();
        memberLoginDTO.setMemMail(member.getMemMail());
        memberLoginDTO.setMemPwd(member.getMemPwd());
        return memberLoginDTO;
    }

}
