package com.teamwiya.wiya.repository;

import com.teamwiya.wiya.model.Address;
import com.teamwiya.wiya.model.Sigudong;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SigudongRepository {

    private final EntityManager em;

    public void save(Sigudong sigudong) {
        em.persist(sigudong);
    }

    public Sigudong findById(Long bCode) {
        return em.find(Sigudong.class, bCode);
        /*return em.createQuery("select s from Sigudong s where s.sigudongId = :bCode ", Sigudong.class)
                .setParameter("bCode", bCode)
                .getSingleResult();*/
    }

    public List<Sigudong> findAll() {
        return em.createQuery("select s from Sigudong s",Sigudong.class).getResultList();
    }

}
