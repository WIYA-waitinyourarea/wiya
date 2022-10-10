package com.teamwiya.wiya.hospital.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class RevImg {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rimId;
    @ManyToOne @JoinColumn(name = "revId")
    private Review review;
    @Column(name = "rimPath")
    private String rimPath;


    public RevImg() {
    }

    public RevImg(Review review, String rimPath) {
        this.review = review;
        this.rimPath = rimPath;
    }
}
