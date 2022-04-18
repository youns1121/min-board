package com.minboard.vo;

import com.minboard.dto.BoardDto;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class BoardAdminSaveVo {

    /** Board Table PK **/
    private Integer id;


    private String categoryName;

    private Integer categoryCode;

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
    public BoardAdminSaveVo(String categoryName, Integer categoryCode, String attachedFileYn, String commentsYn,
                            String replyYn, Integer attachedFileCount, String contents,
                            LocalDateTime createTime, LocalDateTime updateTime, String title) {

        this.categoryName = categoryName;
        this.categoryCode = categoryCode;
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
