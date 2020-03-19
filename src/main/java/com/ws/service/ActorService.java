package com.ws.service;

import com.github.pagehelper.PageInfo;
import com.ws.VO.QueryCeleVO;
import com.ws.bean.Actor;
import com.ws.bean.Movie;

import java.util.List;

/**
 * @author 王朔
 * Description:
 * @date: 2020.03.14 9:47
 * @since JDK 1.8
 */
public interface ActorService {

    List<Actor> getActorsFamous();

    List<Actor> getActorsByMovieId(String movieId);

    PageInfo<Actor> getActorsByPage(QueryCeleVO vo);

    Actor getActorsById(String id);
}
