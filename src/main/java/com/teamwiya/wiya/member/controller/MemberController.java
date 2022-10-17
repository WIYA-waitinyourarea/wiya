package com.teamwiya.wiya.member.controller;

import com.teamwiya.wiya.config.SessionConst;
import com.teamwiya.wiya.member.dto.MemberLoginDTO;
import com.teamwiya.wiya.member.model.Member;
import com.teamwiya.wiya.member.repository.MemberRepository;
import com.teamwiya.wiya.member.dto.MemberSaveForm;
import com.teamwiya.wiya.member.service.MemberService;
import com.teamwiya.wiya.util.SendingMail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final SendingMail sendingMail;

    @GetMapping("/member/register") /*회원가입 창 이동*/
    public String registerForm(
            @ModelAttribute("memberSaveForm") MemberSaveForm memberSaveForm
    ) {
        return "member/register";
    }

    @PostMapping("/member/register") /*회원가입 */
    public String register(
            @Validated @ModelAttribute("memberSaveForm") MemberSaveForm memberSaveForm,
            BindingResult bindingResult,
            HttpServletRequest request
    ) {
        //검증
        HttpSession session = request.getSession();
        String check = (String) session.getAttribute(SessionConst.REGISTER_EMAIL);
        if(check == null || !check.equals(memberSaveForm.getMemMailCheck())){
            bindingResult.rejectValue("memMailCheck", "check", "인증번호 다시 입력");
        }
        if(bindingResult.hasErrors()) return "member/register";

        memberService.register(memberSaveForm);

        return "redirect:/member/login";
    }

    @GetMapping("/member/login")  /*로그인 창 이동*/
    public String loginForm() {
        return "member/login";
    }


   @PostMapping("/member/login") /*로그인 시도 */
    public String login(
            @ModelAttribute MemberLoginDTO memberLoginDTO,
            HttpServletRequest request,
            @RequestParam(defaultValue = "/") String redirectURL
   ) {
        log.info("redirectURL={}", redirectURL);
        Member loginResult = memberService.login(memberLoginDTO);
        if(loginResult != null){ //로그인 성공 시 메인페이지로
            HttpSession session = request.getSession(true);
            session.setAttribute(SessionConst.LOGIN_EMAIL, loginResult);
            return "redirect:"+redirectURL;
        }else{ //로그인 실패 시 다시 로그인
            return "member/login";
            //return "redirect:/member/loginForm";
        }
    }


    @PostMapping("/member/logout")
    public String logout(
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession(false);
        session.invalidate();
        return "redirect:/";
    }


    @PostMapping("/mailcheck")
    @ResponseBody //바디에 담아서 넘기겠다 , 리턴자료형은 스트링
    public String memMailCheck(@RequestParam("memMail") String memMail) {
        List<Member> checkmail = memberRepository.findByEmail(memMail);
        log.info("list={}",checkmail);
        if(checkmail.isEmpty()){
            return "T";
        }else if(!checkmail.isEmpty()) {
            return "F";
        }else{
            throw new IllegalStateException("새로운 이메일을 입력하세요");
        }
    }

    /*나중에 여기 JSON으로 바꾸고싶음*/
    @GetMapping("/member/sendmail")
    @ResponseBody
    public String sendMail(
            @RequestParam("memMail") String memMail,
            HttpServletRequest request
    ) {
        System.out.println("member.getMemMail() = " + memMail);
        HttpSession session = request.getSession();
        String check = sendingMail.MailCheck(memMail);
        session.setAttribute(SessionConst.REGISTER_EMAIL, check);
        return check;
    }

}
