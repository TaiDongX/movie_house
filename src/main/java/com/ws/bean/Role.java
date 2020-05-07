package com.ws.bean;

import java.io.Serializable;
import java.util.List;

public class Role implements Serializable {
    private Integer roleId;

    private String roleName;

    private Integer parentRoleId;

    private String description;

    private Integer status;

    private List<Fun> funList;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Integer getParentRoleId() {
        return parentRoleId;
    }

    public void setParentRoleId(Integer parentRoleId) {
        this.parentRoleId = parentRoleId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Fun> getFunList() {
        return funList;
    }

    public void setFunList(List<Fun> funList) {
        this.funList = funList;
    }
}