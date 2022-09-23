package com.teamwiya.wiya.repository;

import com.teamwiya.wiya.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public List<Member> findByEmail(String memMail) { //중복체크 시 이메일로 확인
        System.out.println("repository"+ memMail);
        return em.createQuery("select m from Member m where m.memMail =:memMail", Member.class)
                .setParameter("memMail", memMail)
                .getResultList();
    }



}
