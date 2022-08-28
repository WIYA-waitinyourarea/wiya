package com.teamwiya.wiya.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class Board extends TimeStamped{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "boaId")
    private long id;

    @Column(nullable = false)
    private String content;

    /*
    @ManyToOne
    @JoinColumn(name = "Member_ID")
    private Member member;
    */

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc") // 댓글 정렬
    private List<Comment> comments;









}
