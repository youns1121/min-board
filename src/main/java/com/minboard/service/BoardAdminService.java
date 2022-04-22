package com.minboard.service;


import com.minboard.dto.BoardAdminDto;
import com.minboard.dto.BoardDto;
import com.minboard.dto.UploadFileDto;
import com.minboard.mapper.BoardMapper;
import com.minboard.mapper.UploadFileMapper;
import com.minboard.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardAdminService{

    private final BoardMapper boardMapper;
    private final UploadFileMapper uploadFileMapper;

    /** 게시물 생성 **/
    public void saveBoardAdminSetting(BoardAdminSaveVo boardAdminSaveVo) {
        boardMapper.insertBoardAdminSetting(boardAdminSaveVo);
    }

    public void updateBoardAdminSetting(BoardAdminUpdateVo boardAdminUpdateVo){
        boardMapper.updateBoardAdminSetting(boardAdminUpdateVo);
    }


    public BoardAdminDto selectBoardAdmin(int id){
        BoardAdminDto boardAdminDto = boardMapper.selectBoardAdmin(id);
        return boardAdminDto;
    }

    public BoardAdminDto getBoardCategory(int id){
        return boardMapper.getBoardCategory(id);
    }

    public List<BoardAdminDto> selectBoardAdminList(BoardAdminDto boardAdminDto){

        List<BoardAdminDto> boardAdminDtoList = boardMapper.selectBoardAdminList(boardAdminDto);

        return boardAdminDtoList;

    }

    public void removeBoardAdmin(int id){
        boardMapper.deleteBoardAdmin(id);
        List<BoardDto> boardIdList = boardMapper.findByBoardIdList(id);
        int size = boardIdList.size();

        for(int i=0; i< size; i++) {
            uploadFileMapper.deleteAllFile(boardIdList.get(i).getBoardId());
        }
    }
}
