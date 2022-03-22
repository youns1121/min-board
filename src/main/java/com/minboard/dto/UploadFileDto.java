package com.minboard.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadFileDto {

    private int id;

    private int boardId;

    /** 서버에 저장 될 파일명**/
    private String storeFileName;

    /** DB에 저장될 파일명 **/
    private String originalFileName;
}
