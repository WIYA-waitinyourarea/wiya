package com.teamwiya.wiya.hospital.controller;

import com.teamwiya.wiya.hospital.dto.*;
import com.teamwiya.wiya.hospital.model.Hospital;
import com.teamwiya.wiya.hospital.repository.HospitalRepository;
import com.teamwiya.wiya.hospital.repository.SigudongRepository;
import com.teamwiya.wiya.hospital.service.HospitalService;
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
        return "hospital/newform";
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
        return "hospital/editform";
    }

    @PostMapping("/edit/{hospitalId}")
    public String edit(
            @Validated @ModelAttribute("hospitalUpdateForm") HospitalUpdateForm hospitalUpdateForm,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        log.info("form={}", hospitalUpdateForm);
        if(bindingResult.hasErrors()) return "hospital/edit-form";

        hospitalService.updateHospital(hospitalUpdateForm);
        redirectAttributes.addAttribute("hospitalId", hospitalUpdateForm.getHosId());
        redirectAttributes.addAttribute("status","update");
        return "redirect:/hospital/detail/{hospitalId}";
    }

    @GetMapping("/delete/{hospitalId}")
    public String deleteForm(
            @PathVariable Long hospitalId
    ) {
        return "hospital/deleteForm";
    }

    @PostMapping("/delete/{hospitalId}")
    public String delete(
            @PathVariable Long hospitalId
    ) {
        hospitalService.deleteHospital(hospitalId);
        return "redirect:/hospital/list";
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

    @GetMapping("/list")
    public String search(
            @ModelAttribute HospitalSearchRequestDTO hospitalSearchRequestDTO,
            @ModelAttribute PagingDTO pagingDTO,
            Model model){
        log.info("검색 요청 = {}", hospitalSearchRequestDTO);
        HospitalSearchResponseDTO result
                = hospitalService.searchHospital(hospitalSearchRequestDTO, pagingDTO);

        model.addAttribute("result", result);
        model.addAttribute("pages", makePages(pagingDTO, result.getTotalResultCount()));
        model.addAttribute("sidos", hospitalService.allSidos());
        return "hospital/list";
    }

    private List<Integer> makePages(
            PagingDTO pagingDTO,
            long totalCount
    ) {
        //총 페이지 구하는 식
        int totalPages = (int) (totalCount / pagingDTO.getLimit());
        if (totalCount % pagingDTO.getLimit() != 0) {
            totalPages = totalPages + 1;
        }
        //첫페이지
        int startPage = ((pagingDTO.getPage() - 1) / pagingDTO.getPages()) * pagingDTO.getPages() + 1;
        //끝페이지
        int endPage = ((pagingDTO.getPage() - 1) / pagingDTO.getPages() + 1) * pagingDTO.getPages();
        if (endPage > totalPages) {
            endPage = totalPages;
        }
        // 페이지 리스트에 담기
        ArrayList<Integer> pages = new ArrayList<>();
        for (int page = startPage; page <= endPage ; page++) {
            pages.add(page);
        }
        return pages;
    }

}
