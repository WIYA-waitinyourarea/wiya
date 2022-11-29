package com.teamwiya.wiya.hospital.model;

import com.teamwiya.wiya.hospital.dto.HospitalSaveForm;
import com.teamwiya.wiya.hospital.dto.HospitalUpdateForm;
import com.teamwiya.wiya.TimeStamped;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Hospital extends TimeStamped {

    //1. PK / 2. 자동으로 값 생성(아이덴티티 전략 -> 디비가 생성)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hosId;

    private String hosName;

    private String hosPhone;

    @Enumerated(EnumType.STRING)
    private HosStatus hosStatus;

    @Enumerated(EnumType.STRING)
    private HosBooking hosBooking; // 예약 가능 상태를 나타내는 값

    @Embedded
    private Address hosAddress;

    private String hosOpenhour;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hos_sigudong")
    private Sigudong hosSigudong;

    @OneToMany(mappedBy = "hospital",orphanRemoval = true)
    private List<HosImg> hosImgs = new ArrayList<>();

    /*== 빌더패턴을 통한 생성 메소드 ==*/
    public static Hospital createHospital(HospitalSaveForm form, Address hosAddress, Sigudong dong) {
        return Hospital.builder()
                .hosName(form.getHosName())
                .hosPhone(form.getHosPhone())
                .hosStatus(HosStatus.OPEN)
                .hosBooking(HosBooking.POSSIBLE)
                .hosAddress(hosAddress)
                .hosSigudong(dong)
                .hosOpenhour(form.getHosOpenHour())
                .build();
     }

     /*상태 유지를 이용한 업데이트*/
    public void update(HospitalUpdateForm hospitalUpdateForm, Address address, Sigudong sigudong) {

        this.hosName = hospitalUpdateForm.getHosName();
        this.hosPhone = hospitalUpdateForm.getHosPhone();
        this.hosOpenhour = hospitalUpdateForm.getHosOpenHour();
        this.hosAddress = address;
        this.hosSigudong = sigudong;
    }
}
