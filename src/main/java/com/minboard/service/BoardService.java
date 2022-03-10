package com.minboard.service;

import com.minboard.dto.BoardDto;
import com.minboard.paging.PaginationDto;
import com.minboard.vo.BoardVo;

import java.util.List;


public interface BoardService {

    /** 게시물 리스트 **/
    List<BoardDto> getBoardList(BoardDto boardDto);

    /** 전체 게시물 수 **/
    int geTotalBoardCount();

    /** 게시물 상세보기 **/
    BoardDto getDetailViewBoard(int id);

    /** 게시물 생성 **/
    void createBoard(BoardVo boardVo);

    /** 게시물 삭제 **/
    void deleteBoard(int id);

    /** 게시물 수정 **/
    void updateBoard(BoardVo boardVo);


}
