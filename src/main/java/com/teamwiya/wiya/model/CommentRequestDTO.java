package com.teamwiya.wiya.model;

import com.teamwiya.wiya.member.model.Member;
import lombok.Getter;

@Getter
public class CommentRequestDTO {

    private String content;
    private Long boardId;
    private Member member;


}
