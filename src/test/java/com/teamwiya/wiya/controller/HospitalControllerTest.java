package com.teamwiya.wiya.controller;

import com.teamwiya.wiya.model.Hospital;
import com.teamwiya.wiya.repository.HospitalRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
@Transactional
class HospitalControllerTest {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Test
    void 새병원등록하기() {
        //given
        Hospital hospital = new Hospital();
        hospital.setHosName("Ham");

        //when
        Hospital savedHospital = hospitalRepository.save(hospital);
        //then
        Optional<Hospital> findHospital = hospitalRepository.findById(savedHospital.getHosId());
        assertThat(findHospital.get().getHosName()).isEqualTo(savedHospital.getHosName());
    }
}