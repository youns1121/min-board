package com.minboard.dto;

import com.minboard.vo.BoardFileVo;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class BoardSaveDto {

    private Integer id;

    @NotBlank(message = "제목은 필수값 입니다.")
    private String title;

    private Long memberSeq;

    @NotBlank(message = "내용은 필수값 입니다.")
    @Length(min = 20, max = 100,message = "내용은 20자 이상 100자 이하")
    private String contents;

    private List<MultipartFile> fileList;

    private BoardFileVo uploadFileVo;

    private BoardUpdateDto boardUpdateVo;

    private Integer boardSort;

    private Integer boardDepth;

    private Integer boardGroup;

    private Integer boardId;

    private Integer boardAdminId;

    private String categoryName;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @Builder
    public BoardSaveDto(Integer id, String title, Long memberSeq, String contents,
                        List<MultipartFile> fileList, BoardFileVo uploadFileVo, BoardUpdateDto boardUpdateVo,
                        Integer boardSort, Integer boardDepth, Integer boardGroup,
                        Integer boardId, Integer boardAdminId, String categoryName) {
        this.id = id;
        this.title = title;
        this.memberSeq = memberSeq;
        this.contents = contents;
        this.fileList = fileList;
        this.uploadFileVo = uploadFileVo;
        this.boardUpdateVo = boardUpdateVo;
        this.boardSort = boardSort;
        this.boardDepth = boardDepth;
        this.boardGroup = boardGroup;
        this.boardId = boardId;
        this.boardAdminId = boardAdminId;
        this.categoryName = categoryName;
        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
    }

    public void setBoardSortAndDepth(){

        if(boardSort == null && boardDepth == null ){

            this.boardSort = 0;
            this.boardDepth = 0;
        }
    }

    public void setBoardSort(Integer boardSort) {
        this.boardSort = boardSort;
    }

    public void setMemberSeq(Long memberSeq) {
        this.memberSeq = memberSeq;
    }
}
