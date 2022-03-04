package com.minboard.vo;

import com.minboard.paging.CommonPageDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardVo extends CommonPageDto {

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


    public BoardVo(String title, String contents, LocalDateTime createTime, LocalDateTime updateTime) {
        this.title = title;
        this.contents = contents;
        this.createTime = createTime.now();
        this.updateTime = updateTime.now();
    }

    public BoardVo(String title, LocalDateTime createTime, Integer viewCount, Integer likeCount) {
        this.title = title;
        this.createTime = createTime.now();
        this.viewCount = viewCount;
        this.likeCount = likeCount;
    }

}
