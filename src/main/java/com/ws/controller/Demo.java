package com.ws.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 王朔
 * Description:
 * @date: 2020.03.07 15:23
 * @since JDK 1.8
 */
@RestController
public class Demo {

    @RequestMapping(value="/hello",method= RequestMethod.GET)
    public ModelAndView sayHello(ModelAndView m){
        m.setViewName("index");
        return m;
    }
}
