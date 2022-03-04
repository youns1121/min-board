package com.minboard.controller;


import com.minboard.dto.BoardDto;
import com.minboard.paging.PaginationDto;
import com.minboard.service.BoardService;
import com.minboard.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;



    @GetMapping("/new")
    public String formBoard(@ModelAttribute("boardDto") BoardDto boardDto){
        return "html/board";
    }

    @PostMapping("/new")
    public String createBoard(BoardVo boardVo, Model model){
        int successBoard = boardService.createBoard(boardVo);
        model.addAttribute("successBoard", successBoard);
        return "html/board";
    }

    @GetMapping("/list")
    public String getBoardPagingList(@ModelAttribute("boardVo") BoardVo boardVo, Model model){
        List<BoardDto> boardList = boardService.getBoardList(boardVo);
        model.addAttribute("boardList", boardList);
        return "html/boardList";
    }

    //상세보기
    @GetMapping()
    public String getDetailBoardView(){
        return "html/board";
    }

    //수정
    @PatchMapping()
    public String updateBoard(){
        return "html/board";
    }
    //삭제, delYn = "Y"
    @PatchMapping()
    public String deleteBoard(){
        return "html/board";
    }




}
