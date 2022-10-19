package com.minboard.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class BoardVo{

    private Integer id;

    private String title;

    private String contents;

    private BoardFileVo boardFileVo;

    private int boardSort;

    private int boardDepth;

    private int boardGroup;

    private Integer boardId;

    private Integer boardAdminId;

    private String categoryName;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private int commentsCount;

    private int fileCount;

    private String delYn;

    private String replyYn;

    private String commentsYn;

    private int categoryNumber;

    private List<BoardFileVo> boardFileVoList;

    private String boardFileYn;

    private int boardFileCount;

    private String memberId;

    @Builder
    public BoardVo(Integer id, String title, String contents,
                   BoardFileVo boardFileVo,
                   int boardSort, int boardDepth, int boardGroup,
                   Integer boardId, Integer boardAdminId, String categoryName,
                   LocalDateTime createTime, LocalDateTime updateTime, int commentsCount,
                   int fileCount, String delYn, String replyYn, String commentsYn,
                   int categoryNumber, List<BoardFileVo> boardFileVoList,
                   String boardFileYn, int boardFileCount, String memberId) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.boardFileVo = boardFileVo;
        this.boardSort = boardSort;
        this.boardDepth = boardDepth;
        this.boardGroup = boardGroup;
        this.boardId = boardId;
        this.boardAdminId = boardAdminId;
        this.categoryName = categoryName;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.commentsCount = commentsCount;
        this.fileCount = fileCount;
        this.delYn = delYn;
        this.replyYn = replyYn;
        this.commentsYn = commentsYn;
        this.categoryNumber = categoryNumber;
        this.boardFileVoList = boardFileVoList;
        this.boardFileYn = boardFileYn;
        this.boardFileCount = boardFileCount;
        this.memberId = memberId;
    }
}