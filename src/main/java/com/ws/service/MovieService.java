package com.ws.service;

import com.ws.bean.Movie;

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
}
