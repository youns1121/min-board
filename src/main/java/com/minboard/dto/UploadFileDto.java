package com.minboard.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class UploadFileDto {

    /** keyProperty **/
    private Integer id;

    /** 서버에 저장 될 파일명**/
    private String storeFileName;

    /** 작성글 FK **/
    private int boardId;

    /** 실제 파일명 **/
    private String originalFileName;

    /** 확장자명 **/
    private String extensionName;

    /** 파일사이즈 **/
    private long storeFileSize;

    /** 파일 저장 경로 **/
    private String storeFilePath;

    /** 파일 삭제 여부 **/
    private String delYn;

    /** 파일 저장 시간 **/
    private LocalDateTime createTime;
}
