package com.minboard.controller;

import com.minboard.dto.BoardAdminDto;
import com.minboard.dto.BoardDto;
import com.minboard.service.impl.BoardAdminServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class BoardAdminController {

    private final BoardAdminServiceImpl boardAdminService;

    @GetMapping
    public String boardAdminList(BoardDto boardDto, Model model){

        List<BoardDto> boardList = boardAdminService.selectBoardAdminList(boardDto);
        model.addAttribute("boardList", boardList);

        return "html/boardAdmin";
    }

    @GetMapping("/{id}")
    public String boardAdminDetails(@PathVariable("id") int id, Model model){

        BoardAdminDto selectBoardAdmin = boardAdminService.selectBoardAdmin(id);
        model.addAttribute("selectBoardAdmin", selectBoardAdmin);
        return "html/boardAdminDetails";
    }



}
