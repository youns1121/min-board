package com.minboard.vo;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class BoardUpdateVo {

    /** Board Table PK **/
    private Integer id;

    /** 게시글 제목 **/
    @NotBlank(message = "제목은 필수값 입니다.")
    private String title;

    /** 게시글 내용 **/
    @NotBlank(message = "내용은 필수값 입니다.")
    @Length(min = 20, max = 100,message = "내용은 20자 이상 100자 이하")
    private String contents;

    /** 등록일 **/
    private LocalDateTime createTime;

    /** 수정일 **/
    private LocalDateTime updateTime;
//    /**
//     * 파일업로드 **/
//    private List<MultipartFile> fileList;
//
//    private UploadFileVo uploadFileVo;

    /** 게시글 조회수 **/
    private Integer viewCount;

    /** 게시글 좋아요수 **/
    private Integer likeCount;

    @Builder

    public BoardUpdateVo(Integer id, String title, String contents, LocalDateTime createTime, LocalDateTime updateTime, Integer viewCount, Integer likeCount) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
    }
}

