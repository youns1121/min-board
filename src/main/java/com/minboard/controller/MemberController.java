package com.minboard.controller;

import com.minboard.service.MemberService;
import com.minboard.vo.MemberSaveVo;
import com.minboard.vo.MemberVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;


    /** 회원가입 페이지 **/
    @GetMapping("/signup")
    public String getFormMemberSignUp(@ModelAttribute("memberSaveVo") MemberSaveVo memberSaveVo){
        return "html/signUpForm";
    }

    /** 회원가입 하기 **/
    @PostMapping("/signup")
    public String signUpMember(@Validated @ModelAttribute("memberSaveVo") MemberSaveVo memberSaveVo, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            return "html/signUpForm";
        }
        memberService.createMember(memberSaveVo);
        redirectAttributes.addAttribute("status", true);
        return "redirect:/board/list";
    }

    @PostMapping("/signin")
    public String signInMember(@ModelAttribute("memberVo") MemberVo memberVo){

        return "redirect:/board/list";
    }

    @GetMapping("/signin")
    public String getFormMemberSignIn(@ModelAttribute("memberVo") MemberVo memberVo){

        return "html/signInForm";
    }
}
