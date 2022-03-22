package com.minboard.vo;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class BoardVo {

    /** BoardVo PK **/
    private Integer id;

    /** 게시글 번호 **/
    private Integer num;

    /** 게시글 제목 **/
    @NotBlank
    private String title;

    /** 게시글 작성자 **/
    private String author;

    /** 게시글 내용 **/
    @NotBlank
    @Range(min = 5, max = 100)
    private String contents;

    /** 게시글 삭제여부, Y : 삭제, N : 미삭제 **/
    private String delYn;

    /** 게시글 조회수 **/
    private Integer viewCount;

    /** 게시글 좋아요수 **/
    private Integer likeCount;

    /** 등록일 */
    private LocalDateTime createTime;

    /** 수정일 */
    private LocalDateTime updateTime;

    @Builder
    public BoardVo(Integer id, Integer num, String title, String author, String contents, String delYn, Integer viewCount, Integer likeCount, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.num = num;
        this.title = title;
        this.author = author;
        this.contents = contents;
        this.delYn = delYn;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.createTime = createTime.now();
        this.updateTime = updateTime.now();
    }
}
