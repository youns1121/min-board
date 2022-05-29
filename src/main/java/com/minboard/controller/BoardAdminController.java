package com.minboard.controller;

import com.minboard.dto.BoardAdminDto;
import com.minboard.dto.BoardDto;
import com.minboard.service.BoardAdminService;
import com.minboard.vo.BoardAdminSaveVo;
import com.minboard.vo.BoardAdminUpdateVo;
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

    @GetMapping("/setting/update/{id}")
    public String boardAdminModify(@PathVariable("id") int id, Model model) {
        BoardAdminDto boardAdminDto = boardAdminService.getBoardAdmin(id);
        model.addAttribute("boardAdminUpdate", boardAdminDto);
        return "html/boardAdminEdit";
    }

    @ResponseBody
    @PostMapping("/setting/update")
    public String boardAdminModify(@ModelAttribute("boardAdminUpdateVo") BoardAdminUpdateVo boardAdminUpdateVo) {

        boardAdminService.modifyBoardAdminSetting(boardAdminUpdateVo);
        return boardAdminUpdateVo.getId().toString();
    }

    @PostMapping("/setting")
    public String boardAdminSetting(@ModelAttribute("boardAdmin") BoardAdminSaveVo boardAdminSaveVo){

        boardAdminService.saveBoardAdminSetting(boardAdminSaveVo);
        return "redirect:/admin/setting/update/" + boardAdminSaveVo.getId();
    }


    @GetMapping("/setting")
    public String boardAdminSetting(Model model, BoardAdminSaveVo boardAdminSaveVo){

        model.addAttribute("boardAdmin", boardAdminSaveVo);
        return "html/boardAdminSetting";
    }

    @GetMapping("/view/{id}")
    public String boardAdminDetails(@PathVariable("id") int id, Model model){

        BoardAdminDto boardAdminDto = boardAdminService.getBoardAdmin(id);
        model.addAttribute("boardAdmin", boardAdminDto);
        return "html/boardAdminDetail";
    }


    @GetMapping
    public String boardAdminList(BoardAdminDto boardAdminDto, Model model){

        List<BoardAdminDto> boardAdminDtoList = boardAdminService.getBoardAdminList(boardAdminDto);

        model.addAttribute("boardAdminList", boardAdminDtoList);


        return "html/boardAdmin";
    }

    @ResponseBody
    @PostMapping("/remove")
    public void boardAdminRemove(int id) {

        boardAdminService.removeBoardAdmin(id);
    }
}
