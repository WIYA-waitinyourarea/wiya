package com.teamwiya.wiya.controller;

import com.teamwiya.wiya.dto.MarketDTO;
import com.teamwiya.wiya.model.*;
import com.teamwiya.wiya.service.MarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MarketController {

    private final MarketService marketService;

    @GetMapping("/market/write") /*장터 글쓰기 페이지*/
    public String marketWrite(Model model) {
        model.addAttribute("MarketDTO", new MarketDTO());
        return "market/marketWriteForms";
    }

    @PostMapping("/market/write") /* 장터 게시글 작성 성공시 */
    public String postItem(Model model, @ModelAttribute MarketDTO marketDTO) {
        marketService.post(marketDTO);
        return "redirect:/market/list";
    }

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
