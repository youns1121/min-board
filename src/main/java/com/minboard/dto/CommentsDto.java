package com.minboard.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentsDto {

    private int id;

    private int boardId;

    private String contents;

    private int commentsGroup;

    private int commentsDepth;

    private int sort;

    private String delYn;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
