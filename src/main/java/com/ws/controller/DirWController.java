package com.ws.controller;

import com.ws.bean.DirW;
import com.ws.service.DirWService;
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
@RequestMapping("dirW")
public class DirWController {
    @Autowired
    DirWService dirWService;

    /**
     * 根据电影id获取导演编剧列表
     * @param movieId
     * @return
     */
    @RequestMapping("getDirWsByMovieId")
    public List<DirW> getDirWsByMovieId(@RequestParam("movieId")String movieId){
        return dirWService.getDirWsByMovieId(movieId);
    }
}
