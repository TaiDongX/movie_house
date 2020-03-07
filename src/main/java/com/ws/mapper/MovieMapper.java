package com.ws.mapper;

import com.ws.bean.Movie;
import com.ws.bean.MovieExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MovieMapper {
    long countByExample(MovieExample example);

    int deleteByExample(MovieExample example);

    int deleteByPrimaryKey(String movieId);

    int insert(Movie record);

    int insertSelective(Movie record);

    List<Movie> selectByExample(MovieExample example);

    Movie selectByPrimaryKey(String movieId);

    int updateByExampleSelective(@Param("record") Movie record, @Param("example") MovieExample example);

    int updateByExample(@Param("record") Movie record, @Param("example") MovieExample example);

    int updateByPrimaryKeySelective(Movie record);

    int updateByPrimaryKey(Movie record);
}