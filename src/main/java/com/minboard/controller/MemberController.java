package com.minboard.controller;

import com.minboard.dto.BoardDto;
import com.minboard.dto.MemberDto;
import com.minboard.service.MemberService;
import com.minboard.vo.member.MemberSaveVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;


    @GetMapping("/siginup")
    public String memberSiginUp(Model model){

        model.addAttribute("siginup", new MemberDto());

        return "html/signup/memberSiginUpForm";
    }

    @PostMapping("/siginup")
    public String memberSiginUp(@Validated @ModelAttribute("siginup") MemberDto memberDto, BindingResult bindingResult){


        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "html/signup/memberSiginUpForm";
        }

        memberService.insertMember(memberDto, passwordEncoder);


        return "redirect:/admin";
    }


}
