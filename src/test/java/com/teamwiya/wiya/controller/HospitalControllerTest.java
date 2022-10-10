package com.teamwiya.wiya.controller;

import com.teamwiya.wiya.hospital.repository.HospitalRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
@Transactional
class HospitalControllerTest {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Test
    void 새병원등록하기() {
/*        //given
        Hospital hospital = new Hospital();
        hospital.setHosName("Ham");

        //when
        Hospital savedHospital = hospitalRepository.save(hospital);
        //then
        Optional<Hospital> findHospital = hospitalRepository.findById(savedHospital.getHosId());
        assertThat(findHospital.get().getHosName()).isEqualTo(savedHospital.getHosName());*/
    }
}