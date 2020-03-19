package com.ws.listener;

import com.ws.service.RegionService;
import com.ws.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author 王朔
 * Description:
 * @date: 2020.03.17 10:59
 * @since JDK 1.8
 */
@WebListener
public class ApplicationListener implements ServletContextListener {

    @Autowired
    TypeService typeService;
    @Autowired
    RegionService regionService;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext application = sce.getServletContext();
        application.setAttribute("typeList", typeService.findAll());
        System.out.println("所有类型已加载");
        application.setAttribute("regionList", regionService.findAll());
        System.out.println("所有地区已加载");
    }
}
