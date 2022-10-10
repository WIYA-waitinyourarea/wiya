package com.teamwiya.wiya.board.model;

import com.teamwiya.wiya.TimeStamped;
import com.teamwiya.wiya.board.dto.CommentRequestDTO;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Getter
@NoArgsConstructor
@Entity
public class Comment extends TimeStamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "boaId")
    private Board board;

    /*
    @ManyToOne
    @JoinColumn(name = "Member_ID")
    private Member member;
    */


    public Comment(CommentRequestDTO requestDTO, Board board){
        this.content = requestDTO.getContent();
        this.board = board;
    }

}
