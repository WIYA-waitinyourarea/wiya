package com.teamwiya.wiya.dto;

import com.teamwiya.wiya.model.HosImg;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter @Setter
public class HospitalNewForm {

    @NotEmpty(message = "병원 이름 필수 입력")
    private String hosName;
    private String hosPhone;
    private String jibunAddress; //서울 서대문구 북가좌동 328-51
    private String sangse;
    private String hosOpenHour;
    private List<MultipartFile> hosImgs;

}
