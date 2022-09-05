package com.teamwiya.wiya.service;

import com.teamwiya.wiya.dto.HospitalNewForm;
import com.teamwiya.wiya.model.Address;
import com.teamwiya.wiya.model.HosImg;
import com.teamwiya.wiya.model.Hospital;
import com.teamwiya.wiya.repository.HosImgRepository;
import com.teamwiya.wiya.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class HospitalService {
    @Value("${file.dir}")
    private static String fileDir;

    private final HospitalRepository hospitalRepository;
    private final HosImgRepository hosImgRepository;

    /**
     * 병원 등록하는 로직
     * @param hospitalNewForm
     * @return 병원 등록한 후 해당 아이디 반환
     */
    public Long registerHos(HospitalNewForm hospitalNewForm) {
        log.info("file.dir={}", fileDir);
        // 병원 등록하기 전 검증해야될 내용은 없을까?
        // 병원 엔티티를 만드는 내용
        Address address = new Address();
        Hospital hospital = Hospital.createHospital(
                hospitalNewForm.getHosName(),
                hospitalNewForm.getHosPhone(),
                address,
                hospitalNewForm.getHosOpenHour()
        ); // 이 시점에 엔티티 생성
        // 병원을 저장하는 내용
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
    public Hospital findHospital(Long hospitalId) {
        return hospitalRepository.findOne(hospitalId);
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

    public void registerHosImgs(Hospital hospital, List<MultipartFile> files) {
        for (MultipartFile file : files) {
            HosImg hosImg = HosImg.createHosImg(hospital, file);
            hosImgRepository.save(hosImg);
        }
    }
}
