package com.minboard.controller;


import com.minboard.dto.BoardDto;
import com.minboard.service.BoardService;
import com.minboard.vo.BoardSaveVo;
import com.minboard.vo.BoardUpdateVo;
import com.minboard.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    /** 게시물 생성페이지 **/
    @GetMapping("/new")
    public String formBoard(Model model, BoardVo boardVo) {
        model.addAttribute("board", boardVo.builder().build());
        return "html/boardNew";
    }

    /** 게시물 생성하기 **/
    @PostMapping("/new")
    public String createBoard(@Validated @ModelAttribute("board") BoardSaveVo boardSaveVo, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            return "html/boardNew";
        }
        boardService.createBoard(boardSaveVo);
        redirectAttributes.addAttribute("id", boardSaveVo.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/board/view/{id}";
    }

    /** 게시물 리스트 **/
    @GetMapping("/list")
    public String getBoardPagingList(@ModelAttribute("boardDto") BoardDto boardDto, Model model) {
        List<BoardDto> boardList = boardService.getBoardList(boardDto);
        model.addAttribute("boardList", boardList);
        return "html/boardList";
    }

    /** 게시물 상세페이지 **/
    @GetMapping("/view/{id}")
    public String getDetailBoardView(@PathVariable("id") int id, Model model) {
        BoardDto detailViewBoard = boardService.getDetailViewBoard(id);
        model.addAttribute("detailViewBoard", detailViewBoard);
        return "html/boardDetail";
    }

    /** 게시물 수정하기 **/
    @PostMapping("/update")
    public String updateBoard(@Validated @ModelAttribute("boardUpdateVo") BoardUpdateVo boardUpdateVo,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            return "html/boardEdit";
        }

        boardService.updateBoard(boardUpdateVo);
        redirectAttributes.addAttribute("id", boardUpdateVo.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/board/view/{id}";
    }

    /** 게시물 수정페이지 **/
    @GetMapping("/update/{id}")
    public String getDetailViewUpdateBoard(@PathVariable("id") int id, Model model) {
        BoardUpdateVo boardUpdateVo = boardService.getDetailViewUpdateBoard(id);
        model.addAttribute("boardUpdateVo", boardUpdateVo);
        return "html/boardEdit";
    }

    /** 게시물 삭제하기 **/
    @PostMapping("/delete")
    public String deleteBoard(int id) {
        boardService.deleteBoard(id);
        return "redirect:/board/list";
    }
}


