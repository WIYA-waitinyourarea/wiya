package com.teamwiya.wiya.hospital.dto;

import com.teamwiya.wiya.hospital.model.HosImg;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
public class HospitalUpdateForm {

    @NotNull
    private Long hosId;
    @NotBlank
    private String hosName;
    @NotBlank
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$")
    private String hosPhone;
    @NotBlank
    private String jibunAddress; //서울 서대문구 북가좌동 328-51
    private String sangse; //202호
    private String hosOpenHour;
    /*사진 수정 관련 필드*/
    private List<HosImg> hosImgsBefore;
    private List<Long> hosImgsAfter;
    private List<MultipartFile> hosImgs;
}
