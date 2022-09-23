package com.teamwiya.wiya.controller;

import com.teamwiya.wiya.model.Member;
import com.teamwiya.wiya.model.MemberSaveForm;
import com.teamwiya.wiya.repository.MemberRepository;
import com.teamwiya.wiya.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    private final MemberRepository memberRepository;

    @GetMapping("/member/register") /*회원가입 창 이동*/
    public String registerForm(Model model) {
        model.addAttribute("memberFormDTO", new MemberSaveForm());
        return "register";
    }

    @PostMapping("/member/register") /*회원가입 */
    public String register(Member member) {
        memberService.register(member);
        return "redirect:/member/login";
    }

    @GetMapping("/member/login")
    public String login() {
        return "login";
    }

    @PostMapping("/memMailCheck")
    @ResponseBody //바디에 담아서 넘기겠다 , 리턴자료형은 스트링
    /*public String memMailCheck(@RequestParam("memMail") String memMail) {*/
    public String memMailCheck(Member member) {
        List<Member> checkmail = memberRepository.findByEmail(member.getMemMail());
        System.out.println("controller"+ checkmail);
        log.info("list={}",checkmail);
        if(checkmail.isEmpty()){
            return "T";
        }else if(!checkmail.isEmpty()) {
            return "F";
        }else{
            throw new IllegalStateException("새로운 이메일을 입력하세요");
        }
    };

/*

    @GetMapping("/member/{memMail}/exists")
    public ResponseEntity<Boolean> duplicateMailCheck(@RequestParam String memMail){
        return ResponseEntity.ok(memberService.overlappedMail(memMail));
    }
*/
/*
    @GetMapping("/member/list")
    public String memberList(Model model){
        model.addAttribute("list", memberService.memberList());
        return "registerSuccess";
    }
*/



/*
    @PostMapping("/member/register")
    public String memberList(Model model, Member member){
        memberService.register(member);
        model.addAttribute("list", memberService.memberList());
        return "registerSuccess";
    }
*/
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
