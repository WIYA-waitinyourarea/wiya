package com.teamwiya.wiya.model;

import com.teamwiya.wiya.dto.HospitalUpdateForm;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Hospital extends TimeStamped{

    //1. PK / 2. 자동으로 값 생성(아이덴티티 전략 -> 디비가 생성)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hosId;

    private String hosName;

    private String hosPhone;

    @Enumerated
    private HosStatus hosStatus;

    @Enumerated
    private HosBooking hosBooking; // 예약 가능 상태를 나타내는 값

    @Embedded
    private Address hosAddress;

     private double hosLatitude;

     private double hosLongitude;

     private String hosOpenhour;

     @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL, orphanRemoval = true)
     private List<HosImg> hosImgs = new ArrayList<>();

    /*public Hospital(Long hosId, String hosName, String hosPhone, HosStatus hosStatus, HosBooking hosBooking, Address hosAddress, double hosLatitude, double hosLongitude, String hosOpenhour, List<HosImg> hosImgs) {
        this.hosId = hosId;
        this.hosName = hosName;
        this.hosPhone = hosPhone;
        this.hosStatus = hosStatus;
        this.hosBooking = hosBooking;
        this.hosAddress = hosAddress;
        this.hosLatitude = hosLatitude;
        this.hosLongitude = hosLongitude;
        this.hosOpenhour = hosOpenhour;
        this.hosImgs = hosImgs;
    }*/

    //이게 왜 퍼블릭으로밖에 안되는거지? @Builder에 대한 문제다


    /*== 빌더패턴을 통한 생성 메소드 ==*/
    public static Hospital createHospital(String hosName, String hosPhone, Address hosAddress, String hosOpenhour) {
        return Hospital.builder()
                .hosName(hosName)
                .hosPhone(hosPhone)
                .hosStatus(HosStatus.OPEN)
                .hosBooking(HosBooking.POSSIBLE)
                .hosAddress(hosAddress)
                .hosOpenhour(hosOpenhour)
                .build();
     }

     /*상태 유지를 이용한 업데이트*/
    public void update(HospitalUpdateForm hospitalUpdateForm) {
        if(!this.hosAddress.getJibunAddress().equals(hospitalUpdateForm.getJibunAddress())
                || !this.hosAddress.getSangse().equals(hospitalUpdateForm.getSangse())){
            this.hosAddress = Address.createAddress(hospitalUpdateForm.getJibunAddress(), hospitalUpdateForm.getSangse());
        }
        this.hosName = hospitalUpdateForm.getHosName();
        this.hosPhone = hospitalUpdateForm.getHosPhone();
        this.hosOpenhour = hospitalUpdateForm.getHosOpenHour();
    }
}
