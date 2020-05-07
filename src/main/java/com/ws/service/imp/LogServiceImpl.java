package com.ws.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ws.VO.QueryLogVO;
import com.ws.bean.Log;
import com.ws.mapper.LogMapper;
import com.ws.service.AdminService;
import com.ws.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 王朔
 * Description:
 * @date: 2020.04.19 16:07
 * @since JDK 1.8
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    LogMapper logMapper;

    @Autowired
    AdminService adminService;


    @Transactional
    @Override
    public void saveLogger(Log log) {
        logMapper.insertSelective(log);
    }

    @Override
    public PageInfo<Log> getLogByVO(QueryLogVO vo) {
        PageHelper.startPage(vo.getPage(), 10);
        PageHelper.orderBy("creat_time desc");
        List<Log> list = logMapper.getLogByVO(vo);
        PageInfo<Log> pageInfo = new PageInfo<>(list);
        list = list.stream()
                .peek(t -> t.setAdmin(adminService.getAdminById(t.getOpAdmin())))
                .collect(Collectors.toList());
        pageInfo.setList(list);
        return pageInfo;
    }


}
