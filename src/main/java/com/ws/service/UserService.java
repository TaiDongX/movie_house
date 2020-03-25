package com.ws.service;

import com.ws.bean.User;

/**
 * @author 王朔
 * Description:
 * @date: 2020.03.20 16:28
 * @since JDK 1.8
 */
public interface UserService {

    User findUserByLoginName(String loginName);

    void insertUser(User user) throws Exception;

    void updateUser(User user);

    int getMovieCollect(String movieId, String userId);

    void addCollect(String userId, String movieId);

    void cancelCollect(String userId, String movieId);
}
