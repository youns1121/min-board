package com.minboard.controller;

import com.minboard.dto.BoardAdminSaveDto;
import com.minboard.dto.BoardAdminUpdateDto;
import com.minboard.dto.request.BoardAdminRequestDto;
import com.minboard.service.BoardAdminService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;




@Slf4j
@RequestMapping("/admin")
@RequiredArgsConstructor
@Controller
public class BoardAdminController {

    private final BoardAdminService boardAdminService;

    @GetMapping("/setting/update/{id}")
    public String boardAdminModify(@PathVariable("id") int id, Model model) {

        model.addAttribute("boardAdminUpdate", boardAdminService.getBoardAdmin(id));
        return "html/boardAdminEdit";
    }

    @PostMapping("/setting")
    public String boardAdminSetting(@Validated @ModelAttribute("boardAdmin") BoardAdminSaveDto boardAdminSaveDto,
                                    BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            return "html/boardAdminSetting";
        }
        boardAdminService.saveBoardAdminSetting(boardAdminSaveDto);
        return "redirect:/admin/setting/update/" + boardAdminSaveDto.getId();
    }


    @GetMapping("/setting")
    public String boardAdminSetting(Model model){

        model.addAttribute("boardAdmin", BoardAdminSaveDto.builder().build());
        return "html/boardAdminSetting";
    }

    @ResponseBody
    @PostMapping("/setting/update")
    public String boardAdminModify(@ModelAttribute("boardAdminUpdateVo") BoardAdminUpdateDto boardAdminUpdateDto ) {

        boardAdminService.modifyBoardAdminSetting(boardAdminUpdateDto);
        return boardAdminUpdateDto.getId().toString();
    }

    @GetMapping
    public String boardAdminList(Model model, BoardAdminRequestDto boardAdminRequestDto) {

        model.addAttribute("boardAdminList", boardAdminService.getBoardAdminList(boardAdminRequestDto));

        return "html/boardAdmin";
    }

    @ResponseBody
    @PostMapping("/remove")
    public void boardAdminRemove(int id) {

        boardAdminService.removeBoardAdmin(id);
    }
}
