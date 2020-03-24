package com.ws.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ws.bean.Comment;
import com.ws.bean.CommentExample;
import com.ws.bean.MovieExample;
import com.ws.mapper.CommentMapper;
import com.ws.mapper.MovieMapper;
import com.ws.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    CommentExample commentExample;
    @Autowired
    MovieMapper movieMapper;
    @Autowired
    MovieExample movieExample;


    @Override
    public PageInfo<Comment> getCommentsByMovieId(String movieId, Integer size, Integer page, String orderBy) {
        PageHelper.startPage(page, size);
        PageHelper.orderBy(orderBy);
        return new PageInfo<>(commentMapper.getCommentsByMovieId(movieId));
    }

    @Transactional
    @Override
    public void addComment(Comment comment) throws Exception {
        commentMapper.insertSelective(comment);
    }

    @Override
    public PageInfo<Comment> getCommentsByUserId(Integer userId, Integer page, Integer size, String orderBy) {
        CommentExample.Criteria criteria = commentExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        PageHelper.startPage(page, size);
        PageHelper.orderBy(orderBy);
        List<Comment> list =  commentMapper.selectByExample(commentExample);
        PageInfo<Comment> pageInfo = new PageInfo<>(list);
        commentExample.clear();
        pageInfo.setList(list);
        return pageInfo;
    }
}
