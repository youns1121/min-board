package com.minboard.service;


import com.minboard.dto.BoardAdminDto;
import com.minboard.dto.BoardDto;
import com.minboard.dto.UploadFileDto;
import com.minboard.mapper.BoardAdminMapper;
import com.minboard.mapper.BoardMapper;
import com.minboard.mapper.UploadFileMapper;
import com.minboard.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardAdminService{

    private final BoardAdminMapper boardAdminMapper;
    private final BoardMapper boardMapper;
    private final UploadFileMapper uploadFileMapper;

    /** 게시물 생성 **/
    public void saveBoardAdminSetting(BoardAdminSaveVo boardAdminSaveVo) {
        boardAdminMapper.insertBoardAdminSetting(boardAdminSaveVo);
    }

    public void updateBoardAdminSetting(BoardAdminUpdateVo boardAdminUpdateVo){
        boardAdminMapper.updateBoardAdminSetting(boardAdminUpdateVo);
    }


    public BoardAdminDto selectBoardAdmin(int id){
        BoardAdminDto boardAdminDto = boardAdminMapper.selectBoardAdmin(id);
        return boardAdminDto;
    }

    public BoardAdminDto getBoardCategory(int categoryNumber){
        BoardAdminDto boardAdminDto = boardAdminMapper.selectBoardCategory(categoryNumber);
        return boardAdminDto;
    }

    public List<BoardAdminDto> selectBoardAdminList(BoardAdminDto boardAdminDto){

        List<BoardAdminDto> boardAdminDtoList = boardAdminMapper.selectBoardAdminList(boardAdminDto);

        return boardAdminDtoList;

    }

    public void removeBoardAdmin(int id){
        List<BoardDto> boardIdList = boardMapper.findByBoardList(id);
        int size = boardIdList.size();

        for(int i=0; i< size; i++) {
            uploadFileMapper.deleteAllFile(boardIdList.get(i).getBoardId());
        }
        boardMapper.deleteBoardAdmin(id);
    }

    public List<BoardAdminDto> selectBoardCategoryList(){
        List<BoardAdminDto> BoardCategoryList = boardAdminMapper.selectBoardCategoryList();

        return BoardCategoryList;
    }

    public int totalCountCategoryBoard(int categoryNumber){
        int totalCountCategoryBoard = boardAdminMapper.totalCountCategoryBoard(categoryNumber);
        return totalCountCategoryBoard;
    }


}
