package com.minboard.service;


import com.minboard.dto.BoardAdminSaveDto;
import com.minboard.dto.BoardAdminUpdateDto;
import com.minboard.dto.request.BoardAdminRequestDto;
import com.minboard.mapper.BoardAdminMapper;
import com.minboard.mapper.BoardMapper;
import com.minboard.mapper.BoardFileMapper;
import com.minboard.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BoardAdminService{

    private final BoardAdminMapper boardAdminMapper;
    private final BoardMapper boardMapper;
    private final BoardFileMapper uploadFileMapper;

    @Transactional
    public void saveBoardAdminSetting(BoardAdminSaveDto boardAdminSaveDto) {

        boardAdminMapper.insertBoardAdminSetting(boardAdminSaveDto);
    }

    @Transactional
    public void modifyBoardAdminSetting(BoardAdminUpdateDto boardAdminUpdateDto){

        boardAdminMapper.updateBoardAdminSetting(boardAdminUpdateDto);
    }


    public BoardAdminVo getBoardAdmin(int id){

        return boardAdminMapper.selectBoardAdmin(id);
    }

    public BoardAdminVo getBoardCategory(int categoryNumber){

        return boardAdminMapper.selectBoardCategory(categoryNumber);
    }

    public List<BoardAdminVo> getBoardAdminList(BoardAdminRequestDto boardAdminRequestDto){

        return boardAdminMapper.selectBoardAdminList(boardAdminRequestDto);
    }

    @Transactional
    public void removeBoardAdmin(int id){

        List<BoardVo> boardIdList = boardMapper.findByBoardList(id);
        int size = boardIdList.size();

//        for(int i=0; i< size; i++) {
//            uploadFileMapper.deleteBoardFileList(boardIdList.get(i).getBoardId());
//        }

        for (BoardVo boardVo : boardIdList){
            uploadFileMapper.deleteBoardFileList(boardVo.getBoardId());
        }
        boardAdminMapper.updateIsDeleteBoardAdmin(id);
    }

    public List<BoardAdminVo> getBoardCategoryList(){

        return boardAdminMapper.selectBoardCategoryList();
    }

}
