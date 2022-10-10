package com.teamwiya.wiya.hospital.model;

import com.teamwiya.wiya.member.model.Member;
import com.teamwiya.wiya.TimeStamped;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking extends TimeStamped {
    /*이 엔티티는 작업을 할 수록 고유키는 하는 역할이 없고, 멤버와 병원이 다함*/
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
