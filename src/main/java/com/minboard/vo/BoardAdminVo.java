package com.minboard.vo;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardAdminVo {

    private Integer id;

    private String categoryName;

    private Integer categoryNumber;

    private String title;

    private String contents;

    private String boardFileYn;

    private String commentsYn;

    private String replyYn;

    private Integer boardFileCount;

    private int commentsCount;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String delYn;

    private int boardId;

    @Builder

    public BoardAdminVo(Integer id, String categoryName, Integer categoryNumber,
                        String title, String contents, String boardFileYn,
                        String commentsYn, String replyYn, Integer boardFileCount,
                        int commentsCount, LocalDateTime createTime, LocalDateTime updateTime,
                        String delYn, int boardId) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryNumber = categoryNumber;
        this.title = title;
        this.contents = contents;
        this.boardFileYn = boardFileYn;
        this.commentsYn = commentsYn;
        this.replyYn = replyYn;
        this.boardFileCount = boardFileCount;
        this.commentsCount = commentsCount;
        this.createTime = createTime.now();
        this.updateTime = updateTime.now();
        this.delYn = delYn;
        this.boardId = boardId;
    }
}
