package com.teamwiya.wiya.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
public class Hospital extends TimeStamped{

    //1. PK / 2. 자동으로 값 생성(아이덴티티 전략 -> 디비가 생성)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hosId;

    private String hosName;

    private String hosPhone;

    @Enumerated
    private HosStatus hosStatus;

    @Enumerated
    private HosBooking hosBooking;

    private String hosAddress;

     private double hosLatitude;

     private double hosLongitude;

     private String hosOpenhour;

     @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
     private List<HosImg> hosImgs = new ArrayList<>();

}
