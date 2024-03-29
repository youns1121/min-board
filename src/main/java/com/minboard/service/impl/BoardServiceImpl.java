package com.minboard.service.impl;


import com.minboard.dto.BoardSaveDto;
import com.minboard.dto.request.BoardRequestDto;
import com.minboard.mapper.*;
import com.minboard.paging.PaginationInfo;
import com.minboard.service.BoardService;
import com.minboard.service.MemberService;
import com.minboard.vo.BoardVo;
import com.minboard.dto.BoardUpdateDto;
import com.minboard.vo.BoardFileVo;
import com.minboard.vo.member.MemberVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;


@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardFileServiceImpl fileStoreService;
    private final BoardMapper boardMapper;
    private final BoardAdminMapper boardAdminMapper;
    private final BoardFileMapper fileMapper;
    private final BoardCommentsMapper commentsMapper;
    private final MemberMapper memberMapper;

    @Value("${custom.path.uploadPath}")
    private String uploadPath;

    @Override
    @Transactional
    public void saveBoard(BoardSaveDto boardSaveDto) {

        MemberVo member = memberMapper.findByMemberId(SecurityContextHolder.getContext().getAuthentication().getName());
        if (member != null){
            boardSaveDto.setMemberSeq(member.getMemberSeq());
        }

        boardSaveDto.setBoardSortAndDepth();
        boardMapper.insertBoard(boardSaveDto);
        boardMapper.updateBoardGroupSet(boardSaveDto.getId());
    }

    @Override
    @Transactional
    public void saveBoardFile(List<MultipartFile> fileList, int boardId) throws IOException {

        if(!CollectionUtils.isEmpty(fileList)) {
             fileStoreService.saveBoardFileList(fileStoreService.storeFiles(fileList, boardId));
        }
    }


    @Override
    @Transactional(readOnly = true)
    public BoardVo getDetailViewBoard(int id) {

        return boardMapper.selectBoard(id);
    }

    @Override
    @Transactional(readOnly = true)
    public BoardVo getBoardReply(int id) {

        return boardMapper.selectBoardReply(id);
    }

    @Override
    @Transactional
    public void removeBoard(int id) {
        List<BoardFileVo> boardFileList = fileMapper.selectBoardFileList(id);
        int boardFileListSize = boardFileList.size();

        for(int i = 0; i < boardFileListSize; i++){
            File file = new File(uploadPath + boardFileList.get(i).getStoreFileName() + "." +
                    boardFileList.get(i).getExtensionName());

            if(file.exists()){
                file.delete();
            }
        }
        commentsMapper.updateIsDeleteAllComments(id);
        BoardVo boardReply = boardMapper.selectBoardReply(id);

        if(boardReply.getBoardSort() > 0) {
            boardMapper.updateBoardSortDecrease(boardReply);
        }
        boardMapper.deleteBoard(id);
        fileMapper.deleteBoardFileList(id);
    }

    @Override
    @Transactional
    public void modifyBoard(BoardUpdateDto boardUpdateVo) {
       boardMapper.updateBoard(boardUpdateVo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BoardVo> getBoardList(BoardRequestDto requestDto) {

        List<BoardVo> boardList = Collections.emptyList();
        int boardTotalCount = boardAdminMapper.totalCountCategoryBoard(requestDto.getCategoryNumber());
        PaginationInfo paginationInfo = new PaginationInfo(requestDto);
        paginationInfo.setTotalRecordCount(boardTotalCount);
        requestDto.setPaginationInfo(paginationInfo);

        if(boardTotalCount > 0){
            boardList = boardMapper.selectBoardList(requestDto);
        }
        return boardList;
    }

    @Transactional
    @Override
    public void saveBoardReply(BoardSaveDto boardSaveDto){

        int calculationResult = boardMapper.selectHierarchicalCalculationFormula(boardSaveDto);

        if(calculationResult == 0){
            calculationResultZero(boardSaveDto);
        }else {
            boardSaveDto.setBoardSort(calculationResult);
            calculationResultNotZero(boardSaveDto);
        }
    }

    @Transactional
    public void calculationResultZero(BoardSaveDto boardSaveDto){

        boardSaveDto.setBoardSort(boardMapper.selectCalculationFormulaResultZero(boardSaveDto));
        boardMapper.insertBoardReply(boardSaveDto);
    }

    @Transactional
    public void calculationResultNotZero(BoardSaveDto boardSaveDto){

        boardMapper.updateBoardSortIncrease(boardSaveDto);
        boardMapper.insertBoardReply(boardSaveDto);
    }

}
