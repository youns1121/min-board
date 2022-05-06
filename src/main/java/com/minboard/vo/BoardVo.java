package com.minboard.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardVo {

    BoardSaveVo boardSaveVo;
    BoardUpdateVo boardUpdateVo;

    @Builder
    public BoardVo(BoardSaveVo boardSaveVo, BoardUpdateVo boardUpdateVo) {
        this.boardSaveVo = boardSaveVo;
        this.boardUpdateVo = boardUpdateVo;
    }
}
