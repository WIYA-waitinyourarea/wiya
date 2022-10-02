package com.teamwiya.wiya.controller;

import com.teamwiya.wiya.dto.MarketDTO;
import com.teamwiya.wiya.model.*;
import com.teamwiya.wiya.repository.MarketRepository;
import com.teamwiya.wiya.service.MarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MarketController {

    private final MarketService marketService;
    private final MarketRepository marketRepository;


    @GetMapping("/market/write") /*장터 글쓰기 페이지*/
    public String marketWrite(Model model) {
        model.addAttribute("MarketDTO", new MarketDTO());
        return "market/marketWriteForms";
    }

     @PostMapping("/market/write") /* 장터 게시글 작성 성공시 */
    public String postItem(Model model, @ModelAttribute MarketDTO marketDTO
                           ,MultipartFile file
            ) throws Exception {
        marketService.post(marketDTO, file);

        return "redirect:/market/list";
    }

/*    @PostMapping("/market/write") *//* 장터 게시글 작성 성공시 *//*
    public String postItem(Model model, @Validated @ModelAttribute("marketDTO") MarketDTO marketDTO
                           ,BindingResult bindingResult
                           ,@RequestParam List<MultipartFile> marImgs
                           //,RedirectAttributes redirectAttributes
                           //redirect 시에 객체 전달,
                           // 세션에는 브라우저 종료전까지 유지해야하는 데이터를 담아야하기 때문에 redirect 시 세션에 데이터를 담으면 안됨
    ) {
        Long postingId = marketService.post(marketDTO, marImgs);

        Market market = marketRepository.findOne(postingId); //글 아이



        return "redirect:/market/list";
    }*/

    @GetMapping("/market/list") /*장터 글 목록 페이지*/
    public String marketPostlist(Model model) {
        List<Market> posts = marketService.findPosts();
        model.addAttribute("posts", posts);
        return "/market/postList";
    }

    @GetMapping("/market/detail/{post_id}") /*글 상세 페이지*/
    public String postListdetail(Model model, @PathVariable Long post_id) {
        Market market = marketService.findOne(post_id);
        model.addAttribute("market", market);
        return "/market/postDetail";
    }








}
