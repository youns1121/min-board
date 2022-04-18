package com.minboard.service;


import com.minboard.dto.BoardAdminDto;
import com.minboard.dto.BoardDto;
import com.minboard.dto.UploadFileDto;
import com.minboard.mapper.BoardMapper;
import com.minboard.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardAdminService{

    private final BoardMapper boardMapper;

    /** 게시물 생성 **/
    public void saveBoardAdminSetting(BoardAdminSaveVo boardAdminSaveVo) {
        boardMapper.insertBoardAdminSetting(boardAdminSaveVo);
    }

    public BoardAdminDto selectBoardAdmin(int id){
        BoardAdminDto boardAdminDto = boardMapper.selectBoardAdmin(id);
        return boardAdminDto;
    }

    public List<BoardAdminDto> selectBoardAdminList(BoardAdminDto boardAdminDto){

        List<BoardAdminDto> boardAdminDtoList = boardMapper.selectBoardAdminList(boardAdminDto);

        return boardAdminDtoList;

    }

    public void saveBoardAdmin(BoardAdminSaveVo boardAdminSaveVo) {
    }
}
