package com.minboard.mapper;

import com.minboard.dto.CommentsDto;
import com.minboard.vo.CommentsSaveVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentsMapper {

    /** 댓글 작성하기 **/
    void insertComments(CommentsSaveVo commentsSaveVo);

    /** 게시물 댓글보기 **/
    List<CommentsDto> getBoardCommentsList(int boardId);

    /** 게시물 댓글 총 갯수 **/
    Integer getBoardCommentsTotalCount(int boardId);
}
