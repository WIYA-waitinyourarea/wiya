package com.teamwiya.wiya.util;

import com.teamwiya.wiya.hospital.dto.HospitalSaveForm;
import com.teamwiya.wiya.hospital.model.Address;
import com.teamwiya.wiya.hospital.model.HosImg;
import com.teamwiya.wiya.hospital.model.Hospital;
import com.teamwiya.wiya.hospital.model.Sigudong;
import com.teamwiya.wiya.hospital.repository.HosImgRepository;
import com.teamwiya.wiya.hospital.repository.HospitalRepository;
import com.teamwiya.wiya.hospital.repository.SigudongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@RequiredArgsConstructor
@Service
public class CrawlingService {

    private final HospitalRepository hospitalRepository;
    private final HosImgRepository hosImgRepository;
    private final SigudongRepository sigudongRepository;

    @Transactional
    public void save(HashMap<String, String> hospitalData) {
        try{

            HospitalSaveForm hospitalSaveForm = new HospitalSaveForm();
            hospitalSaveForm.setHosName(hospitalData.get("hosName"));
            hospitalSaveForm.setJibunAddress(hospitalData.getOrDefault("hosAddress", null));
            hospitalSaveForm.setHosOpenHour("평일 : 09:00 ~ 18:00");
            hospitalSaveForm.setHosPhone(hospitalData.getOrDefault("hosPhone", null));

            // 병원 엔티티에 임베디드 타입인 주소 생성
            Address address = null;
            Sigudong gu = null;

            address = Address.createAddress(hospitalSaveForm.getJibunAddress(), hospitalSaveForm.getSangse());
            gu = sigudongRepository.findById((address.getBCode()/100000)*100000);
            // 병원 엔티티 생성
            Hospital hospital = Hospital.createHospital(
                    hospitalSaveForm,
                    address,
                    gu);
            // 병원을 저장 (병원 영속성 시작)
            hospitalRepository.save(hospital);
            // 병원 이미지 저장
            if (hospitalData.containsKey("himPath")) {
                HosImg hosImg = HosImg.createHosImgForCrawling(hospital, hospitalData.get("himPath"));
                hosImgRepository.save(hosImg);
            }
        } catch (Exception e) {
            System.out.println("주소 안들어감");
        }
    }
}
