package com.ws.VO;

import java.util.Date;

/**
 * @author 王朔
 * Description:
 * @date: 2020.03.18 17:11
 * @since JDK 1.8
 */
public class QueryCeleVO {
    public String name;// 根据演员多条件查询
    public String sex;// 根据性别多条件查询
    public String minBir;
    public String maxBir;// 根据出生年月的范围查询

    public Integer size;
    public Integer page;

    public QueryCeleVO() {
    }

    @Override
    public String toString() {
        return "QueryCeleVO{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", minBir=" + minBir +
                ", maxBir=" + maxBir +
                ", size=" + size +
                ", page=" + page +
                '}';
    }
}
