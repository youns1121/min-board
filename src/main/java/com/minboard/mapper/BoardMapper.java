package com.minboard.mapper;

import com.minboard.dto.BoardAdminDto;
import com.minboard.dto.BoardDto;

import com.minboard.vo.BoardSaveVo;
import com.minboard.vo.BoardUpdateVo;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface BoardMapper {

    /** 게시물 리스트 **/
    List<BoardDto> selectBoardList(BoardDto boardDto);

    /** 관리자 게시물 리스트 **/
    List<BoardDto> selectBoardAdminList(BoardDto boardDto);


    /** 게시물 총 갯수 **/
    int totalCountBoard();

    /** 게시물 상세보기 **/
    BoardDto selectBoard(int id);

    /** 관리자 게시물 상세보기 **/
    BoardAdminDto selectBoardAdmin(int id);

    /** 게시물 수정 상세보기 **/
    BoardUpdateVo selectUpdateBoard(int id);

    /** 게시물 생성 **/
    void insertBoard(BoardSaveVo boardSaveVo);

    void insertBoardAdmin(BoardSaveVo boardSaveVo);

    /** 게시물 삭제 **/
    void deleteBoard(int id);

    /** 게시물 수정 **/
    void updateBoard(BoardUpdateVo boardUpdateVo); // 게시물 수정

    int countComments(int id);

    int countAttachedFile(int id);

    void updateBoardGroupSet(int boardId);

    int hierarchicalCalculationFormula(BoardSaveVo boardSaveVo);

    int calculationFormulaResultZero(BoardSaveVo boardSaveVo);

    void insertBoareReply(BoardSaveVo boardSaveVo);

    void calculationFormulaResultNotZero(BoardSaveVo boardSaveVo);

    BoardDto selectBoardReply(int id);

    void decreaseSort(BoardDto boardDto);


}
