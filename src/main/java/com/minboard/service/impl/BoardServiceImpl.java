package com.minboard.service.impl;


import com.minboard.dto.BoardDto;
import com.minboard.dto.UploadFileDto;
import com.minboard.mapper.BoardAdminMapper;
import com.minboard.mapper.BoardMapper;
import com.minboard.mapper.CommentsMapper;
import com.minboard.mapper.UploadFileMapper;
import com.minboard.paging.PaginationInfo;
import com.minboard.service.BoardService;
import com.minboard.vo.BoardSaveVo;
import com.minboard.vo.BoardUpdateVo;
import com.minboard.vo.UploadFileUpdateVo;
import com.minboard.vo.UploadFileVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final FileStoreServiceImpl fileStoreService;
    private final BoardMapper boardMapper;
    private final BoardAdminMapper boardAdminMapper;
    private final UploadFileMapper fileMapper;
    private final CommentsMapper commentsMapper;

    @Value("${custom.path.uploadPath}")
    private String uploadPath;


    /** 게시물 생성 **/
    @Override
    public void saveBoard(BoardSaveVo boardSaveVo) {
        boardSaveVo.setBoardSortDepth(boardSaveVo);
        boardMapper.insertBoard(boardSaveVo);
        boardMapper.updateBoardGroupSet(boardSaveVo.getId());
    }


    @Override
    public void saveBoardFile(BoardSaveVo boardSaveVo) throws IOException {

        if(CollectionUtils.isEmpty(boardSaveVo.getFileList()) == false) {
            List<UploadFileVo> uploadFileInfoList = fileStoreService.storeFiles(boardSaveVo.getFileList(),
                    boardSaveVo.getId());
            if(CollectionUtils.isEmpty(uploadFileInfoList) == false) {
                fileStoreService.insertFileInfoList(uploadFileInfoList);
            }
        }
    }

    @Override
    public void updateBoardFile(BoardUpdateVo boardUpdateVo) throws IOException {
        if(CollectionUtils.isEmpty(boardUpdateVo.getFileList()) == false) {
            List<UploadFileUpdateVo> uploadFileInfoList = fileStoreService.storeFilesUpdate(boardUpdateVo.getFileList(),
                    boardUpdateVo.getId());

            if(CollectionUtils.isEmpty(uploadFileInfoList) == false) {
                fileStoreService.updateFileInfoList(uploadFileInfoList);
            }
        }
    }

    /** 게시물 상세보기 **/
    @Override
    @Transactional(readOnly = true)
    public BoardDto getDetailViewBoard(int id) {
        BoardDto detailViewBoard = boardMapper.selectBoard(id);
        return detailViewBoard;
    }

    @Override
    public BoardDto getBoardReply(int id) {

        BoardDto boardReply = boardMapper.selectBoardReply(id);
        return boardReply;
    }

    @Override
    public void removeBoard(int id) {
        List<UploadFileDto> uploadFileList = fileMapper.getUploadFileList(id);
        int uploadFileListSize= uploadFileList.size();
        for(int i=0; i < uploadFileListSize; i++){
            File file = new File(uploadPath + uploadFileList.get(i).getStoreFileName() + "." +
                    uploadFileList.get(i).getExtensionName());

            if(file.exists()){
                file.delete();
            }
        }
        commentsMapper.deleteAllComment(id);
        BoardDto boardReply = boardMapper.selectBoardReply(id);
        boardMapper.decreaseSort(boardReply);
        boardMapper.deleteBoard(id);
        fileMapper.deleteAllFile(id);

    }

    /** 게시물 수정 **/
    @Override
    public void modifyBoard(BoardUpdateVo boardUpdateVo) {
       boardMapper.updateBoard(boardUpdateVo);
    }

    /** 게시물 리스트 **/
    @Override
    @Transactional(readOnly = true)
    public List<BoardDto> getBoardList(BoardDto boardDto) {


        boardDto.setCategoryNumber(boardDto.getCategoryNumber());
        List<BoardDto> boardList = Collections.emptyList();
        int boardTotalCount = boardAdminMapper.totalCountCategoryBoard(boardDto.getCategoryNumber());
        PaginationInfo paginationInfo = new PaginationInfo(boardDto);
        paginationInfo.setTotalRecordCount(boardTotalCount);
        boardDto.setPaginationInfo(paginationInfo);

        if(boardTotalCount > 0){
            boardList = boardMapper.selectBoardList(boardDto);
        }
        return boardList;
    }

    @Override
    public void saveBoardReply(BoardSaveVo boardSaveVo){
        int calculationResult = boardMapper.hierarchicalCalculationFormula(boardSaveVo);

        if(calculationResult == 0){
            calculationResultZero(boardSaveVo);
        }

        if(calculationResult != 0){
            boardSaveVo.setBoardSort(calculationResult);
            calculationResultNotZero(boardSaveVo);
        }
    }

    public void calculationResultZero(BoardSaveVo boardSaveVo){
        int addSortValue = boardMapper.calculationFormulaResultZero(boardSaveVo);
        boardSaveVo.setBoardSort(addSortValue);
        boardMapper.insertBoareReply(boardSaveVo);
    }

    public void calculationResultNotZero(BoardSaveVo boardSaveVo){
        boardMapper.calculationFormulaResultNotZero(boardSaveVo);
        boardMapper.insertBoareReply(boardSaveVo);
    }

}
