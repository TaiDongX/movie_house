package com.ws.service;

import com.ws.bean.DirW;

import java.util.List;

/**
 * @author 王朔
 * Description:
 * @date: 2020.03.14 15:11
 * @since JDK 1.8
 */
public interface DirWService {
    List<DirW> getDirWsByMovieId(String movieId);

    void setDirWsStatus(List<DirW> list,String movieId);
}
