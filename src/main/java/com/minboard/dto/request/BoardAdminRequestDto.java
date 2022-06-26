package com.minboard.dto.request;


import com.minboard.dto.BoardFileDto;
import com.minboard.paging.CommonPageDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@Getter
@Setter
public class BoardAdminRequestDto extends CommonPageDto {

    private Integer id;

    private String title;

    private String contents;

    private Integer commentsCount;

    private Integer fileCount;

    List<BoardFileDto> uploadFileDtoList;

    private Integer boardSort;

    private Integer boardDepth;

    private Integer boardGroup;

    private Integer boardId;

    private Integer boardAdminId;

    private String categoryName;

    private int categoryNumber;

    private Integer boardFileCount;

    private String commentsYn;

    private String replyYn;

    private String boardFileYn;

    private String delYn;


    @Builder
    public BoardAdminRequestDto(Integer id, String title, String contents,
                                Integer commentsCount, Integer fileCount, List<BoardFileDto> uploadFileDtoList,
                                Integer boardSort, Integer boardDepth, Integer boardGroup,
                                Integer boardId, Integer boardAdminId, String categoryName, int categoryNumber,
                                Integer boardFileCount, String commentsYn, String replyYn,
                                String boardFileYn, String delYn) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.commentsCount = commentsCount;
        this.fileCount = fileCount;
        this.uploadFileDtoList = uploadFileDtoList;
        this.boardSort = boardSort;
        this.boardDepth = boardDepth;
        this.boardGroup = boardGroup;
        this.boardId = boardId;
        this.boardAdminId = boardAdminId;
        this.categoryName = categoryName;
        this.categoryNumber = categoryNumber;
        this.boardFileCount = boardFileCount;
        this.commentsYn = commentsYn;
        this.replyYn = replyYn;
        this.boardFileYn = boardFileYn;
        this.delYn = delYn;
    }
}
