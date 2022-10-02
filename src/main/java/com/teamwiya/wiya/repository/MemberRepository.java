package com.teamwiya.wiya.repository;

import com.teamwiya.wiya.model.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
@RequiredArgsConstructor
public class MemberRepository {
    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public List<Member> findByEmail(String memMail) { //중복체크 로직
        //System.out.println("repository"+ memMail);
        return em.createQuery("select m from Member m where m.memMail =:memMail", Member.class)
                .setParameter("memMail", memMail)
                .getResultList();
    }


/*
    public Member checkEmail(String memMail) { //중복체크 로직
        //System.out.println("repository"+ memMail);
        return em.createQuery("select m from Member m where m.memMail =:memMail", Member.class)
                .setParameter("memMail", memMail)
                .getSingleResult(); //엔티티 없으면 예외
    }
*/

}
