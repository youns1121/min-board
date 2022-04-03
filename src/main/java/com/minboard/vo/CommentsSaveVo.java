package com.minboard.vo;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentsSaveVo {

    private int id;

    private int boardId;

    private String contents;

    private int groupNumber;

    private String commentsPath;

    private int commentsSort;

    private LocalDateTime createTime;

    @Builder
    public CommentsSaveVo(int id, int boardId, String contents, int groupNumber, String commentsPath, int commentsSort, LocalDateTime createTime) {
        this.id = id;
        this.boardId = boardId;
        this.contents = contents;
        this.groupNumber = groupNumber;
        this.commentsPath = commentsPath;
        this.commentsSort = commentsSort;
        this.createTime = createTime;
    }
}
