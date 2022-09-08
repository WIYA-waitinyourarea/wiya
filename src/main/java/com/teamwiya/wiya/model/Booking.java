package com.teamwiya.wiya.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter @Setter
public class Booking extends TimeStamped {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long booId;
    @ManyToOne @JoinColumn(name = "memId")
    private Member member;
    @ManyToOne @JoinColumn(name = "hosId")
    private Hospital hospital;
    @Enumerated
    private BooState booState;
}
