package com.minboard.service.impl;

import com.minboard.dto.CommentsDto;
import com.minboard.mapper.CommentsMapper;
import com.minboard.service.CommentService;
import com.minboard.vo.CommentsSaveVo;
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
    }

    @Override
    public List<CommentsDto> getBoardCommentsList(int boardId) {

        List<CommentsDto> boardCommentsList = commentsMapper.getBoardCommentsList(boardId);
        return boardCommentsList;
    }
}
