package com.minboard.vo;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
public class BoardSaveVo {

    /** Board Table PK **/
    private Integer id;

    /** 게시글 제목 **/
    @NotBlank(message = "제목은 필수값 입니다.")
    private String title;

    /** 게시글 내용 **/
    @NotBlank(message = "내용은 필수값 입니다.")
    private String contents;

    /** 등록일 */
    private LocalDateTime createTime;

    /** 수정일 */
    private LocalDateTime updateTime;

    @Builder
    public BoardSaveVo(Integer id, String title, String contents, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.createTime = createTime.now();
        this.updateTime = updateTime.now();
    }
}
