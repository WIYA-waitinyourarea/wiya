package com.teamwiya.wiya.controller;

import com.teamwiya.wiya.dto.MemberLoginDTO;
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
        return "redirect:/member/loginForm";
    }

    @GetMapping("/member/loginForm")  /*로그인 창 이동*/
    public String loginForm() {
        return "login";
    }


   @PostMapping("/login") /*로그인 시도 */
    public String login(@ModelAttribute MemberLoginDTO memberLoginDTO) {
        MemberLoginDTO loginResult = memberService.login(memberLoginDTO);
        if(loginResult != null){ //로그인 성공 시 메인페이지로
            return "redirect:";
        }else{ //로그인 실패 시 다시 로그인
            return "redirect:/member/loginForm";
        }
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


}
