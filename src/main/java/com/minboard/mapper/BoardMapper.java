package com.minboard.mapper;

import com.minboard.dto.BoardDto;

import com.minboard.paging.PaginationDto;
import com.minboard.vo.BoardVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    /** 게시물 리스트 **/
    List<BoardDto> getBoardPagingList(BoardDto boardDto);

    /** 게시물 총 갯수 **/
    int geTotalBoardCount();

    /** 게시물 상세보기 **/
    BoardDto getDetailViewBoard(int id);

    /** 게시물 생성 **/
    void createBoard(BoardVo boardVo);

    /** 게시물 삭제 **/
    void deleteBoard(int id);

    /** 게시물 수정 **/
    void updateBoard(BoardVo boardVo); // 게시물 수정
}
