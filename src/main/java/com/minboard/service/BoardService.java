package com.minboard.service;

import com.minboard.dto.BoardDto;
import com.minboard.vo.BoardVo;

import java.util.List;


public interface BoardService {

    int createBoard(BoardVo boardVo);

    List<BoardDto> findAllByBoard();

}
