package com.minboard.vo;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentsReplySaveVo {

    private int id;

    private int boardId;

    private String contents;

    private int commentGroup;

    private int commentDepth;

    private LocalDateTime createTime;


    @Builder
    public CommentsReplySaveVo(int boardId, String contents, int commentGroup,
                               int commentDepth, LocalDateTime createTime) {

        this.boardId = boardId;
        this.contents = contents;
        this.commentGroup = commentGroup;
        this.commentDepth = ++commentDepth;
        this.createTime = createTime.now();
    }
}
