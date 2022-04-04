package com.minboard.service;

import com.minboard.dto.CommentsDto;
import com.minboard.vo.CommentsSaveVo;

import java.util.List;

public interface CommentService {

    void insertComments(CommentsSaveVo commentsSaveVo);

    List<CommentsDto> getBoardCommentsList(int boardId);

}
