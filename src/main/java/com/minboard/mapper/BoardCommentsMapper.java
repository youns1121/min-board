package com.minboard.mapper;

import com.minboard.dto.BoardCommentsUpdateDto;
import com.minboard.dto.BoardCommentsReplySaveDto;
import com.minboard.dto.CommentsSaveDto;
import com.minboard.vo.BoardCommentsVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardCommentsMapper {

    void insertComments(CommentsSaveDto commentsSaveDto);

    List<BoardCommentsVo> getBoardHierarchicalCommentsList(int boardId);

    void updateComments(BoardCommentsUpdateDto BoardCommentsUpdateDto);

    void updateIsDeleteComments(BoardCommentsUpdateDto boardCommentsUpdateDto);

    void updateIsDeleteAllComments(int boardId);

    void updateCommentsSetGroup(CommentsSaveDto commentsSaveDto);

    int getHierarchicalCalculationFormula(BoardCommentsReplySaveDto CommentsReplySaveDto);

    int getCalculationFormulaResultZero(BoardCommentsReplySaveDto CommentsReplySaveDto);

    void updateCommentsSortIncrease(BoardCommentsReplySaveDto commentsReplySaveDto);

    void insertCommentsReply(BoardCommentsReplySaveDto boardCommentsReplySaveDto);

    void updateCommentsSortDecrease(BoardCommentsUpdateDto boardCommentsUpdateDto);




}
