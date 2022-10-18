package com.minboard.controller;

import com.minboard.dto.request.BoardRequestDto;
import com.minboard.service.BoardAdminService;
import com.minboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/")
@RequiredArgsConstructor
@Controller
public class MainController {

    private final BoardService boardService;
    private final BoardAdminService boardAdminService;

    @GetMapping("/")
    public String home(Model model, BoardRequestDto requestDto){

        model.addAttribute("boardList", boardService.getBoardList(requestDto));
        model.addAttribute("categoryList", boardAdminService.getBoardCategoryList());
        return "html/main";
    }

    @GetMapping("/header")
    public String boardHeader(Model model){

        model.addAttribute("categoryList", boardAdminService.getBoardCategoryList());
        return "html/header/boardHeader";
    }
}
