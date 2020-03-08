package com.ws.controller;

import com.alibaba.fastjson.JSONObject;
import com.ws.bean.Movie;
import com.ws.mapper.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
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
    private MovieMapper movieMapper;

    @RequestMapping("/getAllMovies")
    public String getAllMovies(){
       List<Movie> m = movieMapper.selectByExample(null);
       return JSONObject.toJSONString(m);
    }


}
