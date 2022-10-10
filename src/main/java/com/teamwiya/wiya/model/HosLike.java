package com.teamwiya.wiya.model;

import com.teamwiya.wiya.member.model.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class HosLike {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hliId;
    @ManyToOne @JoinColumn(name = "memId")
    private Member member;
    @ManyToOne @JoinColumn(name = "hosId")
    private Hospital hospital;

    public HosLike() {
    }

    public HosLike(Member member, Hospital hospital) {
        this.member = member;
        this.hospital = hospital;
    }
}
