package com.ws.service;

import com.github.pagehelper.PageInfo;
import com.ws.bean.Comment;

import java.util.List;

/**
 * @author 王朔
 * Description:
 * @date: 2020.03.14 15:21
 * @since JDK 1.8
 */
public interface CommentService {

    PageInfo<Comment> getCommentsByMovieId(String movieId, Integer size, Integer page, String orderBy);
}
