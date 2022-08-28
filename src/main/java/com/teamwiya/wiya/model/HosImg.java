package com.teamwiya.wiya.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter @Setter
public class HosImg {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long himId;
    @ManyToOne @JoinColumn(name = "hosId")
    private Hospital hospital;
    @Column(name = "himPath")
    private String himPath;
    @Column(name = "himMain")
    private boolean himMain;

    public HosImg() {
    }

    public HosImg(Hospital hospital, String himPath, boolean himMain) {
        this.hospital = hospital;
        this.himPath = himPath;
        this.himMain = himMain;
    }
}
