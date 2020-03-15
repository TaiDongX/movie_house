package com.ws.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.ws.VO.IndexInfoVO;
import com.ws.bean.Actor;
import com.ws.bean.Movie;
import com.ws.mapper.MovieMapper;
import com.ws.service.ActorService;
import com.ws.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 王朔
 * Description:
 * @date: 2020.03.07 21:41
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ActorService actorService;


    /**
     * 获取最受欢迎的电影：收藏数最多
     * @return
     */
    @RequestMapping("/getMostPopularMovies")
    public List<Movie> getMostPopularMovies(){
        return null;
    }

    /**
     * 分页查询
     * @return
     */
    @RequestMapping("/getMoviesByPage")
    public List<Movie> getMoviesByPage(){
        return null;
    }

    /**
     * 获取首页需要推荐的所有不同地区的电影集合的集合
     * @return
     */
    @RequestMapping("/getIndexMovies")
    public ModelAndView getIndexMovies(){
        ModelAndView m = new ModelAndView();
        IndexInfoVO infoVO = new IndexInfoVO();
        // 热门电影
        infoVO.hostMovies = movieService.getHostMovies();

        // 最受欢迎的电影
        infoVO.popular1 = movieService.getMoviesByRegion(1, "collect_count DESC");
        infoVO.popular2 = movieService.getMoviesByRegion(2, "collect_count DESC");
        // 即将上映的电影
        infoVO.comingSoon1 = movieService.getMoviesComingSoon(1);
        infoVO.comingSoon2 = movieService.getMoviesComingSoon(2);
        // 评分最高的电影
        infoVO.topRate1 = movieService.getMoviesByRegion(1, "rate DESC");
        infoVO.topRate2 = movieService.getMoviesByRegion(2, "rate DESC");
        // 讨论最多的电影
        infoVO.mostReviewed1 = movieService.getMostReviewedMovies(1);
        infoVO.mostReviewed2 = movieService.getMostReviewedMovies(2);

        // 首页演员榜
        infoVO.actorList = actorService.getActorsFamous();
        m.setViewName("index");
        m.addObject("info",infoVO);
        return m;
    }

    @RequestMapping("/getMovieById")
    public ModelAndView getMovieById(@RequestParam("movieId")String movieId){
        ModelAndView m = new ModelAndView();
        m.setViewName("moviesingle");
        m.addObject("movie", movieService.getMovieById(movieId));
        return m;
    }




}
