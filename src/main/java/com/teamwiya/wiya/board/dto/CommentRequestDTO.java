package com.teamwiya.wiya.board.dto;

import com.teamwiya.wiya.member.model.Member;
import lombok.Getter;

@Getter
public class CommentRequestDTO {

    private String content;
    private Long boardId;
    private Member member;


}
