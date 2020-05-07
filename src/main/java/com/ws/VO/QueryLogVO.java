package com.ws.VO;

import java.util.Date;

/**
 * @author 王朔
 * Description:
 * @date: 2020.04.23 9:42
 * @since JDK 1.8
 */
public class QueryLogVO {
    private Date startTime;
    private Date endTime;
    private Integer page;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "QueryLogVO{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", page=" + page +
                '}';
    }
}
