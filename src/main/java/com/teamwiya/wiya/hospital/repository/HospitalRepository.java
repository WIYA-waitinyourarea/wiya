package com.teamwiya.wiya.hospital.repository;

import com.teamwiya.wiya.hospital.dto.HospitalSearchRequestDTO;
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
            HospitalSearchRequestDTO hospitalSearchRequestDTO,
            PagingDTO pagingDTO
    ) {
        return em.createQuery("select h from Hospital h" +
                        " where h.hosName LIKE :keyword", Hospital.class)
                .setParameter("keyword", "%"+ hospitalSearchRequestDTO.getKeyword()+"%")
                .setFirstResult(pagingDTO.getOffset())
                .setMaxResults(pagingDTO.getLimit())
                .getResultList();
    }

    public List<Hospital> findByHosNameAndSi(
            HospitalSearchRequestDTO hospitalSearchRequestDTO,
            PagingDTO pagingDTO
    ) {
        return em.createQuery("select h " +
                        " from Hospital h LEFT JOIN Sigudong s " +
                        " ON h.hosSigudong.sigudongId = s.sigudongId" +
                        " WHERE h.hosName LIKE :keyword " +
                        " AND (s.parent.sigudongId = :sigudongId" +
                        " OR s.sigudongId = :sigudongId) ", Hospital.class)
                .setParameter("keyword", "%"+ hospitalSearchRequestDTO.getKeyword()+"%")
                .setParameter("sigudongId", hospitalSearchRequestDTO.getSido())
                .setFirstResult(pagingDTO.getOffset())
                .setMaxResults(pagingDTO.getLimit())
                .getResultList();
    }

    public List<Hospital> findByHosNameAndGu(
            HospitalSearchRequestDTO hospitalSearchRequestDTO,
            PagingDTO pagingDTO
    ) {
        return em.createQuery("select h from Hospital h" +
                        " where h.hosName LIKE :keyword" +
                        " and h.hosSigudong.sigudongId = :sigudongId", Hospital.class)
                .setParameter("keyword", "%"+ hospitalSearchRequestDTO.getKeyword()+"%")
                .setParameter("sigudongId", hospitalSearchRequestDTO.getSigungu())
                .setFirstResult(pagingDTO.getOffset())
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

    public List<Hospital> search(HospitalSearchRequestDTO hospitalSearchRequestDTO, PagingDTO pagingDTO) {
        if (hospitalSearchRequestDTO.getSido() == 0L){ // 지역 없이 이름으로만 검색
            return findByHosName(hospitalSearchRequestDTO, pagingDTO);
        }
        if (hospitalSearchRequestDTO.getSigungu() == 0L) { // 시,도까지 포함한 검색
            return findByHosNameAndSi(hospitalSearchRequestDTO, pagingDTO);
        }
        // 시, 군, 구를 포함한 검색
        return findByHosNameAndGu(hospitalSearchRequestDTO, pagingDTO);
    }
}
