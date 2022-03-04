package com.minboard.service.impl;


import com.minboard.dto.BoardDto;
import com.minboard.repository.BoardRepository;
import com.minboard.service.BoardService;
import com.minboard.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;


    @Override
    public int createBoard(BoardVo boardVo) {

        int success = boardRepository.createBoard(boardVo);

        return success;
    }

    @Override
    public List<BoardDto> findAllByBoard() {

        List<BoardDto> boardList = boardRepository.findAllByBoard();

        return boardList;
    }
}
