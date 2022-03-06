package com.minboard.service.impl;


import com.minboard.dto.BoardDto;
import com.minboard.mapper.BoardMapper;
import com.minboard.paging.PaginationDto;
import com.minboard.paging.PaginationInfo;
import com.minboard.service.BoardService;
import com.minboard.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    /** 게시물 생성 **/
    @Override
    public int createBoard(BoardVo boardVo) {
        int successCreate = boardMapper.createBoard(boardVo);
        return successCreate;
    }

    /** 게시물 상세보기 **/
    @Override
    @Transactional(readOnly = true)
    public BoardDto getDetailViewBoard(int id) {
        BoardDto detailViewBoard = boardMapper.getDetailViewBoard(id);
        return detailViewBoard;
    }

    /** 게시물 삭제 **/
    @Override
    public void deleteBoard(int id) {
        boardMapper.deleteBoard(id);
    }

    /** 게시물 수정 **/
    @Override
    public int updateBoard(BoardDto boardDto) {
        int successUpdate = boardMapper.updateBoard(boardDto);
        return successUpdate;
    }

    /** 게시물 리스트 **/
    @Override
    @Transactional(readOnly = true)
    public List<BoardDto> getBoardList(BoardDto boardDto) {
        List<BoardDto> boardList = Collections.emptyList();
        int boardTotalCount = boardMapper.geTotalBoardCount();
        PaginationInfo paginationInfo = new PaginationInfo(boardDto);
        paginationInfo.setTotalPageCount(boardTotalCount);
        boardDto.setPaginationInfo(paginationInfo);
        if(boardTotalCount > 0){
            boardList = boardMapper.getBoardPagingList(boardDto);
        }
        return boardList;
    }

    /** 게시물 전체 갯수 **/
    @Override
    @Transactional(readOnly = true)
    public int geTotalBoardCount() {
        int successCount = boardMapper.geTotalBoardCount();
        return successCount;
    }
}
