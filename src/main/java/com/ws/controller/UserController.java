package com.ws.controller;

import com.aliyuncs.exceptions.ClientException;
import com.github.pagehelper.PageInfo;
import com.ws.Util.AliYunOSSUtil;
import com.ws.Util.AliyunSMSUtil;
import com.ws.Util.DeleteFileUtil;
import com.ws.bean.Movie;
import com.ws.bean.User;
import com.ws.service.MovieService;
import com.ws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author 王朔
 * Description:
 * @date: 2020.03.20 16:40
 * @since JDK 1.8
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    MovieService movieService;

    @RequestMapping("userNameIsExist")
    public boolean userNameIsExist(String loginName) {
        return userService.findUserByLoginName(loginName) != null;
    }

    @RequestMapping("login")
    public String login(HttpServletResponse resp, HttpServletRequest req,
                        String loginName, String passWord, boolean remember) {
        // 根据用户名获取信息
        User user = userService.findUserByLoginName(loginName);
        // 如果信息不为空
        if (user != null) {
            // 如果密码相同
            if (user.getPassword().equals(passWord)) {
                // 如果用户选择了记住密码
                if (remember) {
                    Cookie cookie = new Cookie(loginName, passWord);
                    cookie.setMaxAge(60 * 60 * 24 * 7);
                    resp.addCookie(cookie);
                    resp.addCookie(new Cookie("userName", loginName));
                    // 增加登录次数
                    user.setLoginCount(user.getLoginCount() == null ? 1 : user.getLoginCount() + 1);
                    // 记录当前登录时间
                    user.setLoginTime(new Date());

                    userService.updateUser(user);
                } else {
                    resp.addCookie(new Cookie(loginName, null));
                    resp.addCookie(new Cookie("userName", null));
                }
                req.getSession().setAttribute("user", user);
                return "登录成功";
            } else {
                return "密码错误";
            }
        } else {
            return "用户名不存在";
        }
    }

    @RequestMapping("register")
    public String register(User user, String code) {
        try {
            user.setUserName(user.getLoginName());
            userService.insertUser(user);
            System.out.println("user.getUserId() = " + user.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
            return "注册失败";
        }
        return "注册成功！";
    }

    @RequestMapping("userExit")
    public String userExit(HttpServletRequest req) {

        req.getSession().removeAttribute("user");
        return "退出成功";
    }

    /**
     * 跳转到个人中心
     *
     * @return
     */
    @RequestMapping("toUserCenter")
    public ModelAndView toUserCenter(HttpServletRequest req) {
        ModelAndView m = new ModelAndView();
        m.setViewName("userfavoritelist");
        return m;
    }

    /**
     * 验证账户是否已经被记录
     *
     * @param req
     * @param loginName
     * @return
     */
    @RequestMapping("checkUser")
    public User checkUser(HttpServletRequest req, String loginName) {

        User user = new User();
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (loginName == null || loginName.equals("")) {
                    // 找到已经被记录的用户名
                    if ("userName".equals(cookie.getName())) {
                        user.setLoginName(cookie.getValue());
                        // 找寻对应的密码
                        for (Cookie c : cookies) {
                            if (user.getLoginName().equals(c.getName())) {
                                user.setPassword(c.getValue());
                            }
                        }
                    }
                } else {
                    //
                    if (loginName.equals(cookie.getName())) {
                        user.setPassword(cookie.getValue());
                    }
                }
            }
        }
        return user;
    }

    /**
     * 获取短信验证码
     *
     * @param phoneNum
     * @return
     */
    @RequestMapping("getCode")
    public String getCode(HttpServletRequest req, String phoneNum) {
        // 使用随机函数生成一个六位的验证码
        Random rand = new Random();
        int code = rand.nextInt(899999) + 100000;
        try {
            // 将验证码通过短信发送到手机端
            AliyunSMSUtil.sendSms(phoneNum, "" + code);
            // 将生成的验证码保存到session中
            req.getSession().setAttribute("code", code);
            System.out.println("code" + code);
        } catch (ClientException e) {
            e.printStackTrace();
            return "false";
        }
        return "true";
    }

    /**
     * 获取用户收藏列表，分页
     * @param req
     * @param page
     * @param size
     * @param orderBy
     * @return
     */
    @RequestMapping("getMoviesFavorite")
    public PageInfo<Movie> getMoviesFavorite(HttpServletRequest req, Integer page, Integer size, String orderBy) {
        User user = (User) req.getSession().getAttribute("user");
        return movieService.getMovieByUserId(user.getUserId(), page, size, orderBy);
    }

    /**
     * 用户修改头像
     *
     * @param file
     */
    @RequestMapping("changeHeader")
    public String changeHeader(@RequestParam(value = "file") MultipartFile file, HttpServletRequest req) {
        if (file.isEmpty()) {
            return "文件不能为空";
        }
        int userId = ((User) req.getSession().getAttribute("user")).getUserId();
        String fileName = file.getOriginalFilename();
        System.out.println("fileName = " + fileName);
        String uploadUrl = "";

        if (fileName != null && !"".equals(fileName.trim())) {
            try {
                File newFile = new File(fileName);
                FileOutputStream os = new FileOutputStream(newFile);
                os.write(file.getBytes());
                os.close();
                uploadUrl = AliYunOSSUtil.upLoad(newFile, "user" + userId + fileName.substring(fileName.lastIndexOf(".")));
                System.out.println("uploadUrl = " + uploadUrl);
                File file1=new File("");
                String s = file1.getAbsolutePath();
                DeleteFileUtil.delete(s + "\\" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "上传成功";
    }

    /**
     * 跳转到用户个人信息
     * @return
     */
    @RequestMapping("toProfile")
    public ModelAndView toProfile(){
        ModelAndView m = new ModelAndView();
        m.setViewName("userprofile");
        return m;
    }

    /**
     * 跳转到用户评论
     * @return
     */
    @RequestMapping("toUserRate")
    public ModelAndView toUserRate(){
        ModelAndView m = new ModelAndView();
        m.setViewName("userrate");
        return m;
    }


    @RequestMapping("changePWD")
    public String changePWD(HttpServletRequest req,@RequestParam("pwd") String oldPwd,@RequestParam("newPwd") String newPwd){
        User user = (User) req.getSession().getAttribute("user");

        if(!user.getPassword().equals(oldPwd)){
            return "旧密码错误";
        }
        user.setPassword(newPwd);
        userService.updateUser(user);
        return "修改成功!";
    }

}
