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
    private String sido; //서울
    private String siqungu; //서대문구
    private String bname;
    private String jibunAddress; //서울 서대문구 북가좌동 328-51
    private String roadAddress; //서울 서대문구 증가로29길 20-14
    private String sangse;
    private double x;
    private double y;
    private String hosOpenHour;
    private List<MultipartFile> hosImgs;

}
