package com.teamwiya.wiya.controller;

import com.teamwiya.wiya.model.Hospital;
import com.teamwiya.wiya.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;


@RequiredArgsConstructor
@Controller
@RequestMapping("/hospital")
public class HospitalController {

    private final HospitalRepository hospitalRepository;

    /**
     * 병원 등록하는 폼을 보여주기 위한 겟방식 요청
     * @return
     */
    @GetMapping("/add")
    public String addForm(){
        return "hospital/new-form";
    }

    /**
     *
     * @param hospital 뉴폼에서 넘긴 요청 파라미터들로 Hospital 객체를 만듦 > 모델에 저장
     * //@param model 뷰페이지 렌더링할떄 넘길 병원 정보를 받기 위한 모델 객체 >> 모델 어트리뷰트로 자동 애드
     * @return 저장한 병원의 상세페이지에 대한 논리이름을 반환
     */
    @PostMapping("/add")
    public String add(@ModelAttribute("hospital") Hospital hospital,
                      RedirectAttributes redirectAttributes){
        Hospital savedHospital = hospitalRepository.save(hospital);
        redirectAttributes.addAttribute("hospitalId");
        //model.addAttribute("hospital", savedHospital);
        return "Redirect:/hospital/detail/{hospitalId}";
    }

    @GetMapping("/detail/{hospitalId}")
    public String detail(@PathVariable Long hospitalId,
                         Model model) {
        Optional<Hospital> hospital = hospitalRepository.findById(hospitalId);
        //여기서 널이면 예외를 발생시켜야할까??????
        model.addAttribute("hospital", hospital.orElse(null));
        return "hospital/detail";
    }

    @GetMapping("/search/{keyWord}")
    public String search(
            @PathVariable String keyWord,
            Model model){
        // 모델에 리스트(검색결과)를 추가하는 작업
        return "";
    }

}
