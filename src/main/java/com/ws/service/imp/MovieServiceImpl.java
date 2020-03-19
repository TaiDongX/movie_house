package com.ws.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ws.VO.QueryMoviesVO;
import com.ws.bean.*;
import com.ws.mapper.*;
import com.ws.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 王朔
 * Description:
 * @date: 2020.03.12 11:55
 * @since JDK 1.8
 */
@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieMapper movieMapper;
    @Autowired
    ActorMapper actorMapper;
    @Autowired
    TypeMapper typeMapper;
    @Autowired
    RegionMapper regionMapper;
    @Autowired
    MovieExample movieExample;


    @Override
    public List<Movie> getMoviesComingSoon(int region_id) {

        MovieExample.Criteria criteria= movieExample.createCriteria();
        criteria.andReleaseDateGreaterThan(new Date());
        if(region_id == 0){
            criteria.andRegionIdNotEqualTo(1);
        }
        else{
            criteria.andRegionIdEqualTo(region_id);
        }
        criteria.andStatusNotEqualTo(-1);
        PageHelper.startPage(1,10);
        PageHelper.orderBy("`release_date` ASC");
        List<Movie> list = movieMapper.selectByExample(movieExample);
        movieExample.clear();
        return list;
    }

    /**
     *
     * @param region_id
     * @param orderBy
     * @return
     */
    @Override
    public List<Movie> getMoviesByRegion(int region_id,String orderBy) {
        MovieExample.Criteria criteria= movieExample.createCriteria();
        if(region_id == 0){
            criteria.andRegionIdNotEqualTo(1);
        }
        else{
            criteria.andRegionIdEqualTo(region_id);
        }
        criteria.andStatusNotEqualTo(-1);
        PageHelper.startPage(1,10);
        PageHelper.orderBy(orderBy);
        List<Movie> list = movieMapper.selectByExample(movieExample);
        movieExample.clear();
        return list;

    }

    @Override
    public List<Movie> getMostReviewedMovies(int regionId) {
        MovieExample.Criteria criteria = movieExample.createCriteria();
        if(regionId == 0){
            criteria.andRegionIdNotEqualTo(1);
        }
        else{
            criteria.andRegionIdEqualTo(regionId);
        }
        criteria.andStatusNotEqualTo(-1);
        List<Movie> list = movieMapper.getMostReviewedMovies(regionId);
        movieExample.clear();
        return list;
    }

    @Override
    public List<Movie> getHostMovies() {
        MovieExample.Criteria criteria= movieExample.createCriteria();
        criteria.andStatusEqualTo(1);
        criteria.andStatusNotEqualTo(-1);
        PageHelper.startPage(1,16);
        List<Movie> list = movieMapper.selectByExample(movieExample);
        movieExample.clear();
        return list;
    }

    @Override
    public Movie getMovieById(String movieId) {
        // 根据id获取电影
        Movie m = movieMapper.selectByPrimaryKey(movieId);
        // 获取类型列表
        m.setTypeList(typeMapper.getTypesByMovieId(movieId));
        // 获取地区
        m.setRegion(regionMapper.selectByPrimaryKey(m.getRegionId()));
        return m;
    }

    /**
     * 根据电影的演员获取相关的推荐的电影
     * @param m
     * @return
     */
    @Override
    public List<Movie> getMoviesByActor(Movie m) {
        List<Movie> list = new ArrayList<>();
        actorMapper.getActorsByMovieId(m.getMovieId()).forEach(s ->{
            list.addAll(movieMapper.getMoviesByActorId(s.getActorId()));
        });
        list.forEach(ms ->{
            ms.setTypeList(typeMapper.getTypesByMovieId(ms.getMovieId()));
        });
        return list;
    }

    /**
     * 根据电影类型获取相关推荐信息
     * @param m
     * @return
     */
    @Override
    public List<Movie> getMoviesByType(Movie m) {
        return new ArrayList<>(movieMapper.getMoviesByType(m.getTypeList())).stream()
                .peek(ms -> ms.setTypeList(typeMapper.getTypesByMovieId(ms.getMovieId()))).collect(Collectors.toList());
    }

    @Override
    public PageInfo<Movie> getMoviesByPage(QueryMoviesVO vo) {

        PageHelper.startPage(vo.page, vo.size);
        PageHelper.orderBy(vo.orderBy);

        return new PageInfo<>(movieMapper.getMoviesByPage(vo));
    }

    @Override
    public List<Movie> getMovieByActorId(String actorId) {
        return movieMapper.getMovieByActorId(actorId).stream().peek(s ->{
            s.setTypeList(typeMapper.getTypesByMovieId(s.getMovieId()));
        }).collect(Collectors.toList());
    }


}
