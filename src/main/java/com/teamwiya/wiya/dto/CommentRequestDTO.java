package com.teamwiya.wiya.dto;

import com.teamwiya.wiya.model.Member;
import lombok.Getter;

@Getter
public class CommentRequestDTO {

    private String content;
    private Long boardId;
    private Member member;


}
