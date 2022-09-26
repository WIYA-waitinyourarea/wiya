package com.teamwiya.wiya.repository;

import com.teamwiya.wiya.model.HosImg;
import com.teamwiya.wiya.model.Hospital;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class HosImgRepository {

    private final EntityManager em;

    public void save(HosImg hosImg) {
        em.persist(hosImg);
    }

    public HosImg findById(Long id) {
        return em.find(HosImg.class, id);
    }

}
