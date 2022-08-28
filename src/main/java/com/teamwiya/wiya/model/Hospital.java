package com.teamwiya.wiya.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Hospital {

    //1. PK / 2. 자동으로 값 생성(아이덴티티 전략 -> 디비가 생성)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hosId;
    @Column(name = "hosName")
    private String hosName;
    @Column(name = "hosPhone")
    private String hosPhone;
    @Column(name = "hosStatus")
    private boolean hosStatus;
    @Column(name = "hosBooking")
    private boolean hosBooking;
    @Column(name = "hosAddress")
    private String hosAddress;
    @Column(name = "hosLatitude")
    private double hosLatitude;
    @Column(name = "hosLongitude")
    private double hosLongitude;
    @Column(name = "hosOpenhour")
    private String hosOpenhour;

    public Hospital() {
    }

    public Hospital(String hosName, String hosPhone, boolean hosStatus, boolean hosBooking, String hosAddress, double hosLatitude, double hosLongitude, String hosOpenhour) {
        this.hosName = hosName;
        this.hosPhone = hosPhone;
        this.hosStatus = hosStatus;
        this.hosBooking = hosBooking;
        this.hosAddress = hosAddress;
        this.hosLatitude = hosLatitude;
        this.hosLongitude = hosLongitude;
        this.hosOpenhour = hosOpenhour;
    }
}
