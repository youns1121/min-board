package com.minboard.service.impl;

import com.minboard.dto.BoardCommentsUpdateDto;
import com.minboard.dto.BoardCommentsReplySaveDto;
import com.minboard.dto.CommentsSaveDto;
import com.minboard.mapper.BoardCommentsMapper;
import com.minboard.service.CommentsService;
import com.minboard.vo.BoardCommentsVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CommentsServiceImpl implements CommentsService {

    private final BoardCommentsMapper commentsMapper;

    @Transactional
    @Override
    public void saveComments(CommentsSaveDto commentsSaveDto) {

        commentsSaveDto.setCommentsInit(0, 0);
        commentsMapper.insertComments(commentsSaveDto);
        commentsMapper.updateCommentsSetGroup(commentsSaveDto);
    }

    @Override
    public List<BoardCommentsVo> getBoardHierarchicalCommentsList(int boardId) {

        return commentsMapper.getBoardHierarchicalCommentsList(boardId);
    }

    @Transactional
    @Override
    public void modifyComments(BoardCommentsUpdateDto boardCommentsUpdateDto) {

        commentsMapper.updateComments(boardCommentsUpdateDto);
    }

    @Transactional
    @Override
    public void removeComments(BoardCommentsUpdateDto boardCommentsUpdateDto) {

        commentsMapper.updateIsDeleteComments(boardCommentsUpdateDto);
        commentsMapper.updateCommentsSortDecrease(boardCommentsUpdateDto);
    }

    @Transactional
    @Override
    public void saveCommentsReply(BoardCommentsReplySaveDto commentsReplySaveDto) {

        int calculationResult = commentsMapper.getHierarchicalCalculationFormula(commentsReplySaveDto);

        if (calculationResult == 0){
            calculationResultZero(commentsReplySaveDto);
        }else {
            commentsReplySaveDto.setSort(calculationResult);
            calculationResultNotZero(commentsReplySaveDto);
        }
    }

    @Transactional
    public void calculationResultZero(BoardCommentsReplySaveDto commentsReplySaveDto){

        commentsReplySaveDto.setSort(commentsMapper.getCalculationFormulaResultZero(commentsReplySaveDto));
        commentsMapper.insertCommentsReply(commentsReplySaveDto);
    }

    @Transactional
    public void calculationResultNotZero(BoardCommentsReplySaveDto commentsReplySaveDto){

        commentsMapper.updateCommentsSortIncrease(commentsReplySaveDto);
        commentsMapper.insertCommentsReply(commentsReplySaveDto);
    }
}
