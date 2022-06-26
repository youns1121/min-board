package com.minboard.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardAdminUpdateDto {

    /** Board Table PK **/
    private Integer id;

    private String categoryName;

    private String contents;

    private String boardFileYn;

    private String commentsYn;

    private String replyYn;

    private Integer boardFileCount;

    /** 수정시간 */
    private LocalDateTime updateTime;

    @Builder
    public BoardAdminUpdateDto(Integer id, String categoryName, String boardFileYn,
                               String commentsYn, String replyYn, Integer boardFileCount,
                               String contents, LocalDateTime updateTime) {
        this.id = id;
        this.categoryName = categoryName;
        this.contents = contents;
        this.replyYn = replyYn;
        this.boardFileYn = boardFileYn;
        this.commentsYn = commentsYn;
        this.boardFileCount = boardFileCount;
        this.updateTime = updateTime.now();
    }
}
