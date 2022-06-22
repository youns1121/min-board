package com.minboard.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardCommentsReplySaveDto {

    private int id;

    private int boardId;

    private String contents;

    private Integer commentsGroup;

    private Integer commentsDepth;

    private Integer commentsSort;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


    public void setSort(int calculationResult){
        this.commentsSort = calculationResult;
    }

    @Builder
    public BoardCommentsReplySaveDto(Integer boardId, String contents, Integer commentsGroup,
                                     Integer commentsDepth, Integer commentsSort) {

        this.boardId = boardId;
        this.contents = contents;
        this.commentsGroup = commentsGroup;
        this.commentsSort = commentsSort;
        this.commentsDepth = commentsDepth;
        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
    }
}
