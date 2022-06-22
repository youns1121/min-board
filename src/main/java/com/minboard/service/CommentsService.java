package com.minboard.service;

import com.minboard.dto.BoardCommentsUpdateDto;
import com.minboard.dto.BoardCommentsReplySaveDto;
import com.minboard.dto.CommentsSaveDto;
import com.minboard.vo.BoardCommentsVo;

import java.util.List;

public interface CommentsService {

    void saveComments(CommentsSaveDto commentsSaveDto);

    List<BoardCommentsVo> getBoardHierarchicalCommentsList(int boardId);

    void modifyComments(BoardCommentsUpdateDto commentsUpdateDto);

    void removeComments(BoardCommentsUpdateDto boardCommentsUpdateDto);

    void saveCommentsReply(BoardCommentsReplySaveDto commentsReplySaveDto);
}
