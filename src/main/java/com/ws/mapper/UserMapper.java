package com.ws.mapper;

import com.ws.bean.User;
import com.ws.bean.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int getMovieCollectById(@Param("movieId")String movieId, @Param("userId")String userId);

    void addCollect(@Param("userId")String userId,@Param("movieId")String movieId);

    void cancelCollect(@Param("userId")String userId,@Param("movieId")String movieId);
}