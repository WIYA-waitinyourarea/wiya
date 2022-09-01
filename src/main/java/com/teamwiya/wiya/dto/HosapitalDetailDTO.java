package com.teamwiya.wiya.dto;

import com.teamwiya.wiya.model.HosImg;
import com.teamwiya.wiya.model.HosStatus;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class HosapitalDetailDTO {

    private Long hosId;
    private String hosName;
    private String hosAddress;
    private HosStatus hosStatus;
    private double revScore;
    private int waiting;
    private List<HosImg> hosImgs = new ArrayList<>();

}
