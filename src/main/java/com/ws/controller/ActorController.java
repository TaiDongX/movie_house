package com.ws.controller;

import com.ws.bean.Actor;
import com.ws.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 王朔
 * Description:
 * @date: 2020.03.14 15:16
 * @since JDK 1.8
 */
@RestController
@RequestMapping("actor")
public class ActorController {

    @Autowired
    ActorService actorService;
    /**
     * 根据电影id获取演员列表
     * @param movieId
     * @return
     */
    @RequestMapping("getActorsById")
    public List<Actor> getActorsById(@RequestParam("movieId")String movieId){
        return actorService.getActorsByMovieId(movieId);
    }
}
