package com.teamwiya.wiya.service;

import com.teamwiya.wiya.dto.HospitalSaveForm;
import com.teamwiya.wiya.dto.HospitalUpdateForm;
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
     * @param hospitalSaveForm
     * @return 병원 등록한 후 해당 아이디 반환
     */
    public Long registerHos(HospitalSaveForm hospitalSaveForm, List<MultipartFile> files) {

        Address address = Address.createAddress(hospitalSaveForm.getJibunAddress(), hospitalSaveForm.getSangse()); //임베디드 타입
        // 병원 엔티티 생성
        Hospital hospital = Hospital.createHospital(
                hospitalSaveForm.getHosName(),
                hospitalSaveForm.getHosPhone(),
                address,
                hospitalSaveForm.getHosOpenHour()
        );
        // 병원을 저장 (병원 영속성 시작)
        hospitalRepository.save(hospital);
        // 병원 이미지 저장
        for (MultipartFile file : files) {
            HosImg hosImg = HosImg.createHosImg(hospital, file);
            hosImgRepository.save(hosImg);
        }
        return hospital.getHosId();
    }

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


    public void updateHopital(HospitalUpdateForm hospitalUpdateForm, List<MultipartFile> files) {
        Hospital hospital = hospitalRepository.findOne(hospitalUpdateForm.getHosId());
        /*기존 사진 중 삭제된 사진이 있는 경우*/
        hospital.update(hospitalUpdateForm);
        if(hospital.getHosImgs().size() != hospitalUpdateForm.getHosImgsAfter().size()){
            for (int i = 0; i < hospital.getHosImgs().size(); i++) {
                if(!hospitalUpdateForm.getHosImgsAfter().contains(hospital.getHosImgs().get(i).getHimId())) {
                    hospital.getHosImgs().remove(i--);
                }
            }
        }
        /*새로등록된 이미지 저장*/
       for (MultipartFile file : files) {
            HosImg hosImg = HosImg.createHosImg(hospital, file);
            hosImgRepository.save(hosImg);
        }

    }

}
