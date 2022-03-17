package com.minboard.vo;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class UploadFileVo {

    private int id;

    private String storeFileName;

    private String originalFileName;

    private String fileUrl;

    private String fileUrlOri;

    private String fileExtensionName;

    private LocalDateTime createFileTime;

    private int fileSize;

    private String delYn;

    @Builder
    public UploadFileVo(int id, String storeFileName, String originalFileName, String fileUrl, String fileUrlOri, String fileExtensionName, int fileSize, LocalDateTime createFileTime, String delYn) {
        this.id = id;
        this.storeFileName = storeFileName;
        this.originalFileName = originalFileName;
        this.fileUrl = fileUrl;
        this.fileUrlOri = fileUrlOri;
        this.fileExtensionName = fileExtensionName;
        this.fileSize = fileSize;
        this.createFileTime = createFileTime.now();
        this.delYn = delYn;
    }

//    public void updateBoardFile(String fileNameOri, String fileName, String fileUrl){
//        this.fileNameOri = fileNameOri;
//        this.fileName = fileName;
//        this.fileUrl = fileUrl;
//    }
}
