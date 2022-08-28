package com.teamwiya.wiya.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter @Setter
public class Booking {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long booId;
    @ManyToOne @JoinColumn(name = "memId")
    private Member member;
    @ManyToOne @JoinColumn(name = "hosId")
    private Hospital hospital;
    //DATETIME VS TIME 뭘로할지 모르겠음
    @Column(name = "booTime")
    private Timestamp booTime;
    //이건 스트링? 인트? 뭘로하지? 불리언으론 안될거같음
    @Column(name = "booState")
    private int booState;
}
