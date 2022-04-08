package com.minboard.service.impl;

import com.minboard.dto.CommentsDto;
import com.minboard.mapper.CommentsMapper;
import com.minboard.service.CommentService;
import com.minboard.vo.CommentsReplySaveVo;
import com.minboard.vo.CommentsSaveVo;
import com.minboard.vo.CommentsUpdateVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentsMapper commentsMapper;

    @Override
    public void insertComments(CommentsSaveVo commentsSaveVo) {
        commentsMapper.insertComments(commentsSaveVo);
        commentsMapper.insertCommentsSetGroup(commentsSaveVo.getId());
    }

    @Override
    public List<CommentsDto> getBoardCommentsList(int boardId) {

        List<CommentsDto> boardCommentsList = commentsMapper.getBoardCommentsList(boardId);
        return boardCommentsList;
    }

    @Override /** 게시물의 계층형 댓글전체 보기 **/
    public List<CommentsDto> getBoardHierarchicalCommentsList(int boardId) {
        List<CommentsDto> hierarchicalCommentsAll = commentsMapper.getBoardHierarchicalCommentsList(boardId);
        return hierarchicalCommentsAll;
    }

    @Override
    public CommentsDto getUpdateComments(int id) {
        CommentsDto getUpdateComment = commentsMapper.getUpdateComments(id);
        return getUpdateComment;
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
        commentsReplySaveVo.sortIncrease();
        commentsReplySaveVo.commentDepthIncrease();
        int sortValue = commentsMapper.findBySameGroupYn(commentsReplySaveVo);
        if (sortValue > 0){
            commentsReplySaveVo.sortIncrease();
//            commentsMapper.CommentsReplySortUpdate(commentsReplySaveVo);
        }
        if(commentsReplySaveVo.getSort() <= sortValue){
            commentsReplySaveVo.sortIncrease(sortValue);
        }



        commentsMapper.insertCommentsReply(commentsReplySaveVo);
    }
}
