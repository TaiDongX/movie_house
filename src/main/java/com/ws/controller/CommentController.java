package com.ws.controller;

import com.github.pagehelper.PageInfo;
import com.ws.bean.Comment;
import com.ws.bean.User;
import com.ws.service.CommentService;
import com.ws.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.stream.Collectors;

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
    @Autowired
    MovieService movieService;

    /**
     * 根据电影id获取对应的评论列表
     *
     * @param movieId
     * @return
     */
    @RequestMapping("getCommentsById")
    public PageInfo<Comment> getCommentsById(@RequestParam("movieId") String movieId,
                                             @RequestParam("size") Integer size,
                                             @RequestParam("page") Integer page,
                                             @RequestParam("orderBy") String orderBy) {


        return commentService.getCommentsByMovieId(movieId, size, page, orderBy);
    }

    /**
     * 添加评论
     *
     * @param info
     * @param movieId
     * @param userId
     * @param rate
     * @return
     */
    @RequestMapping("addComment")
    public String addComment(String info, String movieId, Integer userId, Float rate) {
        Comment comment = new Comment();
        info = info.replace("马化腾", "***");
        comment.setComments(info);
        comment.setDate(new Date());
        comment.setMovieId(movieId);
        comment.setRate(rate);
        comment.setUserId(userId);
        System.out.println("comment = " + comment);
        System.out.println("rate = " + rate);
        try {
            commentService.addComment(comment);
            movieService.updateRate(movieId, rate);
        } catch (Exception e) {
            e.printStackTrace();
            return "评论失败";
        }
        return "评论成功";
    }

    @RequestMapping("getCommentByUser")
    public PageInfo<Comment> getCommentByUser(HttpServletRequest req, Integer size, Integer page, String orderBy) {
        User user = (User) req.getSession().getAttribute("user");
        PageInfo<Comment> pageInfo = commentService.getCommentsByUserId(user.getUserId(), page, size, orderBy);
        pageInfo.setList(pageInfo.getList().stream().peek(
                c -> c.setMovie(movieService.getMovieById(c.getMovieId()))
        ).collect(Collectors.toList()));
        return pageInfo;
    }

}
