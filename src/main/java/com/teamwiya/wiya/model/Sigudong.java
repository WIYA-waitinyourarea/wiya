package com.teamwiya.wiya.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Sigudong {

    @Id @GeneratedValue
    private Long sigudongId;

    private String sigudongName;

    @OneToMany(mappedBy = "hopitals")
    private List<Hospital> hospitals = new ArrayList<>();

    /*상위 주소 (강남구 : 서울시 / 역삼동 : 강남구*/
    @ManyToOne(fetch = LAZY) @JoinColumn(name = "parent_id")
    private Sigudong parent;

    /*하위 주소 (서울시 : {서대문구, 용산구, 강남구, ...} / 강남구 : {역삼동, 대치동, ...}*/
    @OneToMany(mappedBy = "parent")
    private List<Sigudong> child = new ArrayList<>();




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
