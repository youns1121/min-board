package com.minboard.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardDto {

    private Long id;

    private String title;

    private String contents;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String delYn;

    private Integer viewCount;

    private Integer likeCount;



    public BoardDto() {
    }

}
