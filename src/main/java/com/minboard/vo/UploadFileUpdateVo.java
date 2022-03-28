package com.minboard.vo;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UploadFileUpdateVo {

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

    /** 파일 저장 시간 **/
    private LocalDateTime createTime;


    @Builder
    public UploadFileUpdateVo(String storeFileName, int boardId, String originalFileName, String extensionName, long storeFileSize, String storeFilePath, LocalDateTime createTime) {
        this.storeFileName = storeFileName;
        this.boardId = boardId;
        this.originalFileName = originalFileName;
        this.extensionName = extensionName;
        this.storeFileSize = storeFileSize;
        this.storeFilePath = storeFilePath;
        this.createTime = createTime.now();
    }
}
