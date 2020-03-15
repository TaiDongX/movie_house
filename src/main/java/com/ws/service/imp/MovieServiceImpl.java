package com.ws.service.imp;

import com.github.pagehelper.PageHelper;
import com.ws.bean.*;
import com.ws.mapper.*;
import com.ws.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
    CommentMapper commentMapper;
    @Autowired
    TypeMapper typeMapper;
    @Autowired
    RegionMapper regionMapper;
    @Autowired 
    DirWMapper dirWMapper;
    @Autowired
    TypeExample typeExample;
    @Autowired
    MovieExample movieExample;
    @Autowired
    ActorExample actorExample;
    @Autowired
    CommentExample commentExample;

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
        List<Movie> list = movieMapper.getMostReviewedMovies(regionId);
        movieExample.clear();
        return list;
    }

    @Override
    public List<Movie> getHostMovies() {
        MovieExample.Criteria criteria= movieExample.createCriteria();
        criteria.andStatusEqualTo(1);
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

}
