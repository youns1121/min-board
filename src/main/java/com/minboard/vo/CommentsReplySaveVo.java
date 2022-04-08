package com.minboard.vo;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentsReplySaveVo {

    private int id;

    private int boardId;

    private String contents;

    private int commentGroup;

    private int commentDepth;

    private int sort;

    private LocalDateTime createTime;


    public void sortIncrease(){
        this.sort++;
    }
    public void commentDepthIncrease(){
        this.commentDepth++;
    }
    public void sortIncrease(int sort){
        this.sort = sort + 1;
    }


    @Builder
    public CommentsReplySaveVo(int boardId, String contents, int commentGroup,
                               int commentDepth, int sort, LocalDateTime createTime) {

        this.boardId = boardId;
        this.contents = contents;
        this.commentGroup = commentGroup;
        this.sort = sort;
        this.commentDepth = commentDepth;
        this.createTime = createTime.now();
    }


}
