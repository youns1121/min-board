package com.minboard.mapper;

import com.minboard.dto.BoardAdminSaveDto;
import com.minboard.dto.BoardAdminUpdateDto;
import com.minboard.dto.request.BoardAdminRequestDto;
import com.minboard.vo.BoardAdminVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardAdminMapper {

    void insertBoardAdminSetting(BoardAdminSaveDto boardAdminSaveDto);

    void updateBoardAdminSetting(BoardAdminUpdateDto boardAdminUpdateDto);

    List<BoardAdminVo> selectBoardCategoryList();

    BoardAdminVo selectBoardCategory(Integer id);

    BoardAdminVo selectBoardAdmin(int boardAdminId);

    void updateIsDeleteBoardAdmin(int boardAdminId);

    int totalCountCategoryBoard(int categoryNumber);

    List<BoardAdminVo> selectBoardAdminList(BoardAdminRequestDto boardAdminRequestDto);

}
