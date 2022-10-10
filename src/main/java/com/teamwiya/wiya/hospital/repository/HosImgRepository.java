package com.teamwiya.wiya.hospital.repository;

import com.teamwiya.wiya.hospital.model.HosImg;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Slf4j
@Repository
@RequiredArgsConstructor
public class HosImgRepository {

    private final EntityManager em;

    public void save(HosImg hosImg) {em.persist(hosImg);}

    public HosImg findById(Long id) {return em.find(HosImg.class, id);}

}
