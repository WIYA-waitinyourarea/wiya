package com.teamwiya.wiya.repository;

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

    public Sigudong findById(Long sigudongId) {
        return em.createQuery("select s from Sigudong s where s.sigudongId = :sigudongId", Sigudong.class)
                .setParameter("sigudongId", sigudongId)
                .getSingleResult();
    }

    public List<Sigudong> findByName(Long bCode) {
        return em.createQuery("select s from Sigudong s where s.bCode = :bCode ", Sigudong.class)
                .setParameter("bCode", bCode)
                .getResultList();
    }
}
