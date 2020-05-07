package com.ws.controller;

import com.ws.bean.Actor;
import com.ws.bean.Admin;
import com.ws.bean.Log;
import com.ws.service.ActorService;
import com.ws.service.AdminService;
import com.ws.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author 王朔
 * Description:
 * @date: 2020.04.28 9:39
 * @since JDK 1.8
 */
@Controller
@RequestMapping("view")
public class ViewController {

    @Autowired
    AdminService adminService;
    @Autowired
    LogService logService;
    @Autowired
    ActorService actorService;

    @RequestMapping(value = "addAdmin", method = RequestMethod.POST)
    public String addAdmin(HttpServletRequest req, Admin admin){
        Admin opAdmin = (Admin) req.getSession().getAttribute("loginAdmin");
        adminService.addAdmin(admin);
        Log log = new Log();
        log.setCreatTime(new Date());
        log.setOpAdmin(opAdmin.getAdminId());
        log.setOpType(1);
        log.setOpContent("添加了新的管理员"+admin.getName());
        logService.saveLogger(log);
        return "redirect:/admin/toAdminManager";
    }

    @RequestMapping("updateAdmin")
    public String updateAdmin(HttpServletRequest req,Admin admin){
        Admin opAdmin = (Admin) req.getSession().getAttribute("loginAdmin");
        adminService.updateAdmin(admin);
        Log log = new Log();
        log.setCreatTime(new Date());
        log.setOpAdmin(opAdmin.getAdminId());
        log.setOpType(1);
        log.setOpContent("添加了新的管理员"+admin.getName());
        logService.saveLogger(log);
        return "redirect:/admin/toAdminManager";
    }

    @RequestMapping("updateActor")
    public String updateActor(HttpServletRequest req, Actor actor){
        Admin opAdmin = (Admin) req.getSession().getAttribute("loginAdmin");
        actorService.updateActor(actor);
        Log log = new Log();
        log.setCreatTime(new Date());
        log.setOpAdmin(opAdmin.getAdminId());
        log.setOpType(3);
        log.setOpContent("修改了演员"+actor.getName()+"的信息");
        logService.saveLogger(log);
        return "redirect:/admin/toActorManager";
    }

}
