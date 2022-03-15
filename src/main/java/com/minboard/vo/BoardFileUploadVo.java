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

    private String delYn;

    @Builder
    public BoardFileUploadVo(int id, String fileName, String fileNameOri, String fileUrl, String fileUrlOri, String delYn) {
        this.id = id;
        this.fileName = fileName;
        this.fileNameOri = fileNameOri;
        this.fileUrl = fileUrl;
        this.fileUrlOri = fileUrlOri;
        this.delYn = delYn;
    }
}
