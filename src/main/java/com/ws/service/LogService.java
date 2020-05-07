package com.ws.service;

import com.github.pagehelper.PageInfo;
import com.ws.VO.QueryLogVO;
import com.ws.bean.Log;

/**
 * @author 王朔
 * Description:
 * @date: 2020.04.19 16:07
 * @since JDK 1.8
 */
public interface LogService {

    void saveLogger(Log log);

    PageInfo<Log> getLogByVO(QueryLogVO vo);
}
