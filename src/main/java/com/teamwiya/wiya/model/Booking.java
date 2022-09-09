package com.teamwiya.wiya.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking extends TimeStamped {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long booId;
    @ManyToOne @JoinColumn(name = "memId")
    private Member member;
    @ManyToOne @JoinColumn(name = "hosId")
    private Hospital hospital;
    @Enumerated
    private BooState booState;

    public static Booking createBooking(Member member, Hospital hospital){

        return Booking.builder()
                .member(member)
                .hospital(hospital)
                .booState(BooState.WAITING)
                .build();

    }
}
