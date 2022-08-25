package com.teamwiya.wiya.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class HospitalDTO {

    //1. PK / 2. 자동으로 값 생성(아이덴티티 전략 -> 디비가 생성)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hosId;
    @Column(name = "hos_name")
    private String hosName;
    @Column(name = "hos_phone")
    private String hosPhone;
    @Column(name = "hos_state")
    private String hosState;
    @Column(name = "hos_hour")
    private String hosHour;

    public HospitalDTO() {
    }
}
