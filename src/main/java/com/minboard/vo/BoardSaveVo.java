package com.minboard.vo;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class BoardSaveVo {

    /** Board Table PK **/
    private Integer id;

    /** 게시글 제목 **/
    @NotBlank(message = "제목은 필수값 입니다.")
    private String title;

    /** 게시글 내용 **/
    @NotBlank(message = "내용은 필수값 입니다.")
    @Length(min = 20, max = 100,message = "내용은 20자 이상 100자 이하")
    private String contents;

    /** 파일업로드 **/
    private List<MultipartFile> fileList;

    private UploadFileVo uploadFileVo;

    /** 등록일 */
    private LocalDateTime createTime;

    /** 수정일 */
    private LocalDateTime updateTime;

    @Builder
    public BoardSaveVo(String title, String contents, List<MultipartFile> fileList, UploadFileVo uploadFileVo, LocalDateTime createTime, LocalDateTime updateTime) {
        this.title = title;
        this.contents = contents;
        this.fileList = fileList;
        this.uploadFileVo = uploadFileVo;
        this.createTime = createTime.now();
        this.updateTime = updateTime.now();
    }
}
