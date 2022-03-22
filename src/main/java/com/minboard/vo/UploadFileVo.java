package com.minboard.vo;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;


@Getter
public class UploadFileVo {

    private int id;

    private int boardId;

    /** 서버에 저장 될 파일명**/
    private String storeFileName;

    /** DB에 저장될 파일명 **/
    private String originalFileName;

    @Builder
    public UploadFileVo(int id, int boardId, String storeFileName, String originalFileName) {
        this.id = id;
        this.boardId = boardId;
        this.storeFileName = storeFileName;
        this.originalFileName = originalFileName;
    }

}
