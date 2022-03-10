package com.minboard.controller;


import com.minboard.dto.BoardDto;
import com.minboard.service.BoardService;
import com.minboard.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    /** 게시물 생성페이지 **/
    @GetMapping("/new")
    public String formBoard(@ModelAttribute("boardVo") BoardVo boardVo, Model model) {
        model.addAttribute("boardVo", BoardVo.builder().build());
        return "html/boardNew";
    }

    /** 게시물 생성하기 **/
    @PostMapping("/new")
    public String createBoard(BoardVo boardVo, RedirectAttributes redirectAttributes, Model model) {
        //검증 오류 결과를 보관
        Map<String, String> hasErrors = new HashMap<>();
        //검증 로직
        if(!StringUtils.hasText(boardVo.getTitle())){
            hasErrors.put("title", "제목은 필수입니다.");
        }
        if((boardVo.getContents() != null && boardVo.getContents().trim().length() < 20) || "".equals(boardVo.getContents())){
            log.info("hasErrors = {}", hasErrors);
            hasErrors.put("contents", "내용은 공백제외 20자 이상 작성해야 합니다.");
        }
        //검증에 실패하면 다시 입력 폼으로
        if(!hasErrors.isEmpty()){
            model.addAttribute("hasErrors", hasErrors);
            return "html/boardNew";
        }
        //성공로직
        boardService.createBoard(boardVo);
        redirectAttributes.addAttribute("id", boardVo.getId());
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
    public String updateBoard(@ModelAttribute("boardDto") BoardDto boardDto, BoardVo boardVo, RedirectAttributes redirectAttributes) {
        boardService.updateBoard(boardVo);
        redirectAttributes.addAttribute("id", boardDto.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/board/view/{id}";
    }

    /** 게시물 수정페이지 **/
    @GetMapping("/update/{id}")
    public String getUpdateBoardView(@PathVariable("id") int id, Model model) {
        BoardDto boardDto = boardService.getDetailViewBoard(id);
        model.addAttribute("boardDto", boardDto);
        return "html/boardEdit";
    }

    /** 게시물 삭제하기 **/
    @PostMapping("/delete")
    public String deleteBoard(int id) {
        boardService.deleteBoard(id);
        return "redirect:/board/list";
    }
}


