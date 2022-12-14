package com.teamwiya.wiya.member.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private Long id;

    @Column
    private String petName;

    @Column
    private int petAge;

    @Column
    private String petGender; //female, male

    @Enumerated(EnumType.STRING)
    private Species species;

    @ManyToOne
    @JoinColumn(referencedColumnName = "memId")
    private Member member;

}
