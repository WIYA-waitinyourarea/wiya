package com.teamwiya.wiya.member.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class MemberChangpwdForm {
    @NotBlank(message = "이메일은 필수 입력값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요")
    private String memMail;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Length(min = 6, max = 12, message = "비밀번호는 6자이상, 12자 이하입니다.")
    private String memPwd;

    @NotBlank(message = "비밀번호확인은 필수 입력값입니다.")
    @Length(min=6, max=12, message ="비밀번호는 6자이상, 12자 이하입니다.")
    private String memPwdCheck;
}
