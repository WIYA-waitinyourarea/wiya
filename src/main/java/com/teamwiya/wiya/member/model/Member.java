package com.teamwiya.wiya.member.model;

import com.teamwiya.wiya.TimeStamped;
import lombok.*;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
@Builder
public class Member extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="memId")
    private Long id; //pk -> memId

    private String memName;
    private String memPwd;
    private String memPwdCheck;
    private String memMail;
    private String memNickname;
    @Enumerated(EnumType.STRING)
    private MemberRole role;

    public Member(Long id, String memName, String memPwd, String memPwdCheck, String memMail, String memNickname, MemberRole role) {
        this.id = id;
        this.memName = memName;
        this.memPwd = memPwd;
        this.memPwdCheck = memPwdCheck;
        this.memMail = memMail;
        this.memNickname = memNickname;
        this.role = role;
    }
}