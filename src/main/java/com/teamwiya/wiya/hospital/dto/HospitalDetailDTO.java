package com.teamwiya.wiya.hospital.dto;

import com.teamwiya.wiya.hospital.model.Address;
import com.teamwiya.wiya.hospital.model.HosImg;
import com.teamwiya.wiya.hospital.model.HosStatus;
import com.teamwiya.wiya.hospital.model.Hospital;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class HospitalDetailDTO {

    private Long hosId;
    private String hosName;
    private String hosPhone;
    private Address hosAddress;
    private HosStatus hosStatus;
    private double revScore;
    private List<HosImg> hosImgs = new ArrayList<>();

    public HospitalDetailDTO (Hospital hospital) {
        this.hosId = hospital.getHosId();
        this.hosName = hospital.getHosName();
        this.hosPhone = hospital.getHosPhone();
        this.hosAddress = hospital.getHosAddress();
        this.hosStatus = hospital.getHosStatus();
        this.hosImgs = hospital.getHosImgs();
    }
}
