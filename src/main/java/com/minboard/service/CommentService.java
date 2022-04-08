package com.minboard.service;

import com.minboard.dto.CommentsDto;
import com.minboard.vo.CommentsReplySaveVo;
import com.minboard.vo.CommentsSaveVo;
import com.minboard.vo.CommentsUpdateVo;

import java.util.List;

public interface CommentService {

    void insertComments(CommentsSaveVo commentsSaveVo);

    List<CommentsDto> getBoardCommentsList(int boardId);

    /** 게시물의 계층형 댓글전체 보기 **/
    List<CommentsDto> getBoardHierarchicalCommentsList(int boardId);

    /** 댓글 수정 페이지 **/
    CommentsDto getUpdateComments(int id);

    /** 댓글 수정하기 **/
    void updateComments(CommentsUpdateVo commentsUpdateVo);

    /** 댓글 삭제하기 **/
    void deleteComment(int id);

    /** 게시글의 모든댓글 삭제하기 **/
    void deleteAllComment(int boardId);

    /** 댓글의 답변 작성하기 **/
    void insertCommentsReply(CommentsReplySaveVo commentsReplySaveVo);
}
