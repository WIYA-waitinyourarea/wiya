package com.teamwiya.wiya.repository;

import com.teamwiya.wiya.model.Hospital;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

//스프링에서 자동으로 프록시 -> 빈객체 생성해줌
//public interface HospitalRepository extends JpaRepository<Hospital, Long> {

@Repository
@RequiredArgsConstructor
public class HospitalRepository {

    private final EntityManager em;

    public void save(Hospital hospital) {
        em.persist(hospital);
    }

    public Hospital findOne(Long id) {
        return em.find(Hospital.class, id);
    }

    public List<Hospital> findAll() {
        return em.createQuery("select h from Hospital h ", Hospital.class)
                .getResultList();
    }
    /*병원명 검색*/
    public List<Hospital> findByHosNameContaining(String keyword) {
        return null;
    }
    /*동네 검색*/
    public List<Hospital> findByHosAddressContaining(String keyword){
        return null;
    }

}
