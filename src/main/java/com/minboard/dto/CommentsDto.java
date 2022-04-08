package com.minboard.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentsDto {

    private int id;

    private int boardId;

    private String contents;

    private int commentGroup;

    private int commentDepth;

    private String delYn;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
