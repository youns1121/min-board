package com.minboard.mapper;

import com.minboard.dto.BoardFileDto;
import com.minboard.vo.BoardFileVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardFileMapper {

    void insertBoardFileList(List<BoardFileDto> uploadFileList);

    BoardFileVo selectBoardFile(int id);

    List<BoardFileVo> selectBoardFileList(int id);

    void deleteBoardFile(int id);

    void deleteBoardFileList(int id);
}
