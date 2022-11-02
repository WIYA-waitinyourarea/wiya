package com.teamwiya.wiya.hospital.service;

import com.teamwiya.wiya.hospital.dto.HospitalSaveForm;
import com.teamwiya.wiya.hospital.dto.HospitalUpdateForm;
import com.teamwiya.wiya.hospital.model.Address;
import com.teamwiya.wiya.hospital.model.HosImg;
import com.teamwiya.wiya.hospital.model.Hospital;
import com.teamwiya.wiya.hospital.model.Sigudong;
import com.teamwiya.wiya.hospital.repository.HosImgRepository;
import com.teamwiya.wiya.hospital.repository.HospitalRepository;
import com.teamwiya.wiya.hospital.repository.SigudongRepository;
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
    private final SigudongRepository sigudongRepository;

    public Long registerHos(HospitalSaveForm hospitalSaveForm, List<MultipartFile> files) {
        // 병원 엔티티에 임베디드 타입인 주소 생성
        Address address = Address.createAddress(hospitalSaveForm.getJibunAddress(), hospitalSaveForm.getSangse());
        Sigudong gu = sigudongRepository.findById((address.getBCode()/100000)*100000);
        // 병원 엔티티 생성
        Hospital hospital = Hospital.createHospital(
                hospitalSaveForm,
                address,
                gu
        );
        // 병원을 저장 (병원 영속성 시작)
        hospitalRepository.save(hospital);
        // 병원 이미지 저장
        for (MultipartFile file : files) {
            if(!file.isEmpty()) {
                HosImg hosImg = HosImg.createHosImg(hospital, file);
                hosImgRepository.save(hosImg);
            }
        }
        return hospital.getHosId();
    }





    public void updateHospital(HospitalUpdateForm hospitalUpdateForm) {
        Hospital hospital = hospitalRepository.findOne(hospitalUpdateForm.getHosId());
        Address address = hospital.getHosAddress();
        Sigudong gu = sigudongRepository.findById(address.getBCode());
        if(hospital.getHosAddress().getJibunAddress().equals(hospitalUpdateForm.getJibunAddress()) // 지번주소가 달라졌거나
                || !hospital.getHosAddress().getSangse().equals(hospitalUpdateForm.getSangse())){ //상세주소가달라졌으면
            address = Address.createAddress(hospitalUpdateForm.getJibunAddress(), hospitalUpdateForm.getSangse());
            gu = sigudongRepository.findById((address.getBCode()/100000)*100000);
        }
        hospital.update(hospitalUpdateForm, address, gu);

        if(hospital.getHosImgs().size() != hospitalUpdateForm.getHosImgsAfter().size()){
            for (int i = 0; i < hospital.getHosImgs().size(); i++) {
                Long targetId = hospital.getHosImgs().get(i).getHimId();
                if(!hospitalUpdateForm.getHosImgsAfter().contains(targetId)) {
                    HosImg targetImg = hosImgRepository.findById(targetId);
                    hosImgRepository.remove(targetImg);
                }
            }
        }
        /*새로등록된 이미지 저장*/
       for (MultipartFile file : hospitalUpdateForm.getHosImgs()) {
           if(!file.isEmpty()) {
               HosImg hosImg = HosImg.createHosImg(hospital, file);
               hosImgRepository.save(hosImg);
           }
        }
    }

    public void deleteHospital(Long hospitalId) {
        Hospital hospital = hospitalRepository.findOne(hospitalId);
        hospitalRepository.remove(hospital);
    }

    @Transactional(readOnly = true)
    public Hospital findHospital(Long hospitalId) {
        return hospitalRepository.findOne(hospitalId);
    }

    @Transactional(readOnly = true)
    public List<Hospital> searchHospital(String keyword, int page) {
        int limit = 4;
        int offset = (page-1)*limit;
        log.info("offset={}",offset);
        return hospitalRepository.findByHosNamePage(keyword, offset, limit);
        //return hospitalRepository.findByHosNameContaining(keyword);
    }

}
