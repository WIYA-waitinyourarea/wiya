package com.teamwiya.wiya.member.model;

import com.teamwiya.wiya.model.TimeStamped;
import lombok.*;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
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

}