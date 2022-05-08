package com.minboard.dto;


import com.minboard.paging.CommonPageDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;


@Getter
public class BoardDto extends CommonPageDto {

    /** 게시글 아이디 **/
    private Integer id;

    /** 게시글 제목 **/
    @NotBlank(message = "제목은 필수값 입니다.")
    private String title;

    /** 게시글 내용 **/
    @NotBlank(message = "내용은 필수값 입니다.")
    private String contents;

    /** 게시글 댓글 갯수 **/
    private int commentsCount;

    private int fileCount;

    /** 파일업로드 **/
    List<UploadFileDto> uploadFileDtoList;

    private Integer boardSort;

    private Integer boardDepth;

    private Integer boardGroup;

    private int boardId;

    private int boardAdminId;

    private String categoryName;

    private int categoryNumber;

    private Integer attachedFileCount;

    private String commentsYn;

    private String replyYn;

    private String attachedFileYn;

    private String delYn;

    public void setCategoryNumber(int categoryNumber) {
        this.categoryNumber = categoryNumber;
    }
}
