package com.ws.service.imp;

import com.github.pagehelper.PageHelper;
import com.ws.bean.Comment;
import com.ws.mapper.CommentMapper;
import com.ws.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 王朔
 * Description:
 * @date: 2020.03.14 15:21
 * @since JDK 1.8
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;


    @Override
    public List<Comment> getCommentsByMovieId(String movieId, Integer size, Integer page, String orderBy) {
        PageHelper.startPage(page, size);
        PageHelper.orderBy(orderBy);
        return commentMapper.getCommentsByMovieId(movieId);
    }
}
