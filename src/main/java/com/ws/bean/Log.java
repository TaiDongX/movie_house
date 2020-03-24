package com.ws.bean;

import java.io.Serializable;
import java.util.Date;

public class Log implements Serializable {
    private Integer logId;

    private Integer opType;

    private Integer opAdmin;

    private Date creatTime;

    private String opContent;

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Integer getOpType() {
        return opType;
    }

    public void setOpType(Integer opType) {
        this.opType = opType;
    }

    public Integer getOpAdmin() {
        return opAdmin;
    }

    public void setOpAdmin(Integer opAdmin) {
        this.opAdmin = opAdmin;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public String getOpContent() {
        return opContent;
    }

    public void setOpContent(String opContent) {
        this.opContent = opContent == null ? null : opContent.trim();
    }
}