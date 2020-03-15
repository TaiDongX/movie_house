package com.ws.controller;

import com.ws.bean.Comment;
import com.ws.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 王朔
 * Description:
 * @date: 2020.03.14 15:16
 * @since JDK 1.8
 */
@RestController
@RequestMapping("comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    /**
     * 根据电影id获取对应的评论列表
     * @param movieId
     * @return
     */
    @RequestMapping("getCommentsById")
    public List<Comment> getCommentsById(@RequestParam("movieId")String movieId,
                                         @RequestParam("size")Integer size,
                                         @RequestParam("page")Integer page,
                                         @RequestParam("orderBy")String orderBy){
        return  commentService.getCommentsByMovieId(movieId,size,page,orderBy);
    }

}
