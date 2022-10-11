package com.teamwiya.wiya.hospital.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sigudong {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sigudongId;//1124900000

    private String sigudongName; //용산구

    @OneToMany(mappedBy = "hosSigudong")
    private List<Hospital> hospitals = new ArrayList<>();  // 용산구 1병워, 용산2병원, ...
    @ManyToOne(fetch = LAZY) @JoinColumn(name = "parent_id")
    private Sigudong parent; //서울특별시 객체
    @OneToMany(mappedBy = "parent")
    private List<Sigudong> child = new ArrayList<>(); //empty
    /*해당 엔티티는 생성할 일이 없음*/
    //
}









/*매니투매니는 실무에서는 절대 사용하지 않음*/
    /*M:N관계 -> 중간 테이블 필요*/
    /*@ManyToMany
    @JoinTable(
            name = "hospital_sigudong",
            joinColumns = @JoinColumn(name = "sigudong_id"),
            inverseJoinColumns = @JoinColumn(name = "hospital_id")
    )
    private List<Hospital> hospitals = new ArrayList<>();*/
