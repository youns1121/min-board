package com.minboard.controller;

import com.minboard.dto.MemberDto;
import com.minboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/success")
    public String memberSuccess(){

        return "html/member/loginSuccess.html";
    }

    @GetMapping("/fail")
    public String memberFail(){

        return "html/member/loginFail.html";
    }

    @GetMapping("/siginin")
    public String memberSiginIn(Model model){


        model.addAttribute("siginin", new MemberDto());


        return "html/member/memberSiginInForm";
    }

//    @PostMapping("/siginin")
//    public String memberSiginIn(@ModelAttribute("siginin") MemberDto memberDto, BindingResult bindingResult){
//
//        memberService.isLoginMember(memberDto);
//
//        if(bindingResult.hasErrors()){
//            log.info("errors={}", bindingResult);
//            return "html/member/memberSiginInForm";
//        }
//
//        return "redirect:/board/category";
//    }

    @GetMapping("/siginup")
    public String memberSiginUp(Model model){

        model.addAttribute("siginup", new MemberDto());

        return "html/member/memberSiginUpForm";
    }

    @PostMapping("/siginup")
    public String memberSiginUp(@Validated @ModelAttribute("siginup") MemberDto memberDto, BindingResult bindingResult,
                                Model model){

        if (bindingResult.hasErrors()) {

            log.info("errors={}", bindingResult);
            return "html/member/memberSiginUpForm";
        }

        try {
            memberService.insertMember(memberDto, passwordEncoder);
        }catch (IllegalStateException e){
            model.addAttribute("errors", e);
            return "html/member/memberSiginUpForm";
        }

        return "redirect:/admin";
    }

    @GetMapping("/duplicate/check/member")
    @ResponseBody
    public String getDuplicateCheckMember(String userName){

//        memberService.validateDuplicateMember(m);
        return "name";
    }


}
