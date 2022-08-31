package com.teamwiya.wiya.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

}