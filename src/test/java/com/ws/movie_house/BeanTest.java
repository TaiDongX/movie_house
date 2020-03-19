package com.ws.movie_house;

import com.ws.bean.Movie;
import com.ws.service.MovieService;
import com.ws.service.imp.MovieServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 王朔
 * Description:
 * @date: 2020.03.15 11:08
 * @since JDK 1.8
 */
public class BeanTest {
    MovieService movieService = new MovieServiceImpl();

    @Test
    public void test(){
        Set<Movie> set = new HashSet<>();
        List<Movie> list = Arrays.asList(new Movie("4920389"),new Movie("4920389"),new Movie("4920389"));
        set.add(new Movie("4920389"));
        set.addAll(list);
        System.out.println(set.size());
    }
}
