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
    private List<UploadFileVo> uploadFileVoList;

    private String storeFileName;

    private MultipartFile attachFile;

    /** 등록일 */
    private LocalDateTime createTime;

    /** 수정일 */
    private LocalDateTime updateTime;

    @Builder

    public BoardSaveVo(Integer id, String title, String contents, List<UploadFileVo> uploadFileVoList, MultipartFile attachFile, String storeFileName, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.uploadFileVoList = uploadFileVoList;
        this.attachFile = attachFile;
        this.storeFileName = storeFileName;
        this.createTime = createTime.now();
        this.updateTime = updateTime.now();
    }
}
