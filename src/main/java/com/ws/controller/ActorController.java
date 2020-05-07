package com.ws.controller;

import com.github.pagehelper.PageInfo;
import com.ws.bean.Actor;
import com.ws.bean.Admin;
import com.ws.bean.Log;
import com.ws.service.ActorService;
import com.ws.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Random;

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

    @Autowired
    LogService logService;
    /**
     * 根据电影id获取演员列表
     * @param movieId
     * @return
     */
    @RequestMapping("getActorsById")
    public List<Actor> getActorsById(@RequestParam("movieId") String movieId){
        return actorService.getActorsByMovieId(movieId);
    }

    /**
     * 根据id获取演员，主要用于后台修改信息
     * @param actorId
     * @return
     */
    @RequestMapping("findActorById")
    public ModelAndView findActorById(String actorId){
        Actor actor = actorService.getActorsById(actorId);
        actor.setImgUrl(actorId);
        if(actor.getActorId().length() == 16){
            actor.setImgUrl("default");
        }
        ModelAndView m = new ModelAndView();
        m.addObject("actor", actor);
        m.setViewName("actor_update");
        return m;
    }
    @RequestMapping("toAddActor")
    public ModelAndView toAddActor(){
        ModelAndView m = new ModelAndView();
        m.setViewName("add_actor");
        return m;
    }
    @RequestMapping("findActorByName")
    public boolean findActorByName(String name){
        return actorService.getActorByName(name);
    }

    @RequestMapping("addActor")
    public ModelAndView addActor(Actor actor, HttpServletRequest req){
        actor.setActorId("143"+(new Random().nextInt(59999)));
        actorService.addActor(actor);

        Admin opAdmin = (Admin) req.getSession().getAttribute("loginAdmin");
        Log log = new Log();
        log.setCreatTime(new Date());
        log.setOpAdmin(opAdmin.getAdminId());
        log.setOpType(1);
        log.setOpContent("添加了演员"+actor.getActorId()+actor.getName());
        logService.saveLogger(log);

        ModelAndView m = new ModelAndView();
        m.setViewName("actorList");
        return m;
    }

}
