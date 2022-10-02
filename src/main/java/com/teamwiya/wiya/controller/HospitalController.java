package com.teamwiya.wiya.controller;

import com.teamwiya.wiya.dto.HospitalSaveForm;
import com.teamwiya.wiya.dto.HospitalUpdateForm;
import com.teamwiya.wiya.model.Hospital;
import com.teamwiya.wiya.repository.HospitalRepository;
import com.teamwiya.wiya.repository.SigudongRepository;
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

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/hospital")
public class HospitalController {

    //기본적으로 레파지토리는 서비스계층에서 접근하는게 일반적이지만 유연하게 설계
    private final HospitalRepository hospitalRepository;
    private final HospitalService hospitalService;
    private final SigudongRepository sigudongRepository;

    /**
     * @return 뷰템플릿 논리명
     */
    @GetMapping("/add")
    public String addForm(Model model){
        model.addAttribute("hospitalSaveForm", new HospitalSaveForm());
        return "hospital/new-form";
    }

    /**
     * @param hospitalSaveForm 으로 넘긴 요청 파라미터들로 Hospital 객체를 만듦 > 모델에 저장
     * @return 저장한 병원의 상세페이지에 대한 논리이름을 반환
     */
    @PostMapping(value = "/add")
    public String add(
            @Validated @ModelAttribute("hospitalSaveForm") HospitalSaveForm hospitalSaveForm,
            BindingResult bindingResult,
            @RequestParam List<MultipartFile> hosImgs,
            RedirectAttributes redirectAttributes
    ) {
        //바인딩 에러나면 다시 폼으로 돌리기
        if(bindingResult.hasErrors()) return "hospital/new-form";

        /*병원 저장*/
        Long hospitalId = hospitalService.registerHos(hospitalSaveForm, hosImgs);

        Hospital hospital = hospitalRepository.findOne(hospitalId);
        redirectAttributes.addAttribute("hospitalId", hospitalId);
        redirectAttributes.addAttribute("status","create");
        return "redirect:/hospital/detail/{hospitalId}";

    }


    @GetMapping("/edit/{hospitalId}")
    public String editForm(@PathVariable Long hospitalId, Model model) {
        Hospital hospital = hospitalService.findHospital(hospitalId);
        HospitalUpdateForm form = new HospitalUpdateForm();
        form.setHosId(hospital.getHosId());
        form.setHosName(hospital.getHosName());
        form.setHosPhone(hospital.getHosPhone());
        form.setJibunAddress(hospital.getHosAddress().getJibunAddress());
        form.setSangse(hospital.getHosAddress().getSangse());
        form.setHosImgsBefore(hospital.getHosImgs());
        model.addAttribute("hospitalUpdateForm", form);
        return "hospital/edit-form";
    }

    @PostMapping("/edit/{hopitalId}")
    public String edit(
            @Validated @ModelAttribute("hospitalUpdateForm") HospitalUpdateForm hospitalUpdateForm,
            BindingResult bindingResult,
            @RequestParam List<MultipartFile> hosImgs,
            RedirectAttributes redirectAttributes
    ) {
        log.info("form={}", hospitalUpdateForm);
        //바인딩 에러나면 다시 폼으로 돌리기
        if(bindingResult.hasErrors()) return "hospital/edit-form";

        hospitalService.updateHopital(hospitalUpdateForm, hosImgs);
        redirectAttributes.addAttribute("hospitalId", hospitalUpdateForm.getHosId());
        redirectAttributes.addAttribute("status","update");
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

    @GetMapping("/search")
    public String search(
            @RequestParam(name = "keyword", defaultValue = "") String keyword,
            @RequestParam(defaultValue = "1") int page,
            Model model){
        // 모델에 리스트(검색결과)를 추가하는 작업
        List<Hospital> searchResult = hospitalService.searchHospital(keyword, page);
        long totalCount = hospitalRepository.countSearchHospital(keyword);
        int totalPages = (int) (totalCount % 4 == 0? totalCount / 4 : totalCount / 4 + 1);
        ArrayList<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= totalPages ; i++) {
            pages.add(i);
        }
        model.addAttribute("sigudongs", sigudongRepository.findAll());
        model.addAttribute("pages", pages);
        model.addAttribute("searchResult",searchResult);
        return "hospital/search";
    }

}
