package com.teamwiya.wiya.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teamwiya.wiya.member.model.Member;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Getter@
@Data
@NoArgsConstructor
@Entity
@Builder
@ToString
@AllArgsConstructor
public class Board extends TimeStamped{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "boaId")
    private long id;

    @Column(nullable = false)
    private String content;


    @ManyToOne
    @JoinColumn(name = "Member_ID")
    private Member member;

    @JsonIgnore
    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @OrderBy("id asc") // 댓글 정렬
    private List<Comment> comments = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @OrderBy("id asc") // 댓글 정렬
    private List<BoaImg> boaImgs = new ArrayList<>();









}
