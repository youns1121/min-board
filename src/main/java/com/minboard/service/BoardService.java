package com.minboard.service;

import com.minboard.dto.BoardDto;
import com.minboard.vo.BoardSaveVo;
import com.minboard.vo.BoardUpdateVo;

import java.io.IOException;
import java.util.List;


public interface BoardService {

    /** 게시물 리스트 **/
    List<BoardDto> getBoardList(BoardDto boardDto);

    /** 전체 게시물 수 **/
    int geTotalBoardCount();

    /** 게시물 상세보기 **/
    BoardDto getDetailViewBoard(int id);

    /** 게시물 상세보기의 모든정보  **/
    BoardDto getDetailViewBoardAllInfo(int boardId);

    /** 게시물 수정 상세보기 **/
    BoardUpdateVo getDetailViewUpdateBoard(int id);

    /** 게시물 생성 **/
    void createBoard(BoardSaveVo boardSaveVo);

    /** 게시물의 파일 저장 **/
    void saveBoardFile(BoardSaveVo boardSaveVo) throws IOException;

    /** 게시물의 파일 수정 **/
    void updateBoardFile(BoardUpdateVo boardUpdateVo) throws IOException;

    /** 게시물 삭제 **/
    void deleteBoard(int id);

    /** 게시물 수정 **/
    void updateBoard(BoardUpdateVo boardUpdateVo);


}
