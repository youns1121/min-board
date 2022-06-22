package com.minboard.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
public class BoardFileVo {

    private int id;

    private String storeFileName;

    private int boardId;

    private String originalFileName;

    private String extensionName;

    private Long storeFileSize;

    private String storeFilePath;

    private LocalDateTime createTime;

    @Builder
    public BoardFileVo(int id, String storeFileName, int boardId, String originalFileName, String extensionName,
                       Long storeFileSize, String storeFilePath) {
        this.id = id;
        this.storeFileName = storeFileName;
        this.boardId = boardId;
        this.originalFileName = originalFileName;
        this.extensionName = extensionName;
        this.storeFileSize = storeFileSize;
        this.storeFilePath = storeFilePath;
        this.createTime = LocalDateTime.now();
    }
}
