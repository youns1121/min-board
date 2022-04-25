package com.minboard.vo;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class BoardAdminSaveVo {

    /** Board Table PK **/
    private Integer id;

    private String categoryName;

    private Integer categoryNumber;

    private String title;

    private String contents;

    private String attachedFileYn;

    private String commentsYn;

    private String replyYn;

    private Integer attachedFileCount;

    /** 등록시간 */
    private LocalDateTime createTime;

    /** 수정시간 */
    private LocalDateTime updateTime;

    @Builder
    public BoardAdminSaveVo(String categoryName, String attachedFileYn, String commentsYn,
                            String replyYn, Integer attachedFileCount, String title,
                            String contents, LocalDateTime createTime, LocalDateTime updateTime) {

        this.categoryName = categoryName;
        this.categoryNumber = (int)(Math.random() * 10000);
        this.attachedFileYn = attachedFileYn;
        this.commentsYn = commentsYn;
        this.title = title;
        this.contents = contents;
        this.replyYn = replyYn;
        this.attachedFileCount = attachedFileCount;
        this.createTime = createTime.now();
        this.updateTime = updateTime.now();
    }
}