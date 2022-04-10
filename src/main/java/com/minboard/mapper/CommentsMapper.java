package com.minboard.mapper;

import com.minboard.dto.CommentsDto;
import com.minboard.vo.CommentsReplySaveVo;
import com.minboard.vo.CommentsSaveVo;
import com.minboard.vo.CommentsUpdateVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentsMapper {

    /** 댓글 작성하기 **/
    void insertComments(CommentsSaveVo commentsSaveVo);

    /** 게시물의 계층형 댓글전체 보기 **/
    List<CommentsDto> getBoardHierarchicalCommentsList(int boardId);

    /** 댓글 수정하기 **/
    void updateComments(CommentsUpdateVo commentsUpdateVo);

    /** 댓글 삭제하기 **/
    void deleteComment(int id);

    /** 게시글의 모든댓글 삭제하기 **/
    void deleteAllComment(int boardId);

    /** 댓글의 그룹값 셋팅하기 **/
    void insertCommentsSetGroup(CommentsSaveVo commentsSaveVo);

    /** 계층형 공식계산 **/
    int hierarchicalCalculationFormula(CommentsReplySaveVo commentsReplySaveVo);

    /** 공식결과값이 0일때 **/
    int calculationFormulaResultZero(CommentsReplySaveVo commentsReplySaveVo);

    /** 결과값 0 입력 **/
    void insertResultZero(CommentsReplySaveVo commentsReplySaveVo);

    /** 계산식 결과가 0이 아니면 **/
    void calculationFormulaResultNotZero(CommentsReplySaveVo commentsReplySaveVo);

    /** 결과값 0 아닌값 입력 **/
    void insertResultNotZero(CommentsReplySaveVo commentsReplySaveVo);


}
