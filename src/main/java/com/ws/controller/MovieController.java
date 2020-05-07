package com.ws.controller;

import com.github.pagehelper.PageInfo;
import com.ws.VO.IndexInfoVO;
import com.ws.VO.QueryMoviesVO;
import com.ws.bean.Admin;
import com.ws.bean.Log;
import com.ws.bean.Movie;
import com.ws.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
    private UserService userService;

    @Autowired
    private ActorService actorService;

    @Autowired
    private DirWService dirWService;

    @Autowired
    private LogService logService;

    @Autowired
    private RedisTemplate redisTemplate;





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
        IndexInfoVO infoVO ;
        ValueOperations<String,IndexInfoVO> vo = redisTemplate.opsForValue();
        if(vo.get("info") == null) {
            infoVO = new IndexInfoVO();
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
            vo.set("info", infoVO);
        }
        else{
            infoVO = vo.get("info");
        }
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
        List<Movie> list;
        Movie m = movieService.getMovieById(movieId);
        // 根据电影获取相应演员对应的电影
        Set<Movie> set = new HashSet<>(movieService.getMoviesByActor(m));
        // 如果获得的数量太少
        list = new ArrayList<>(set);

        if(set.size() < 9){
            // 根据电影类型获取相关电影
            set.addAll(movieService.getMoviesByType(m));
            Random r = new Random();
            list = new ArrayList<>(set);
            while(list.size() > 9){
                list.remove(r.nextInt(list.size() - 6) + 6);
            }
        }
        list.remove(m);
        System.out.println(list.size());


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

    /**
     * 跳转到电影网格页面
     * @return
     */
    @RequestMapping("toMovieGrid")
    public ModelAndView toMovieGrid(){
        ModelAndView m = new ModelAndView();
        m.addObject(10);
        m.setViewName("moviegrid");
        return m;
    }

    /**
     * 根据演员获取相关的电影列表
     * @param actorId
     * @return
     */
    @RequestMapping("getMoviesByActorId")
    public List<Movie> getMoviesByActorId(String actorId){
        return movieService.getMovieByActorId(actorId);
    }

    /**
     * 判断电影是否已经上映
     * @param movieId
     * @return
     */
    @RequestMapping("isPublish")
    public boolean isPublish(String movieId){
        return movieService.getMovieById(movieId).getReleaseDate().after(new Date());
    }

    /**
     * 验证该电影是否已经被用户收藏
     * @param movieId
     * @param userId
     * @return
     */
    @RequestMapping("checkCollect")
    public boolean checkCollect(String movieId,String userId){
        return  userService.getMovieCollect(movieId,userId) > 0;
    }

    /**
     * 提供用户收藏功能
     * @param movieId
     * @param userId
     * @return
     */
    @RequestMapping("addCollect")
    public String addCollect(String movieId,String userId){
        if(userService.getMovieCollect(movieId,userId) > 0){
            userService.cancelCollect(userId,movieId);
            movieService.updateCollectCount(movieId,-1);
            return "已取消收藏";
        }
        else{
            userService.addCollect(userId,movieId);
            movieService.updateCollectCount(movieId,1);
            return "收藏成功";
        }
    }

    /**
     * 转到电影管理页面
     * @return
     */
    @RequestMapping("showMovies")
    public ModelAndView showMovies(){
        ModelAndView m = new ModelAndView();
        m.setViewName("showMovie");
        return m;
    }

    /**
     * 获取后台电影列表
     * @param vo
     * @return
     */
    @RequestMapping("getBackMovieList")
    public PageInfo<Movie> getBackMovieList(@RequestBody QueryMoviesVO vo){
        System.out.println("vo = " + vo);
        return movieService.getMoviesByPageIgnoreStatus(vo);
    }

    /**
     * 修改电影状态
     * @param movieId
     * @param status
     * @param req
     */
    @RequestMapping("changeMovieStatus")
    public void changeMovieStatus(String movieId, Integer status, HttpServletRequest req){
        // 修改状态
        movieService.changeMovieStatus(movieId,status);
        Admin admin = (Admin) req.getSession().getAttribute("loginAdmin");
        // 保存日志
        Log log = new Log();
        log.setCreatTime(new Date());
        log.setOpAdmin(admin.getAdminId());
        log.setOpType(3);
        String content = status == -1 ? "上架": "下架";
        log.setOpContent(content+"了电影"+movieId);
        logService.saveLogger(log);
    }

    /**
     * 查询电影信息以供修改
     * @param movieId
     * @return
     */
    @RequestMapping("findMovieForUpdate")
    public ModelAndView findMovieForUpdate(String movieId){
        ModelAndView m = new ModelAndView();
        Movie movie = movieService.getMovieById(movieId);
        movie.setActorList(actorService.getActorsByMovieId(movieId));
        m.addObject("movie",movie);
        m.addObject("mo", movie.getTypeList());
        m.setViewName("movie_update");
        return m;
    }

    @RequestMapping("updateMovie")
    public String updateMovie(@RequestBody Movie m,HttpServletRequest req){
        Admin admin = (Admin) req.getSession().getAttribute("loginAdmin");
        movieService.updateMovie(m);
        Log log = new Log();
        log.setCreatTime(new Date());
        log.setOpAdmin(admin.getAdminId());
        log.setOpType(3);
        log.setOpContent("修改了电影"+m.getMovieId()+"的信息");
        logService.saveLogger(log);

        return "修改成功";
    }
}
