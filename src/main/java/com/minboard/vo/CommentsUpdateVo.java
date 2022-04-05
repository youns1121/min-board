package com.minboard.vo;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Getter
public class CommentsUpdateVo {
    private int id;

    private int boardId;

    private String contents;

    private LocalDateTime updateTime;

    private String delYn;

    @Builder
    public CommentsUpdateVo(int id, String contents, LocalDateTime updateTime) {
        this.id = id;
        this.contents = contents;
        this.updateTime = updateTime.now();
    }
}
