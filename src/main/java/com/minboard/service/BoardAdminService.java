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

    @Transactional(readOnly = true)
    public BoardAdminVo getBoardAdmin(int id){

        return boardAdminMapper.selectBoardAdmin(id);
    }

    @Transactional(readOnly = true)
    public BoardAdminVo getBoardCategory(Integer categoryNumber){

        return boardAdminMapper.selectBoardCategory(categoryNumber);
    }

    @Transactional(readOnly = true)
    public List<BoardAdminVo> getBoardAdminList(BoardAdminRequestDto boardAdminRequestDto){

        return boardAdminMapper.selectBoardAdminList(boardAdminRequestDto);
    }

    @Transactional
    public void removeBoardAdmin(int id){

        List<BoardVo> boardIdList = boardMapper.findByBoardList(id);

        for (BoardVo boardVo : boardIdList){
            uploadFileMapper.deleteBoardFileList(boardVo.getBoardId());
        }
        boardAdminMapper.updateIsDeleteBoardAdmin(id);
    }

    @Transactional(readOnly = true)
    public List<BoardAdminVo> getBoardCategoryList(){

        return boardAdminMapper.selectBoardCategoryList();
    }

}
