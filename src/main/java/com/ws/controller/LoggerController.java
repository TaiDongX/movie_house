package com.ws.controller;

import com.github.pagehelper.PageInfo;
import com.ws.VO.QueryLogVO;
import com.ws.bean.Log;
import com.ws.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王朔
 * Description:
 * @date: 2020.04.23 9:44
 * @since JDK 1.8
 */
@RestController
@RequestMapping("Log")
public class LoggerController {

    @Autowired
    LogService logService;

    @RequestMapping("getAdminLog")
    public PageInfo<Log> getAdminLog(@RequestBody QueryLogVO vo){
        return logService.getLogByVO(vo);
    }
}
