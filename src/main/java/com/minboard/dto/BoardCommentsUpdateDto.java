package com.minboard.dto;


import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardCommentsUpdateDto {

    private int id;

    private Integer boardId;

    private String contents;

    private Integer commentGroup;

    private Integer commentsSort;

    private Integer commentsDepth;

    private Integer commentsGroup;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


    @Builder
    public BoardCommentsUpdateDto(int id, Integer boardId, String contents, Integer commentGroup,
                                  Integer commentsSort, Integer commentsDepth, Integer commentsGroup) {
        this.id = id;
        this.boardId = boardId;
        this.contents = contents;
        this.commentGroup = commentGroup;
        this.commentsSort = commentsSort;
        this.commentsDepth = commentsDepth;
        this.commentsGroup = commentsGroup;
        this.updateTime = LocalDateTime.now();
    }
}
