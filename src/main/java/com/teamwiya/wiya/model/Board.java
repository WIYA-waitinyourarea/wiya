package com.teamwiya.wiya.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Board extends TimeStamped{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;

    @Column(nullable = false)
    private String title;

    /*
    @ManyToOne
    @JoinColumn(name = "Member_ID")
    private Member member;
    */








}
