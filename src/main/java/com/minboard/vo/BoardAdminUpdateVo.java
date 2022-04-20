package com.minboard.vo;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardAdminUpdateVo {

    /** Board Table PK **/
    private Integer id;

    private String categoryName;

    private String contents;

    private String attachedFileYn;

    private String commentsYn;

    private String replyYn;

    private Integer attachedFileCount;

    /** 수정시간 */
    private LocalDateTime updateTime;

    @Builder
    public BoardAdminUpdateVo(Integer id, String categoryName, String attachedFileYn,
                              String commentsYn, String replyYn, Integer attachedFileCount,
                              String contents, LocalDateTime updateTime) {
        this.id = id;
        this.categoryName = categoryName;
        this.contents = contents;
        this.replyYn = replyYn;
        this.attachedFileYn = attachedFileYn;
        this.commentsYn = commentsYn;
        this.attachedFileCount = attachedFileCount;
        this.updateTime = updateTime.now();
    }
}
