package com.minboard.controller;

import com.minboard.dto.MemberDto;
import com.minboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;


    @GetMapping("/siginup")
    public String memberSiginUp(Model model){

        model.addAttribute("member", new MemberDto());

        return "html/signup/memberSiginUpForm";
    }


}
