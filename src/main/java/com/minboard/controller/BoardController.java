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

    /** 게시물 페이지 **/
    @GetMapping("/new")
    public String formBoard(@ModelAttribute("boardVo") BoardVo boardVo, Model model){
        model.addAttribute("boardVo", BoardVo.builder().build());
        return "html/boardNew";
    }

    /** 게시물 생성하기 **/
    @PostMapping("/new")
    public String createBoard(@ModelAttribute("boardVo") BoardVo boardVo, Model model){
        int successBoard = boardService.createBoard(boardVo);
        model.addAttribute("successBoard", successBoard);
        return "html/boardNew";
    }

    /** 게시물 리스트 **/
    @GetMapping("/list")
    public String getBoardPagingList(BoardDto boardDto, Model model){
        List<BoardDto> boardList = boardService.getBoardList(boardDto);
        model.addAttribute("boardList", boardList);
        return "html/boardList";
    }

    /** 게시물 상세페이지 **/
    @GetMapping("/view/{id}")
    public String getDetailBoardView(@PathVariable("id") int id, Model model){
        BoardDto detailViewBoard = boardService.getDetailViewBoard(id);
        model.addAttribute("detailViewBoard", detailViewBoard);
        return "html/boardDetail";
    }

    /** 게시물 수정하기 **/
    @PostMapping("/update")
    public String updateBoard(@ModelAttribute("boardDto") BoardDto boardDto, Model model){
        int updateBoard = boardService.updateBoard(boardDto);
        model.addAttribute("updateBoard", updateBoard);
        return "html/boardEdit";
    }

    /** 게시물 수정페이지 **/
    @GetMapping("/update/{id}")
    public String getUpdateBoardView(@PathVariable("id") int id, Model model){
        BoardDto boardDto = boardService.getDetailViewBoard(id);
        model.addAttribute("boardDto", boardDto);
        return "html/boardEdit";
    }

    /** 게시물 삭제하기 **/
    @PostMapping("/delete")
    public String deleteBoard(int id){
        boardService.deleteBoard(id);
        return "html/boardDetail";
    }


    }


