package com.ws.mapper;

import com.ws.bean.DirW;
import com.ws.bean.DirWExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DirWMapper {
    long countByExample(DirWExample example);

    int deleteByExample(DirWExample example);

    int deleteByPrimaryKey(String dId);

    int insert(DirW record);

    int insertSelective(DirW record);

    List<DirW> selectByExample(DirWExample example);

    DirW selectByPrimaryKey(String dId);

    int updateByExampleSelective(@Param("record") DirW record, @Param("example") DirWExample example);

    int updateByExample(@Param("record") DirW record, @Param("example") DirWExample example);

    int updateByPrimaryKeySelective(DirW record);

    int updateByPrimaryKey(DirW record);
}