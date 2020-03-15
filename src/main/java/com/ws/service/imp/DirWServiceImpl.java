package com.ws.service.imp;

import com.ws.bean.DirW;
import com.ws.bean.DirWExample;
import com.ws.mapper.DirWMapper;
import com.ws.service.DirWService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 王朔
 * Description:
 * @date: 2020.03.14 15:12
 * @since JDK 1.8
 */
@Service
public class DirWServiceImpl implements DirWService {

    @Autowired
    DirWMapper dirWMapper;
    @Autowired
    DirWExample dirWExample;

    @Override
    public List<DirW> getDirWsByMovieId(String movieId) {
        return dirWMapper.getDirWsByMovieId(movieId);
    }
}
