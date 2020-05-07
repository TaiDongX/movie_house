package com.ws.service;

import com.github.pagehelper.PageInfo;
import com.ws.bean.Comment;
import com.ws.bean.Type;

import java.util.List;

/**
 * @author 王朔
 * Description:
 * @date: 2020.03.14 15:21
 * @since JDK 1.8
 */
public interface CommentService {

    PageInfo<Comment> getCommentsByMovieId(String movieId, Integer size, Integer page, String orderBy);

    void addComment(Comment comment) throws Exception;

    PageInfo<Comment> getCommentsByUserId(Integer userId, Integer page, Integer size, String orderBy);

    List<Integer> getCountByType(List<Type> list);
}
