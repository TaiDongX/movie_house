package com.ws.mapper;

import com.ws.bean.Fun;
import com.ws.bean.FunExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FunMapper {
    long countByExample(FunExample example);

    int deleteByExample(FunExample example);

    int deleteByPrimaryKey(Integer funId);

    int insert(Fun record);

    int insertSelective(Fun record);

    List<Fun> selectByExample(FunExample example);

    Fun selectByPrimaryKey(Integer funId);

    int updateByExampleSelective(@Param("record") Fun record, @Param("example") FunExample example);

    int updateByExample(@Param("record") Fun record, @Param("example") FunExample example);

    int updateByPrimaryKeySelective(Fun record);

    int updateByPrimaryKey(Fun record);
}