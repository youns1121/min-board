package com.minboard.mapper;

import com.minboard.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardAdminMapper {

    List<BoardDto> findByBoardIdList(int boardAdminId);

    void deleteBoardAdmin(int id);


}
