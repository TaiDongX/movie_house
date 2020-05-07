package com.ws.controller;

import com.ws.VO.TypeVO;
import com.ws.bean.*;
import com.ws.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 王朔
 * Description:
 * @date: 2020.03.28 15:57
 * @since JDK 1.8
 */
@RequestMapping("admin")
@Controller
public class AdminController {

    @Autowired
    AdminService adminService;
    @Autowired
    RoleService roleService;
    @Autowired
    FunService funService;
    @Autowired
    TypeService typeService;
    @Autowired
    CommentService commentService;
    @Autowired
    MovieService movieService;



    @RequestMapping("toLogin")
    public ModelAndView toLogin(){
        ModelAndView m = new ModelAndView();
        m.setViewName("login_register");
        return m;
    }

    @RequestMapping("login")
    public ModelAndView Login(HttpServletRequest req,@RequestParam("loginName")String loginName, @RequestParam("pwd")String pwd){
        Admin admin = adminService.getAdminByLoginNameAndPassword(loginName, pwd);
        ModelAndView m = new ModelAndView();
        if(admin != null){
            admin.setRole(roleService.getRoleByRoleId(admin.getRoleId()));
            req.getSession().setAttribute("loginAdmin", admin);
            m.setViewName("adminIndex");
            /*return "登录成功！";*/
        } else{
            m.setViewName("login_register");
           /* return "登录失败！";*/
        }
        return m;
    }

    /**
     * 跳转到日志管理页面
     * @return
     */
    @RequestMapping("toLogManager")
    public ModelAndView toLogManager(){
        ModelAndView m = new ModelAndView();
        m.setViewName("admin_log");
        return m;
    }

    /**
     * 跳转到评论管理页面
     * @return
     */
    @RequestMapping("toCommentManager")
    public ModelAndView toCommentManager(){
        ModelAndView m = new ModelAndView();
        m.setViewName("movie_comments");
        return m;
    }

    /**
     * 跳转到日志管理页面
     * @return
     */
    @RequestMapping("toMovieManager")
    public ModelAndView toMovieManager(){
        ModelAndView m = new ModelAndView();
        m.setViewName("showMovie");
        return m;
    }

    /**
     * 跳转到演员管理页面
     * @param req
     * @return
     */
    @RequestMapping("toActorManager")
    public ModelAndView toActorManager(HttpServletRequest req){
        ModelAndView m = new ModelAndView();
        m.setViewName("actorList");
        return m;
    }

    /**
     * 跳转到管理员管理页面
     * @return
     */
    @RequestMapping("toAdminManager")
    public ModelAndView toAdminManager(HttpServletRequest req){
        ModelAndView m = new ModelAndView();
        Admin admin = (Admin) req.getSession().getAttribute("loginAdmin");
        m.addObject("aList",adminService.getAllAdmins().stream().peek(a ->{
            a.setRole(roleService.getRoleByRoleId(a.getRoleId()));
        }).filter(a -> !a.getAdminId().equals(admin.getAdminId())).collect(Collectors.toList()));

        m.setViewName("adminList");
        return m;
    }

    /**
     * 获取需要展示的数据并跳转页面
     * @return
     */
    @RequestMapping("toDataShow")
    public ModelAndView toDataShow(){
        ModelAndView m = new ModelAndView();
        List<Type> list = typeService.findAll();
        // 获取每种电影类型的收藏数量
        m.addObject("typec",list.stream()
                .map(type -> new TypeVO(typeService.getTypeColls(type.getTypeId()),type.getTypeName()))
                .collect(Collectors.toList()));
        // 获取每种电影类型的评论数量
        List<String> typeNames = new ArrayList<>();
        list.forEach(t -> typeNames.add(t.getTypeName()));
        List<Integer> count = commentService.getCountByType(list);
        m.addObject("typeName", typeNames);
        m.addObject("numList", count);
        List<Integer> movieCount = movieService.getMovieCountByType(list);
        m.addObject("movieCount",movieCount);

        m.setViewName("reportForm");
        return m;
    }

    /**
     * 根据id获取管理员信息
     * @param adminId
     * @return
     */
    @RequestMapping("findAdminById")
    public ModelAndView findAdminById(Integer adminId){
        ModelAndView m = new ModelAndView();
        Admin admin = adminService.getAdminById(adminId);
        admin.setRole(roleService.getRoleByRoleId(admin.getRoleId()));
        m.addObject("admin",admin);
        m.setViewName("update_admin");
        return m;
    }

    /**
     * 解雇管理员
     * @param adminId
     * @return
     */
    @RequestMapping("deleteAdmin")
    public String deleteAdmin(Integer adminId){
        Admin admin = adminService.getAdminById(adminId);
        admin.setStatus(-1);
        adminService.updateAdmin(admin);
        return "redirect:toAdminManager";
    }

    /**
     * 检查管理员登录名是否存在，true为存在，false为不存在
     * @param name
     * @return
     */
    @RequestMapping("adminIsExisted")
    @ResponseBody
    public Boolean adminIsExisted(String name){
        return adminService.isExisted(name);
    }

    @RequestMapping("toAddAdmin")
    public ModelAndView toAddAdmin(){
        ModelAndView m = new ModelAndView();
        m.setViewName("add_admin");
        return m;
    }

    @RequestMapping("quit")
    public String quit(HttpServletRequest req){
        req.getSession().removeAttribute("loginAdmin");
        return "redirect:toLogin";
    }
    @RequestMapping("toChangePwd")
    public ModelAndView toChangePwd(){
        ModelAndView m = new ModelAndView();
        m.setViewName("change_pwd");
        return m;
    }

    @RequestMapping("changePwd")
    @ResponseBody
    public String changePwd(HttpServletRequest req,String password){
        Admin admin = (Admin) req.getSession().getAttribute("loginAdmin");
        admin.setPassword(password);
        adminService.updateAdmin(admin);
        return "修改成功";
    }

    @RequestMapping("showInfo")
    public ModelAndView showInfo(HttpServletRequest req){
        ModelAndView m = new ModelAndView();
        Admin admin = (Admin)req.getSession().getAttribute("loginAdmin");
        m.setViewName("admin_info");
        m.addObject("admin", admin);
        return m;
    }

}
