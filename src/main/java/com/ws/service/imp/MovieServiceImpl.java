package com.ws.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ws.VO.QueryMoviesVO;
import com.ws.bean.*;
import com.ws.mapper.*;
import com.ws.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 王朔
 * Description: 电影的服务层
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

    /**
     * 获取即将上映的电影
     *
     * @param region_id
     * @return
     */
    @Override
    public List<Movie> getMoviesComingSoon(int region_id) {

        MovieExample.Criteria criteria = movieExample.createCriteria();
        criteria.andReleaseDateGreaterThan(new Date());
        if (region_id == 0) {
            criteria.andRegionIdNotEqualTo(1);
        } else {
            criteria.andRegionIdEqualTo(region_id);
        }
        criteria.andStatusNotEqualTo(-1);
        PageHelper.startPage(1, 10);
        PageHelper.orderBy("`release_date` ASC");
        List<Movie> list = movieMapper.selectByExample(movieExample);
        movieExample.clear();
        return list;
    }

    /**
     * 根据地区获取电影
     *
     * @param region_id
     * @param orderBy
     * @return
     */
    @Override
    public List<Movie> getMoviesByRegion(int region_id, String orderBy) {
        MovieExample.Criteria criteria = movieExample.createCriteria();
        if (region_id == 0) {
            criteria.andRegionIdNotEqualTo(1);
        } else {
            criteria.andRegionIdEqualTo(region_id);
        }
        criteria.andStatusNotEqualTo(-1);
        PageHelper.startPage(1, 10);
        PageHelper.orderBy(orderBy);
        List<Movie> list = movieMapper.selectByExample(movieExample);
        movieExample.clear();
        return list;

    }

    /**
     * 获取电影评论最多的电影
     *
     * @param regionId
     * @return
     */
    @Override
    public List<Movie> getMostReviewedMovies(int regionId) {
        MovieExample.Criteria criteria = movieExample.createCriteria();
        if (regionId == 0) {
            criteria.andRegionIdNotEqualTo(1);
        } else {
            criteria.andRegionIdEqualTo(regionId);
        }
        criteria.andStatusNotEqualTo(-1);
        List<Movie> list = movieMapper.getMostReviewedMovies(regionId);
        movieExample.clear();
        return list;
    }

    /**
     * 获取热门电影，主要是根据状态判断
     *
     * @return
     */
    @Override
    public List<Movie> getHostMovies() {
        MovieExample.Criteria criteria = movieExample.createCriteria();
        criteria.andStatusEqualTo(1);
        criteria.andStatusNotEqualTo(-1);
        PageHelper.startPage(1, 16);
        List<Movie> list = movieMapper.selectByExample(movieExample);
        movieExample.clear();
        return list;
    }

    /**
     * 根据电影id获取电影信息
     *
     * @param movieId
     * @return
     */
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
     *
     * @param m
     * @return
     */
    @Override
    public List<Movie> getMoviesByActor(Movie m) {
        List<Movie> list = new ArrayList<>();
        actorMapper.getActorsByMovieId(m.getMovieId()).forEach(s -> {
            list.addAll(movieMapper.getMoviesByActorId(s.getActorId()));
        });

        list.forEach(ms -> {
            ms.setTypeList(typeMapper.getTypesByMovieId(ms.getMovieId()));
        });
        return list;
    }

    /**
     * 根据电影类型获取相关推荐信息
     *
     * @param m
     * @return
     */
    @Override
    public List<Movie> getMoviesByType(Movie m) {
        return new ArrayList<>(movieMapper.getMoviesByType(m.getTypeList())).stream()
                .peek(ms -> ms.setTypeList(typeMapper.getTypesByMovieId(ms.getMovieId()))).collect(Collectors.toList());
    }

    /**
     * 多条件分页查询
     *
     * @param vo
     * @return
     */
    @Override
    public PageInfo<Movie> getMoviesByPage(QueryMoviesVO vo) {

        PageHelper.startPage(vo.page, vo.size);
        PageHelper.orderBy(vo.orderBy);

        return new PageInfo<>(movieMapper.getMoviesByPage(vo));
    }

    /**
     * 根据演员id获取电影列表，主要用于演员详情页的电影列表查询
     *
     * @param actorId
     * @return
     */
    @Override
    public List<Movie> getMovieByActorId(String actorId) {
        return movieMapper.getMovieByActorId(actorId).stream().peek(s -> {
            s.setTypeList(typeMapper.getTypesByMovieId(s.getMovieId()));
        }).collect(Collectors.toList());
    }

    /**
     * 获取用户收藏的电影列表
     *
     * @param userId
     * @return
     */
    @Override
    public PageInfo<Movie> getMovieByUserId(Integer userId, Integer page, Integer size, String orderBy) {
        PageHelper.startPage(page, size);
        PageHelper.orderBy(orderBy);
        return new PageInfo<>(movieMapper.getMovieByUserId(userId).stream().peek(s -> {
            String info = s.getMovieInfo();
            s.setMovieInfo(info.length() > 80 ? info.substring(0, 80) + "..." : info);
            s.setTypeList(typeMapper.getTypesByMovieId(s.getMovieId()));
        }).collect(Collectors.toList()));
    }

    @Transactional
    @Override
    public void updateRate(String movieId, Float rate) throws Exception {
        Movie movie = movieMapper.selectByPrimaryKey(movieId);
        Float oldRate = movie.getRate();
        BigDecimal newRate = new BigDecimal(rate).multiply(new BigDecimal(0.1));
        if(movie.getRate() == 0){
            movie.setRate(rate);
        }
        else{
            movie.setRate(oldRate < rate ?
                    new BigDecimal(oldRate).add(newRate).floatValue() :
                    new BigDecimal(oldRate).subtract(newRate).floatValue());
        }
        MovieExample.Criteria criteria = movieExample.createCriteria();
        criteria.andMovieIdEqualTo(movieId);
        movieMapper.updateByExample(movie, movieExample);
        movieExample.clear();


    }

    @Override
    public void updateCollectCount(String movieId, int i) {
        Movie movie = movieMapper.selectByPrimaryKey(movieId);
        movie.setCollectCount(movie.getCollectCount() + i);
        MovieExample.Criteria criteria = movieExample.createCriteria();
        criteria.andMovieIdEqualTo(movieId);
        movieMapper.updateByExample(movie, movieExample);
        movieExample.clear();
    }


}
