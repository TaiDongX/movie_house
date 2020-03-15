package com.ws.service.imp;

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

}
