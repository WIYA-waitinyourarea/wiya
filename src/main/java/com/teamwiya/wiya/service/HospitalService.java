package com.teamwiya.wiya.service;

import com.teamwiya.wiya.model.Hospital;
import com.teamwiya.wiya.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    /**
     * 병원 등록하는 로직
     * @param hospital
     * @return 병원 등록한 후 해당 아이디 반환
     */
    public Long registerHos(Hospital hospital) {
        // 병원 등록하기 전 검증해야될 내용은 없을까?
        hospitalRepository.save(hospital);
        return hospital.getHosId();
    }

    /**
     * UD 메소드 필요
     */

    /**
     * 특정 병원에 대한 정보
     * @param hospitalId
     * @return Hospital
     */
    @Transactional(readOnly = true)
    public Optional<Hospital> findHospital(Long hospitalId) {
        return hospitalRepository.findById(hospitalId);
    }

    /**
     * 병원 이름으로 검색하는 메소드
     * @param keyword
     * @return List<Hospital>
     */
    @Transactional(readOnly = true) //검석어 테이블 인서트 시, 삭제 필요
   public List<Hospital> searchHospital(String keyword) {
        //아직 검색어 로그는 엔티티 만들지 않음
        return hospitalRepository.findByHosNameContaining(keyword);
    }

}
