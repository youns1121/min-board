package com.minboard.repository;

import com.minboard.dto.BoardDto;
import com.minboard.vo.BoardVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardRepository {

    List<BoardDto> findAllByBoard(); //전체 출력

    int createBoard(BoardVo boardVo); //생성




}
