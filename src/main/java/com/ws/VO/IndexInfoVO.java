package com.ws.VO;

import com.ws.bean.Actor;
import com.ws.bean.Movie;

import java.util.List;

/**
 * @author 王朔
 * Description: 电影网站首页需要展示的内容：1为大陆 2为美国
 * @date: 2020.03.13 16:13
 * @since JDK 1.8
 */
public class IndexInfoVO {


    public List<Movie> popular1;

    public List<Movie> popular2;

    public List<Movie> mostReviewed1;

    public List<Movie> mostReviewed2;

    public List<Movie> topRate1;

    public List<Movie> topRate2;

    public List<Movie> comingSoon1;

    public List<Movie> comingSoon2;

    public List<Movie> hostMovies;

    public List<Actor> actorList;

    public IndexInfoVO() {
    }
}
