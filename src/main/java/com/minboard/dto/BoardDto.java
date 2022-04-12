package com.minboard.dto;


import com.minboard.paging.CommonPageDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;


@Getter
public class BoardDto extends CommonPageDto {

    private UploadFileDto uploadFileDto;

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

    /** 게시글 댓글 갯수 **/
    private Integer commentsCount;

    /** 게시글 첨부파일 갯수 **/
    private Integer attachedFileCount;

    /** 파일업로드 **/
    List<UploadFileDto> uploadFileDtoList;

    /** 댓글 **/
    List<CommentsDto> commentsDtoList;

    /** 게시글 삭제여부, Y : 삭제, N : 미삭제 **/
    private String delYn;


    public BoardDto(Integer commentsCount, Integer attachedFileCount){
        this.commentsCount = commentsCount;
        this.attachedFileCount = attachedFileCount;
    }

    public static BoardDto countOf(Integer commentsCount, Integer attachedFileCount){
        return new BoardDto(commentsCount, attachedFileCount);
    }
}
