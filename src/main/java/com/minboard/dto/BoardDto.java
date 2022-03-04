package com.minboard.dto;


import com.minboard.paging.CommonPageDto;
import com.minboard.paging.PaginationInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardDto extends CommonPageDto {

    /** 게시글 번호 **/
    private Long num;

    /** 게시글 제목 **/
    private String title;

    /** 게시글 작성자 **/
    private String author;

    /** 게시글 내용 **/
    private String contents;

    /** 게시글 생성시간 **/
    private LocalDateTime createTime;

    /** 게시글 수정시간 **/
    private LocalDateTime updateTime;

    /** 게시글 삭제시간 **/
    private LocalDateTime deleteTime;

    /** 게시글 삭제여부, Y : 삭제, N : 삭제안됨 **/
    private String delYn;

    /** 게시글 조회수 **/
    private Integer viewCount;

    /** 게시글 좋아요수 **/
    private Integer likeCount;
}
