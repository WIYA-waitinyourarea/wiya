package com.teamwiya.wiya.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="memId")
    private Long id; //pk -> memId

    @Column
    private String memName;

    @Column
    private String memPwd;

    @Column(unique = true)
    private String memMail;

    @Column
    private String memNickname;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    /*
    public static Member createMember(MemberFormDTO memberFormDTO, PasswordEncoder passwordEncoder) {
        Member member = Member.builder()
                .memName(memberFormDTO.getMemName())
                .memMail(memberFormDTO.getMemMail())
                .memNickname(memberFormDTO.getMemNickname())
                .memPwd(passwordEncoder.encode(memberFormDTO.getMemPwd()))
                .role(MemberRole.USER)
                .build();
        return member;
    }
*/

}
