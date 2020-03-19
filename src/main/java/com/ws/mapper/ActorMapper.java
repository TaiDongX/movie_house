package com.ws.mapper;

import com.ws.VO.QueryCeleVO;
import com.ws.bean.Actor;
import com.ws.bean.ActorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActorMapper {
    long countByExample(ActorExample example);

    int deleteByExample(ActorExample example);

    int deleteByPrimaryKey(String actorId);

    int insert(Actor record);

    int insertSelective(Actor record);

    List<Actor> selectByExample(ActorExample example);

    Actor selectByPrimaryKey(String actorId);

    int updateByExampleSelective(@Param("record") Actor record, @Param("example") ActorExample example);

    int updateByExample(@Param("record") Actor record, @Param("example") ActorExample example);

    int updateByPrimaryKeySelective(Actor record);

    int updateByPrimaryKey(Actor record);

    List<Actor> getActorsFamous();

    List<Actor> getActorsByMovieId(String movieId);

    List<Actor> getActorsByPage(@Param("vo") QueryCeleVO vo);


}