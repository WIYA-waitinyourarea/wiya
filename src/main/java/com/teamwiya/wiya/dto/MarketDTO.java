package com.teamwiya.wiya.dto;

import com.teamwiya.wiya.model.MarketItemStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class MarketDTO {

    private Long post_Id;

    @NotBlank(message = "제목은 필수 입력값입니다.")
    private String postTitle;

    private String postContent;

    @NotBlank(message = "가격은 필수 입력값입니다.")
    private int itemPrice;

    private String filename;
    private String filepath;


    private MarketItemStatus itemStatus;


}
