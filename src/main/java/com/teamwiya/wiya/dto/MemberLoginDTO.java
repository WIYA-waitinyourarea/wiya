package com.teamwiya.wiya.dto;

import com.teamwiya.wiya.model.Member;
import lombok.Data;

import javax.validation.constraints.NotBlank;

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
