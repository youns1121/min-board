package com.minboard.vo;


import lombok.Builder;
import lombok.Getter;


import java.time.LocalDateTime;

@Getter
public class CommentsSaveVo {

    private int id;

    private int boardId;

    private String contents;

    private Integer commentGroup;

    private Integer sort;

    private Integer commentDepth;

    private LocalDateTime createTime;


    @Builder
    public CommentsSaveVo(int boardId, String contents, Integer commentGroup,
                          Integer sort, Integer commentDepth, LocalDateTime createTime) {
        this.boardId = boardId;
        this.contents = contents;
        this.commentGroup = commentGroup;
        this.sort = sort;
        this.commentDepth = commentDepth;
        this.createTime = createTime.now();
    }
}
