package com.minboard.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Getter
@Setter
public class BoardAdminSaveDto {

    private Integer id;

    @NotBlank(message = "카테고리 이름은 필수 값입니다.")
    private String categoryName;

    @NotBlank(message = "카테고리 내용은 필수 값입니다.")
    private String contents;

    private int categoryNumber;

    private Integer commentsCount;

    private Integer boardFileCount;

    private String commentsYn;

    private String replyYn;

    private String boardFileYn;

    private String delYn;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @Builder
    public BoardAdminSaveDto(Integer id, String contents, String categoryName,
                             Integer commentsCount, Integer boardFileCount,
                             String commentsYn, String replyYn,
                             String boardFileYn, String delYn) {
        this.id = id;
        this.contents = contents;
        this.categoryName = categoryName;
        this.categoryNumber = (int)(Math.random()* 1000);
        this.commentsCount = commentsCount;
        this.boardFileCount = boardFileCount;
        this.commentsYn = commentsYn;
        this.replyYn = replyYn;
        this.boardFileYn = boardFileYn;
        this.delYn = delYn;
        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
    }
}
