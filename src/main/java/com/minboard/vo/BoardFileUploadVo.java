package com.minboard.vo;

import lombok.Builder;
import lombok.Getter;



@Getter
public class BoardFileUploadVo {

    private int id;

    private String fileName;

    private String fileNameOri;

    private String fileUrl;

    private String fileUrlOri;

    private String fileExtensionName;

    private int fileSize;

    private String delYn;

    @Builder
    public BoardFileUploadVo(int id, String fileName, String fileNameOri, String fileUrl, String fileUrlOri, String fileExtensionName, int fileSize, String delYn) {
        this.id = id;
        this.fileName = fileName;
        this.fileNameOri = fileNameOri;
        this.fileUrl = fileUrl;
        this.fileUrlOri = fileUrlOri;
        this.fileExtensionName = fileExtensionName;
        this.fileSize = fileSize;
        this.delYn = delYn;
    }

    public void updateBoardFile(String fileNameOri, String fileName, String fileUrl){
        this.fileNameOri = fileNameOri;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
    }
}
