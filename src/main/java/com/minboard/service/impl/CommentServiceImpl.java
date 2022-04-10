package com.minboard.service.impl;

import com.minboard.dto.CommentsDto;
import com.minboard.mapper.CommentsMapper;
import com.minboard.service.CommentService;
import com.minboard.vo.CommentsReplySaveVo;
import com.minboard.vo.CommentsSaveVo;
import com.minboard.vo.CommentsUpdateVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentsMapper commentsMapper;

    @Override /** 댓글작성 **/
    public void insertComments(CommentsSaveVo commentsSaveVo) {
        commentsMapper.insertComments(commentsSaveVo);
        commentsMapper.insertCommentsSetGroup(commentsSaveVo);
    }

    @Override /** 게시물의 계층형 댓글전체 보기 **/
    public List<CommentsDto> getBoardHierarchicalCommentsList(int boardId) {
        List<CommentsDto> hierarchicalCommentsAll = commentsMapper.getBoardHierarchicalCommentsList(boardId);
        return hierarchicalCommentsAll;
    }

    @Override
    public void updateComments(CommentsUpdateVo commentsUpdateVo) {
        commentsMapper.updateComments(commentsUpdateVo);
    }

    @Override
    public void deleteComment(int id) {
        commentsMapper.deleteComment(id);
    }

    @Override
    public void deleteAllComment(int boardId) {
        commentsMapper.deleteAllComment(boardId);
    }

    @Override
    public void insertCommentsReply(CommentsReplySaveVo commentsReplySaveVo) {

        int calculationResult = commentsMapper.hierarchicalCalculationFormula(commentsReplySaveVo);

        if (calculationResult == 0){
            calculationResultZero(commentsReplySaveVo);
        }

        if(calculationResult != 0){
            commentsReplySaveVo.setSort(calculationResult);
            calculationResultNotZero(commentsReplySaveVo);
        }
    }

    public void calculationResultZero(CommentsReplySaveVo commentsReplySaveVo){
        int addSortValue = commentsMapper.calculationFormulaResultZero(commentsReplySaveVo);
        commentsReplySaveVo.setSort(addSortValue);
        commentsMapper.insertResultZero(commentsReplySaveVo);
    }

    public void calculationResultNotZero(CommentsReplySaveVo commentsReplySaveVo){
        commentsMapper.calculationFormulaResultNotZero(commentsReplySaveVo);
        commentsMapper.insertResultNotZero(commentsReplySaveVo);
    }
}
