package com.minboard.vo;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class UploadFileVo {

    private int id;

    private int boardId;

    private String storeFileName;

    private String originalFileName;

    private String fileUrl;

    private String fileUrlOri;

    private String fileExtensionName;

    private LocalDateTime createFileTime;

    private int fileSize;

    private String delYn;

    @Builder
    public UploadFileVo(int id, int boardId, String storeFileName, String originalFileName, String fileUrl, String fileUrlOri, String fileExtensionName, int fileSize, LocalDateTime createFileTime, String delYn) {
        this.id = id;
        this.boardId = boardId;
        this.storeFileName = storeFileName;
        this.originalFileName = originalFileName;
        this.fileUrl = fileUrl;
        this.fileUrlOri = fileUrlOri;
        this.fileExtensionName = fileExtensionName;
        this.fileSize = fileSize;
        this.createFileTime = createFileTime.now();
        this.delYn = delYn;
    }
}
