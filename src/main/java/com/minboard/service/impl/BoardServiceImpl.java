package com.minboard.service.impl;


import com.minboard.dto.BoardDto;
import com.minboard.mapper.BoardMapper;
import com.minboard.paging.PaginationDto;
import com.minboard.service.BoardService;
import com.minboard.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;


    @Override
    public int createBoard(BoardVo boardVo) {

        int successCreate = boardMapper.createBoard(boardVo);

        return successCreate;
    }



    @Override
    public BoardDto getDetailViewBoard(int id) {
        return null;
    }

    @Override
    public int deleteBoard(int boardId) {

        int successDelete = boardMapper.deleteBoard(boardId);
        return successDelete;
    }

    @Override
    public int updateBoard(int boardId) {

        int successUpdate = boardMapper.updateBoard(boardId);
        return successUpdate;
    }


    @Override
    public List<BoardDto> findAllByBoard() {

        List<BoardDto> boardList = boardMapper.findAllByBoard();

        return boardList;
    }

    @Override
    public List<BoardDto> getBoardList(BoardVo boardVo) {
        List<BoardDto> boardList = Collections.emptyList();
        int boardTotalCount = boardMapper.geTotalBoardCount();

        if(boardTotalCount > 0){
            boardList = boardMapper.getBoardPagingList(boardVo);
        }

        return boardList;
    }


    @Override
    public int geTotalBoardCount() {

        int successCount = boardMapper.geTotalBoardCount();
        return successCount;
    }
}
