package com.minboard.vo;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ComentsSaveVo {

    private int id;

    private int boardId;

    private String contents;

    private int groupNumber;

    private String depts;

    private LocalDateTime createTime;

    @Builder
    public ComentsSaveVo(int id, int boardId, String contents, int groupNumber, String depts, LocalDateTime createTime) {
        this.id = id;
        this.boardId = boardId;
        this.contents = contents;
        this.groupNumber = groupNumber;
        this.depts = depts;
        this.createTime = createTime;
    }
}
