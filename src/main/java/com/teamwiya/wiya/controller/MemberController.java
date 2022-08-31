package com.teamwiya.wiya.controller;

import com.teamwiya.wiya.model.Member;
import com.teamwiya.wiya.model.MemberFormDTO;
import com.teamwiya.wiya.repository.MemberRepository;
import com.teamwiya.wiya.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/member/register")
    public String registerForm() {
        return "/registerform";
    }

    @PostMapping("/member/registersuccess")
    public String registerSuccess(Member member){
        memberService.register(member);
        return "";
    }

/*
    private final MemberService memberService;

    @PostMapping("/login_register")
    public String register(@ModelAttribute MemberFormDTO memberFormDTO){
        Member member = new Member();
        member.setMemName(memberFormDTO.getMemName());
        member.setMemMail(member.getMemMail());
        member.setMemNickname(member.getMemNickname());
        member.setMemPwd(member.getMemPwd());

        memberService.

        //memberService.join(member);
        return "/register";
    }
*/

}
