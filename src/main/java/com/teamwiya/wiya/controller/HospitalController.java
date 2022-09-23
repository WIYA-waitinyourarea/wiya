package com.teamwiya.wiya.controller;

import com.teamwiya.wiya.dto.HospitalSaveForm;
import com.teamwiya.wiya.model.Hospital;
import com.teamwiya.wiya.repository.HospitalRepository;
import com.teamwiya.wiya.service.HospitalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/hospital")
public class HospitalController {

    //기본적으로 레파지토리는 서비스계층에서 접근하는게 일반적이지만 유연하게 설계
    private final HospitalRepository hospitalRepository;
    private final HospitalService hospitalService;

    /**
     * 병원 등록하는 폼을 보여주기 위한 겟방식 요청
     * @return 뷰템플릿 논리명
     */
    @GetMapping("/add")
    public String addForm(Model model){
        model.addAttribute("hospital", new HospitalSaveForm());
        return "hospital/new-form";
    }

    /**
     *
     * @param hospitalSaveForm 으로 넘긴 요청 파라미터들로 Hospital 객체를 만듦 > 모델에 저장
     * //@param model 뷰페이지 렌더링할떄 넘길 병원 정보를 받기 위한 모델 객체 >> 모델 어트리뷰트로 자동 애드
     * @return 저장한 병원의 상세페이지에 대한 논리이름을 반환
     */
    @PostMapping(value = "/add")
    public String add(
            @Validated @ModelAttribute("hospital") HospitalSaveForm hospitalSaveForm,
            BindingResult bindingResult,
            @RequestParam List<MultipartFile> hosImgs,
            RedirectAttributes redirectAttributes
    ) {
        //바인딩 에러나면 다시 폼으로 돌리기
        if(bindingResult.hasErrors()) return "hospital/new-form";

        /*이 두 메소드를 한 트랜잭셔널 안에서 처리해야되는데..*/
        /*병원 저장*/
        Long hospitalId = hospitalService.registerHos(hospitalSaveForm);
        /*병원 이미지 저장*/
        if (!hosImgs.isEmpty()) hospitalService.registerHosImgs(hospitalId, hosImgs);

        Hospital hospital = hospitalRepository.findOne(hospitalId);
        redirectAttributes.addAttribute("hospitalId", hospitalId);
        redirectAttributes.addAttribute("status",true);
        return "redirect:/hospital/detail/{hospitalId}";

    }


    @GetMapping("/detail/{hospitalId}")
    public String detail(@PathVariable Long hospitalId,
                         Model model) {
        Hospital hospital = hospitalService.findHospital(hospitalId);
        log.info("hospitalimg={}", hospital.getHosImgs().get(0).getHimPath());
        //여기서 널이면 예외를 발생시켜야할까??????
        model.addAttribute("hospital", hospital);
        return "hospital/detail";
    }

    @GetMapping("/search/{keyWord}")
    public String search(
            @PathVariable String keyWord,
            Model model){
        // 모델에 리스트(검색결과)를 추가하는 작업
        return "";
    }

    @GetMapping("/edit/{hospitalId}")
    public String edit(@PathVariable Long hospitalId, Model model) {
        Hospital hospital = hospitalService.findHospital(hospitalId);
        model.addAttribute("hospital", hospital);
        return "hospital/edit-form";
    }


}
