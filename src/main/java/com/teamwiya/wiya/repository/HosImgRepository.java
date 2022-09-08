package com.teamwiya.wiya.repository;

import com.teamwiya.wiya.model.HosImg;
import com.teamwiya.wiya.model.Hospital;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class HosImgRepository {

    private final EntityManager em;

    public void save(HosImg hosImg) {
        em.persist(hosImg);
    }

}
