package com.teamwiya.wiya.dto;

import com.teamwiya.wiya.market.model.MarketItemStatus;
import lombok.Data;

import javax.validation.constraints.NotBlank;

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
