package com.ws.controller;

import com.github.pagehelper.PageInfo;
import com.ws.VO.QueryCeleVO;
import com.ws.VO.QueryMoviesVO;
import com.ws.bean.Actor;
import com.ws.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 王朔
 * Description:
 * @date: 2020.03.18 16:23
 * @since JDK 1.8
 */
@RestController
@RequestMapping("cele")
public class CelebrityController {

    @Autowired
    ActorService actorService;

    @RequestMapping("toCeleGrid")
    public ModelAndView toCeleGrid(){
        ModelAndView m = new ModelAndView();
        m.setViewName("celebritygrid01");
        return m;
    }

    @RequestMapping("getCeleByPage")
    public PageInfo<Actor> getCeleByPage(@RequestBody QueryCeleVO vo){
        System.out.println(vo);
        return actorService.getActorsByPage(vo);
    }

    @RequestMapping("getCeleById")
    public ModelAndView getCeleById(String id){
        ModelAndView m = new ModelAndView();
        m.setViewName("celebritySingle");
        m.addObject("actor",actorService.getActorsById(id));
        return m;
    }
}
