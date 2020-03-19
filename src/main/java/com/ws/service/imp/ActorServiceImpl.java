package com.ws.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ws.VO.QueryCeleVO;
import com.ws.bean.Actor;
import com.ws.bean.Movie;
import com.ws.mapper.ActorMapper;
import com.ws.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 王朔
 * Description:
 * @date: 2020.03.14 9:48
 * @since JDK 1.8
 */
@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    ActorMapper actorMapper;

    @Override
    public List<Actor> getActorsFamous() {
        return actorMapper.getActorsFamous();
    }

    @Override
    public List<Actor> getActorsByMovieId(String movieId) {
        return actorMapper.getActorsByMovieId(movieId);
    }

    @Override
    public PageInfo<Actor> getActorsByPage(QueryCeleVO vo) {
        PageHelper.startPage(vo.page, vo.size);
        return new PageInfo<>(actorMapper.getActorsByPage(vo));
    }

    @Override
    public Actor getActorsById(String id) {
        return actorMapper.selectByPrimaryKey(id);
    }


}
