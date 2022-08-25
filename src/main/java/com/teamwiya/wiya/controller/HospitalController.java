package com.teamwiya.wiya.controller;

import com.teamwiya.wiya.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
//반환값으로 뷰를 찾는 것이 아니라, HTTP 메세지 바디에 바로 입력한다
@RestController
@RequestMapping("/hospital")
public class HospitalController {

    private final HospitalRepository hospitalRepository;

    @GetMapping("")
    public String serarch(){
        return "";
    }

}
