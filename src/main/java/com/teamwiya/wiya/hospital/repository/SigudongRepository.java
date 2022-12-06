package com.teamwiya.wiya.hospital.repository;

import com.teamwiya.wiya.hospital.model.Sigudong;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class SigudongRepository {

    private final EntityManager em;

    public void save(Sigudong sigudong) {
        em.persist(sigudong);
    }

    public Sigudong findById(Long bCode) {
        return em.find(Sigudong.class, bCode);
    }

    public List<Sigudong> findAll() {
        return em.createQuery("select s from Sigudong s",Sigudong.class).getResultList();
    }

    public List<Sigudong> findSi() {
        return em.createQuery("select distinct si " +
                                "from Sigudong si join fetch si.child " +
                                "where si.parent is null ", Sigudong.class)
                .getResultList();
    }

}
