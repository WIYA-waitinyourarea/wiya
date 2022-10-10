package com.teamwiya.wiya.repository;

import com.teamwiya.wiya.model.Market;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MarketRepository {

    private final EntityManager em;

    public void save(Market market) {
        em.persist(market);
    }

    public List<Market> findAll() { //전체조회
        return em.createQuery("select m from Market m", Market.class)
                .getResultList();
    }
    public Market findOne(Long post_Id) {
        return em.find(Market.class, post_Id);
    }

//        return em.createQuery("select m from Market m where m.id=:post_Id", Market.class)
     //           .setParameter("post_Id", "post_Id")
     //           .getResultList();


}

