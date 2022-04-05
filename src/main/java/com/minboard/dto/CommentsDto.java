package com.minboard.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentsDto {

    private int id;

    private int boardId;

    private String contents;

    private int groupNumber;

    private String commentsPath;

    private int commentsSort;

    private String delYn;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
