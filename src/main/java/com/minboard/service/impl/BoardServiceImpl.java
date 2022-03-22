package com.minboard.service.impl;


import com.minboard.dto.BoardDto;
import com.minboard.mapper.BoardMapper;
import com.minboard.mapper.UploadFileMapper;
import com.minboard.paging.PaginationDto;
import com.minboard.paging.PaginationInfo;
import com.minboard.service.BoardService;
import com.minboard.service.FileStoreService;
import com.minboard.vo.BoardSaveVo;
import com.minboard.vo.BoardUpdateVo;
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
    public void createBoard(BoardSaveVo boardSaveVo) {
        boardMapper.createBoard(boardSaveVo);
    }

    /** 게시물 상세보기 **/
    @Override
    @Transactional(readOnly = true)
    public BoardDto getDetailViewBoard(int id) {
        BoardDto detailViewBoard = boardMapper.getDetailViewBoard(id);
        return detailViewBoard;
    }

    /** 게시물 상세보기 **/
    @Override
    @Transactional(readOnly = true)
    public BoardUpdateVo getDetailViewUpdateBoard(int id) {
        BoardUpdateVo detailViewUpdateBoard = boardMapper.getDetailViewUpdateBoard(id);
        return detailViewUpdateBoard;
    }

    /** 게시물 삭제 **/
    @Override
    public void deleteBoard(int id) {
        boardMapper.deleteBoard(id);
    }

    /** 게시물 수정 **/
    @Override
    public void updateBoard(BoardUpdateVo boardUpdateVo) {
       boardMapper.updateBoard(boardUpdateVo);

    }

    /** 게시물 리스트 **/
    @Override
    public List<BoardDto> getBoardList(BoardDto boardDto) {
        List<BoardDto> boardList = Collections.emptyList();
        int boardTotalCount = boardMapper.geTotalBoardCount();
        PaginationInfo paginationInfo = new PaginationInfo(boardDto);
        paginationInfo.setTotalRecordCount(boardTotalCount);
        boardDto.setPaginationInfo(paginationInfo);
        if(boardTotalCount > 0){
            boardList = boardMapper.getBoardPagingList(boardDto);
        }
        return boardList;
    }

    /** 게시물 전체 갯수 **/
    @Override
    public int geTotalBoardCount() {
        int successCount = boardMapper.geTotalBoardCount();
        return successCount;
    }
}
