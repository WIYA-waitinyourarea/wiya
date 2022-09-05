package com.teamwiya.wiya.model;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String sido; //서울
    private String siqungu; //서대문구
    private String bname;
    private String jibunAddress; //서울 서대문구 북가좌동 328-51
    private String roadAddress; //서울 서대문구 증가로29길 20-14
    private String sangse;
    private double x;
    private double y;

    public Address() {}

    public Address(String sido, String siqungu, String bname, String jibunAddress, String roadAddress, String sangse, double x, double y) {
        this.sido = sido;
        this.siqungu = siqungu;
        this.bname = bname;
        this.jibunAddress = jibunAddress;
        this.roadAddress = roadAddress;
        this.sangse = sangse;
        this.x = x;
        this.y = y;
    }
}
