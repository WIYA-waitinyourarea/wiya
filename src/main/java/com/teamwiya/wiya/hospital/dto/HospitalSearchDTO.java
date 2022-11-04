package com.teamwiya.wiya.hospital.dto;

import lombok.*;

@Data
public class HospitalSearchDTO {

    private String keyword = "";
    private int page = 1;
    private Long sido = 0L;
    private Long sigungu = 0L;
}
