package com.minboard.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardFileDto {

    private Integer id;

    private String storeFileName;

    private int boardId;

    private String originalFileName;

    private String extensionName;

    private long storeFileSize;

    private String storeFilePath;

    private LocalDateTime createTime;

    @Builder
    public BoardFileDto(Integer id, String storeFileName, int boardId,
                        String originalFileName, String extensionName, long storeFileSize,
                        String storeFilePath) {
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
