package com.minboard.mapper;

import com.minboard.dto.CommentsDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentsMapper {

    /** 댓글 작성하기 **/
    void insertComments(int boardId);

    /** 게시물 댓글보기 **/
    CommentsDto getBoardCommentsList(int boardId);

    /** 게시물 댓글 총 갯수 **/
    Integer getBoardCommentsTotalCount(int boardId);
}
