package com.ws.bean;

import java.io.Serializable;

public class Fun implements Serializable {
    private Integer funId;

    private String funName;

    private Integer parentFunId;

    private Integer status;

    private String description;

    private String url;

    public Integer getFunId() {
        return funId;
    }

    public void setFunId(Integer funId) {
        this.funId = funId;
    }

    public String getFunName() {
        return funName;
    }

    public void setFunName(String funName) {
        this.funName = funName == null ? null : funName.trim();
    }

    public Integer getParentFunId() {
        return parentFunId;
    }

    public void setParentFunId(Integer parentFunId) {
        this.parentFunId = parentFunId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}