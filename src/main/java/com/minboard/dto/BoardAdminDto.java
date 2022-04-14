package com.minboard.dto;

import com.minboard.paging.CommonPageDto;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
public class BoardAdminDto extends CommonPageDto {

    /** 게시글 아이디 **/
    private Integer id;

    /** 게시글 번호 **/
    private Integer num;

    /** 게시글 제목 **/
    private String title;

    /** 게시글 내용 **/
    private String contents;

    /** 게시글 댓글 갯수 **/
    private int commentsCount;

    private int attachdFileCount;

    private int boardId;

    private String commentsYn;

    private String replyYn;

    private String attachdFileYn;

    /** 게시글 삭제여부, Y : 삭제, N : 미삭제 **/
    private String delYn;
}
