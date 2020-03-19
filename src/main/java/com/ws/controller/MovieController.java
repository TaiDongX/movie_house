package com.ws.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ws.VO.IndexInfoVO;
import com.ws.VO.QueryMoviesVO;
import com.ws.bean.Actor;
import com.ws.bean.Movie;
import com.ws.mapper.MovieMapper;
import com.ws.service.ActorService;
import com.ws.service.DirWService;
import com.ws.service.MovieService;
import jdk.nashorn.internal.ir.LiteralNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private DirWService dirWService;



    /**
     * 电影分页查询
     * @return
     */
    @RequestMapping("/getMoviesByPage")
    public PageInfo<Movie> getMoviesByPage(@RequestBody QueryMoviesVO vo){
        return movieService.getMoviesByPage(vo);
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

    /**
     * 根据电影id获取电影
     * @param movieId
     * @return
     */
    @RequestMapping("/getMovieById")
    public ModelAndView getMovieById(@RequestParam("movieId")String movieId){
        ModelAndView m = new ModelAndView();
        m.setViewName("moviesingle");
        m.addObject("movie", movieService.getMovieById(movieId));
        return m;
    }

    /**
     * 获取推荐电影
     * @param movieId
     * @return
     */
    @RequestMapping("getMovieRelated")
    public List<Movie> getMovieRelated(@RequestParam("movieId")String movieId){
        List<Movie> list = new ArrayList<>();
        Movie m = movieService.getMovieById(movieId);
        list.addAll(movieService.getMoviesByActor(m));
        list.addAll(movieService.getMoviesByType(m));
        Set<Movie> set = new HashSet<>(list);
        set.remove(m);
        System.out.println(set.size());
        list = new ArrayList<>(set);
        Random r = new Random();
        while(list.size() > 8){
            list.remove(r.nextInt(list.size() - 4) + 4);
        }
        list.forEach(ms ->{
            ms.setDirWList(dirWService.getDirWsByMovieId(ms.getMovieId()));

            dirWService.setDirWsStatus( ms.getDirWList(),ms.getMovieId());

            if(ms.getMovieInfo()!= null){
                String info = ms.getMovieInfo();
                ms.setMovieInfo(info.length() > 80 ? info.substring(0, 80)+"..." : info);
            }
        });
        return list;
    }

    @RequestMapping("toMovieGrid")
    public ModelAndView toMovieGrid(){
        ModelAndView m = new ModelAndView();
        m.addObject(10);
        m.setViewName("moviegrid");
        return m;
    }

    @RequestMapping("getMoviesByActorId")
    public List<Movie> getMoviesByActorId(String actorId){
        return movieService.getMovieByActorId(actorId);
    }

}
