package com.teamwiya.wiya.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter
public class MemberFormDTO {

    @NotBlank(message = "이름은 필수 입력값입니다.")
    private String memName;

    @NotEmpty(message = "비밀번호는 필수 입력값입니다.")
    @Length(min=6, max=12, message ="비밀번호는 6자이상, 12자 이하입니다.")
    private String memPwd;

    @NotEmpty(message = "이메일은 필수 입력값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요")
    private String memMail;

    @NotEmpty(message = "닉네임은 필수 입력값입니다.")
    private String memNickname;

    @Builder
    public MemberFormDTO(String memName, String memPwd, String memMail, String memNickname) {
        this.memName = memName;
        this.memPwd = memPwd;
        this.memMail = memMail;
        this.memNickname = memNickname;
    }

}
