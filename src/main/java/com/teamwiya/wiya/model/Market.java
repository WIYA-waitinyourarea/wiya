package com.teamwiya.wiya.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class Market extends TimeStamped{

    @Id
    @GeneratedValue
    @Column(name = "post_Id")
    private Long id;
    private String postTitle;
    private String postContent;
    private int itemPrice;
    //재고관리 따로 안함-> 상품은 무조건 1개 등록, 여러개일 경우 글내용으로 명시

    private String filename;
    private String filepath;

    @Enumerated(EnumType.STRING)
    private MarketItemStatus itemStatus;

    public static Market writeNewPost(String postTitle, int itemPrice, String postContent, String filepath, String filename ) {
        return Market.builder()
                .postTitle(postTitle)
                .itemPrice(itemPrice)
                .postContent(postContent)
                .filename(filename)
                .filepath(filepath)
                .build();
    }

}
