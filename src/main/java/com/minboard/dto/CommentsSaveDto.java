package com.minboard.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
public class CommentsSaveDto {

    private Integer id;

    private Integer boardId;

    private String contents;

    private Integer commentsGroup;

    private Integer commentsSort;

    private Integer commentsDepth;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


    public void setCommentsInit(Integer commentsSort, Integer commentsDepth) {
        this.commentsSort = commentsSort;
        this.commentsDepth = commentsDepth;
    }

    @Builder
    public CommentsSaveDto(Integer id, Integer boardId, String contents,
                           Integer commentsGroup, Integer commentsSort, Integer commentsDepth) {
        this.id = id;
        this.boardId = boardId;
        this.contents = contents;
        this.commentsGroup = commentsGroup;
        this.commentsSort = commentsSort;
        this.commentsDepth = commentsDepth;
        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
    }
}
