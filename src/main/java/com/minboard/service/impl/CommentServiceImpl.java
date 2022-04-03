package com.minboard.service.impl;

import com.minboard.mapper.CommentsMapper;
import com.minboard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private static CommentsMapper commentsMapper;

    @Override
    public void insertComments(int id) {
        commentsMapper.insertComments(id);
    }
}
