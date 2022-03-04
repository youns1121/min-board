package com.minboard.service;

import com.minboard.dto.BoardDto;
import com.minboard.paging.PaginationDto;
import com.minboard.vo.BoardVo;

import java.util.List;


public interface BoardService {

    List<BoardDto> findAllByBoard(); //전체 출력

    // 게시물 페이징
    List<BoardDto> getBoardList(BoardVo boardVo);

    int geTotalBoardCount(); // 총 게시물 수

    BoardDto getDetailViewBoard(int id); // 상세보기

    int createBoard(BoardVo boardVo); //게시물 생성

    int deleteBoard(int id); // 게시물 삭제

    int updateBoard(int id); // 게시물 수정

}
