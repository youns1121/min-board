package com.minboard.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardVo {

    private Long id;

    private String title;

    private String contents;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String delYn;

    private Integer viewCount;

    private Integer likeCount;


    public BoardVo(String title, String contents, LocalDateTime createTime, LocalDateTime updateTime) {
        this.title = title;
        this.contents = contents;
        this.createTime = createTime.now();
        this.updateTime = updateTime.now();
    }

    public BoardVo(String title, LocalDateTime create_time,Integer viewCount, Integer likeCount) {
        this.title = title;
        this.createTime = create_time;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
    }

}
