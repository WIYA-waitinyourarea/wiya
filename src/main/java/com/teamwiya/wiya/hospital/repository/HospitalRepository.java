package com.teamwiya.wiya.hospital.repository;

import com.teamwiya.wiya.hospital.dto.HospitalSearchDTO;
import com.teamwiya.wiya.hospital.dto.PagingDTO;
import com.teamwiya.wiya.hospital.model.Hospital;
import lombok.RequiredArgsConstructor;
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
        em.createQuery("");
        return em.createQuery("select h from Hospital h where h.hosName LIKE :keyword", Hospital.class)
                .setParameter("keyword", "%"+keyword+"%")
                .getResultList();
    }

    public List<Hospital> findByHosName(
            HospitalSearchDTO hospitalSearchDTO,
            PagingDTO pagingDTO
    ) {
        return em.createQuery("select h from Hospital h" +
                        " where h.hosName LIKE :keyword", Hospital.class)
                .setParameter("keyword", "%"+hospitalSearchDTO.getKeyword()+"%")
                .setFirstResult(pagingDTO.getPage())
                .setMaxResults(pagingDTO.getLimit())
                .getResultList();
    }

    public List<Hospital> findByHosNameAndSi(
            HospitalSearchDTO hospitalSearchDTO,
            PagingDTO pagingDTO
    ) {
        return em.createQuery("select h " +
                        " from Hospital h JOIN Sigudong s " +
                        " ON h.hosSigudong.sigudongId = s.sigudongId" +
                        " WHERE h.hosName LIKE :keyword " +
                        " AND (s.parent.sigudongId = :sigudongId" +
                        " OR s.sigudongId = :sigudongId) ", Hospital.class)
                .setParameter("keyword", "%"+hospitalSearchDTO.getKeyword()+"%")
                .setParameter("sigudongId", hospitalSearchDTO.getSigungu())
                .setFirstResult(pagingDTO.getPage())
                .setMaxResults(pagingDTO.getLimit())
                .getResultList();
    }

    public List<Hospital> findByHosNameAndGu(
            HospitalSearchDTO hospitalSearchDTO,
            PagingDTO pagingDTO
    ) {
        return em.createQuery("select h from Hospital h" +
                        " where h.hosName LIKE :keyword" +
                        " and h.hosSigudong.sigudongId = :sigudongId", Hospital.class)
                .setParameter("keyword", "%"+hospitalSearchDTO.getKeyword()+"%")
                .setParameter("sigudongId", hospitalSearchDTO.getSigungu())
                .setFirstResult(pagingDTO.getPage())
                .setMaxResults(pagingDTO.getLimit())
                .getResultList();
    }

    public Long countSearchHospital(String keyword) {
        return em.createQuery("select count(h) from Hospital h " +
                " where h.hosName LIKE :keyword", Long.class)
                .setParameter("keyword", "%"+keyword+"%")
                .getSingleResult();
    }

    /*동네 검색*/
    public List<Hospital> findByHosAddressContaining(String keyword){
        return null;
    }

    public void remove(Hospital hospital) {
        em.remove(hospital);
    }
}
