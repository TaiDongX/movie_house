package com.ws.service;

import com.github.pagehelper.PageInfo;
import com.ws.VO.QueryMoviesVO;
import com.ws.bean.Movie;
import com.ws.bean.Type;

import java.util.List;

/**
 * @author 王朔
 * Description:
 * @date: 2020.03.12 11:55
 * @since JDK 1.8
 */

public interface MovieService {
    /**
     * 获取即将上映的电影
     * @return
     */
    List<Movie> getMoviesComingSoon(int region_id);

    List<Movie> getMoviesByRegion(int region_id,String orderBy);

    List<Movie> getMostReviewedMovies(int regionId);

    List<Movie> getHostMovies();

    Movie getMovieById(String movieId);

    List<Movie> getMoviesByActor(Movie m);

    List<Movie> getMoviesByType(Movie m);

    PageInfo<Movie> getMoviesByPage(QueryMoviesVO vo);

    List<Movie> getMovieByActorId(String actorId);

    PageInfo<Movie> getMovieByUserId(Integer userId,Integer page,Integer size,String orderBy);

    void updateRate(String movieId, Float rate) throws Exception;

    void updateCollectCount(String movieId, int i);

    PageInfo<Movie> getMoviesByPageIgnoreStatus(QueryMoviesVO vo);

    void changeMovieStatus(String movieId, Integer status);

    void updateMovie(Movie m);

    List<Integer> getMovieCountByType(List<Type> list);
}
