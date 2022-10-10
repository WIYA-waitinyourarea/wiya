package com.teamwiya.wiya.hospital.dto;

import com.teamwiya.wiya.hospital.model.HosImg;
import com.teamwiya.wiya.hospital.model.HosStatus;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class HospitalDetailDTO {

    private Long hosId;
    private String hosName;
    private String hosAddress;
    private HosStatus hosStatus;
    private double revScore;
    private int waiting;
    private List<HosImg> hosImgs = new ArrayList<>();

}
