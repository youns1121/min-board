package com.minboard.vo;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class CommentsSaveVo {

    private int id;

    private int boardId;

    private String contents;

    private LocalDateTime createTime;


    @Builder
    public CommentsSaveVo(int boardId, String contents, LocalDateTime createTime) {
        this.boardId = boardId;
        this.contents = contents;
        this.createTime = createTime.now();
    }
}