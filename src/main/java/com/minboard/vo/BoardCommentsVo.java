package com.minboard.vo;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardCommentsVo {

    private int id;

    private int boardId;

    private String contents;

    private Integer commentsGroup;

    private Integer commentsSort;

    private Integer commentsDepth;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String delYn;

    @Builder
    public BoardCommentsVo(int id, int boardId, String contents,
                           Integer commentsGroup, Integer commentsSort,
                           Integer commentsDepth, LocalDateTime createTime, LocalDateTime updateTime,
                           String delYn) {
        this.id = id;
        this.boardId = boardId;
        this.contents = contents;
        this.commentsGroup = commentsGroup;
        this.commentsSort = commentsSort;
        this.commentsDepth = commentsDepth;
        this.delYn = delYn;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
