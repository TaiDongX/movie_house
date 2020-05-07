package com.ws.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ws.VO.QueryCeleVO;
import com.ws.bean.Actor;
import com.ws.bean.ActorExample;
import com.ws.bean.Movie;
import com.ws.mapper.ActorMapper;
import com.ws.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    ActorExample actorExample;

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
        PageHelper.orderBy("length(actor_id)");
        return new PageInfo<>(actorMapper.getActorsByPage(vo));
    }

    @Override
    public Actor getActorsById(String id) {
        ActorExample.Criteria criteria = actorExample.createCriteria();
        criteria.andStatusNotEqualTo(-1);
        criteria.andActorIdEqualTo(id);
        List<Actor> list = actorMapper.selectByExample(actorExample);
        actorExample.clear();
        if(list.size() != 0){
            return list.get(0);
        }
        return null;
    }

    @Transactional
    @Override
    public void updateActor(Actor actor) {
        actorMapper.updateByPrimaryKeySelective(actor);
    }

    @Override
    public boolean getActorByName(String name) {
        ActorExample.Criteria criteria = actorExample.createCriteria();
        criteria.andNameEqualTo(name);
        List<Actor> list = actorMapper.selectByExample(actorExample);
        actorExample.clear();
        return list != null && list.size() > 1 ;
    }

    @Transactional
    @Override
    public void addActor(Actor actor) {
        actorMapper.insertSelective(actor);
    }

}
