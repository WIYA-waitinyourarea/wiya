package com.teamwiya.wiya.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Review {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long revId;
    @ManyToOne @JoinColumn(name = "memId")
    private Member member;
    @ManyToOne @JoinColumn(name = "hosId")
    private Hospital hospital;
    /** 리뷰같이 짧은 글은 VARCHAR(255)로 괜찮을것 같음  -> 생각해봐야겠음 */
    @Column(name = "revContent")
    private String revContent;
    @Column(name = "revScore")
    private double revScore;
    @Column(name = "revLevel")
    private int revLevel;

    public Review() {
    }

    public Review(Member member, Hospital hospital, String revContent, double revScore, int revLevel) {
        this.member = member;
        this.hospital = hospital;
        this.revContent = revContent;
        this.revScore = revScore;
        this.revLevel = revLevel;
    }
}
