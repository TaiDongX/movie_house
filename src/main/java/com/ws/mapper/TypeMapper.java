package com.ws.mapper;

import com.ws.bean.Movie;
import com.ws.bean.Type;
import com.ws.bean.TypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TypeMapper {
    long countByExample(TypeExample example);

    int deleteByExample(TypeExample example);

    int deleteByPrimaryKey(Integer typeId);

    int insert(Type record);

    int insertSelective(Type record);

    List<Type> selectByExample(TypeExample example);

    Type selectByPrimaryKey(Integer typeId);

    int updateByExampleSelective(@Param("record") Type record, @Param("example") TypeExample example);

    int updateByExample(@Param("record") Type record, @Param("example") TypeExample example);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);

    List<Type> getTypesByMovieId(String movieId);

    void updateMovieTypes(@Param("movie") Movie m);

    void deleteMovieTypes(@Param("movie") Movie m);

    Integer getTypeColls(Integer typeId);
}