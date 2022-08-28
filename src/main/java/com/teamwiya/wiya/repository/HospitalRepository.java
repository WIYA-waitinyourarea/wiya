package com.teamwiya.wiya.repository;

import com.teamwiya.wiya.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

//스프링에서 자동으로 프록시 -> 빈객체 생성해줌
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

}
