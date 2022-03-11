package com.minboard.dto;


import com.minboard.paging.CommonPageDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
public class BoardDto extends CommonPageDto {

    /** 게시글 아이디 **/
    private Integer id;
    
    /** 게시글 번호 **/
    private Integer num;

    /** 게시글 제목 **/
    @NotBlank(message = "제목은 필수값 입니다.")
    private String title;

    /** 게시글 작성자 **/
    private String author;

    /** 게시글 내용 **/
    @NotBlank(message = "내용은 필수값 입니다.")
    private String contents;

    /** 게시글 삭제여부, Y : 삭제, N : 미삭제 **/
    private String delYn;

    /** 게시글 조회수 **/
    private Integer viewCount;

    /** 게시글 좋아요수 **/
    private Integer likeCount;

}
