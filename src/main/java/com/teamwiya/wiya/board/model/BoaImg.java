package com.teamwiya.wiya.board.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor//기본생성자
@AllArgsConstructor //차이가 뭘까?//모든 필드를 넣은 생성자를 만들어라 애송아
@Builder
@Getter
public class BoaImg {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String orgName;
    private String saveName;
    private String savePath;

    @ManyToOne//앞에 나온말이 나의 관계
    @JoinColumn(name = "boaID")
    private Board board;

    //@빌더????






}
