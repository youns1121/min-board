package com.minboard.mapper;


import com.minboard.dto.BoardSaveDto;
import com.minboard.dto.request.BoardRequestDto;
import com.minboard.vo.BoardVo;
import com.minboard.dto.BoardUpdateDto;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface BoardMapper {

    List<BoardVo> selectBoardList(BoardRequestDto requestDto);

    BoardVo selectBoard(int id);

    void insertBoard(BoardSaveDto boardSaveDto);

    void deleteBoard(int id);

    void updateBoard(BoardUpdateDto boardUpdateVo);

    void updateBoardGroupSet(int boardId);

    int selectHierarchicalCalculationFormula(BoardSaveDto boardSaveDto);

    int selectCalculationFormulaResultZero(BoardSaveDto boardSaveDto);

    void insertBoardReply(BoardSaveDto boardSaveDto);

    void updateBoardSortIncrease(BoardSaveDto boardSaveDto);

    BoardVo selectBoardReply(int id);

    void updateBoardSortDecrease(BoardVo boardVo);

    List<BoardVo> findByBoardList(int boardAdminId);
}
