package com.minboard.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class UploadFileUpdateVo {

    /** keyProperty **/
    private Integer id;

    private Integer boardId;

    /** 서버에 저장 될 파일명**/
    private String storeFileName;

    /** 실제 파일명 **/
    private String originalFileName;

    /** 확장자명 **/
    private String extensionName;

    /** 파일사이즈 **/
    private Long storeFileSize;

    /** 파일 저장 경로 **/
    private String storeFilePath;

    /** 파일 저장 시간 **/
    private LocalDateTime createTime;

    @Builder
    public UploadFileUpdateVo(Integer id, Integer boardId, String storeFileName, String originalFileName, String extensionName, Long storeFileSize, String storeFilePath, LocalDateTime createTime) {
        this.id = id;
        this.boardId = boardId;
        this.storeFileName = storeFileName;
        this.originalFileName = originalFileName;
        this.extensionName = extensionName;
        this.storeFileSize = storeFileSize;
        this.storeFilePath = storeFilePath;
        this.createTime = createTime.now();
    }
}
