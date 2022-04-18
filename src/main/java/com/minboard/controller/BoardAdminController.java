package com.minboard.controller;

import com.minboard.dto.BoardAdminDto;
import com.minboard.dto.BoardDto;
import com.minboard.service.BoardAdminService;
import com.minboard.vo.BoardAdminSaveVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/admin")
@RequiredArgsConstructor
@Controller
public class BoardAdminController {

    private final BoardAdminService boardAdminService;






    @ResponseBody
    @PostMapping("/new")
    public String boardAdminSave(@ModelAttribute("boardAdmin") BoardAdminSaveVo boardAdminSaveVo){

        boardAdminService.saveBoardAdmin(boardAdminSaveVo);
        return boardAdminSaveVo.getId().toString();
    }

    @GetMapping("/new")
    public String boardAdminSave(Model model, BoardAdminSaveVo boardAdminSaveVo, BoardAdminDto boardAdminDto){

        List<BoardAdminDto> boardAdminDtoList = boardAdminService.selectBoardAdminList(boardAdminDto);
        model.addAttribute("boardCategory", boardAdminDtoList);
        model.addAttribute("boardAdmin", boardAdminSaveVo);
        return "html/boardAdminNew";
    }


    @ResponseBody
    @PostMapping("/setting")
    public String boardAdminSetting(@ModelAttribute("boardAdmin") BoardAdminSaveVo boardAdminSaveVo){

        boardAdminService.saveBoardAdminSetting(boardAdminSaveVo);
        return boardAdminSaveVo.getId().toString();
    }

    @GetMapping("/setting")
    public String boardAdminSetting(Model model, BoardAdminSaveVo boardAdminSaveVo){

        model.addAttribute("boardAdmin", boardAdminSaveVo);
        return "html/boardAdminSetting";
    }

    @GetMapping("/view/{id}")
    public String boardAdminDetails(@PathVariable("id") int id, Model model){

        BoardAdminDto boardAdminDto = boardAdminService.selectBoardAdmin(id);
        model.addAttribute("boardAdmin", boardAdminDto);
        return "html/boardAdminDetail";
    }


    @GetMapping
    public String boardAdminList(BoardAdminDto boardAdminDto, Model model){

        List<BoardAdminDto> boardAdminDtoList = boardAdminService.selectBoardAdminList(boardAdminDto);
        model.addAttribute("boardAdminList", boardAdminDtoList);

        return "html/boardAdmin";
    }






}
