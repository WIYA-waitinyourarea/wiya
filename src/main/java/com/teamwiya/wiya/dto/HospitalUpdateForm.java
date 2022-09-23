package com.teamwiya.wiya.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
public class HospitalUpdateForm {

    @NotBlank
    private String hosName;
    @NotBlank
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$")
    private String hosPhone;
    @NotBlank
    private String jibunAddress; //서울 서대문구 북가좌동 328-51
    private String sangse;
    private String hosOpenHour;
    private List<MultipartFile> hosImgs;

}
