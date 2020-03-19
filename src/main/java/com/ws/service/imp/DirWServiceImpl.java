package com.ws.service.imp;

import com.ws.bean.DirW;
import com.ws.bean.DirWExample;
import com.ws.mapper.DirWMapper;
import com.ws.service.DirWService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        return dirWMapper.getDirWsByMovieId(movieId).stream()
                .peek(d -> d.setStatus(dirWMapper.getDirWStatus(""+movieId+d.getdId()))).collect(Collectors.toList());
    }

    @Override
    public void setDirWsStatus(List<DirW> list,String movieId) {
        list.stream().peek(d -> d.setStatus(dirWMapper.getDirWStatus(""+movieId+d.getdId()))).collect(Collectors.toList());
    }
}
