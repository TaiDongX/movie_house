/**
 * All rights Reserved, Designed By Android_Robot
 * @Title:  QueryMoviesVO.java
 * @Package com.movie.vo
 * @Description:
 * @author: ws
 * @date:   2019年10月9日 下午3:26:16
 * @version V1.0
 */
package com.ws.VO;

import java.util.Date;
import java.util.List;

import com.ws.bean.Region;
import com.ws.bean.Type;

/**
 * @ClassName:  QueryMoviesVO
 * @Description: 电影查询条件实体类
 * @author: ws
 * @date:   2019年10月9日 下午3:26:16
 *
 */
public class QueryMoviesVO {


    public List<Integer> type;
    public String name;
    public List<Integer> region;
    public String earlyDate;
    public String latedDate;
    public Double minScore;
    public String typeStr;
    public String regionStr;
    public Double maxScore;
    public String orderBy;
    public Integer page;
    public Integer size;


    public QueryMoviesVO() {
    }


    @Override
    public String toString() {
        return "QueryMoviesVO{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", region=" + region +
                ", earlyDate=" + earlyDate +
                ", latedDate=" + latedDate +
                ", minScore=" + minScore +
                ", typeStr='" + typeStr + '\'' +
                ", regionStr='" + regionStr + '\'' +
                ", maxScore=" + maxScore +
                ", orderBy='" + orderBy + '\'' +
                ", page=" + page +
                ", size=" + size +
                '}';
    }
}
