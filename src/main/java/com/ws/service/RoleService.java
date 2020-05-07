package com.ws.service;

import com.ws.bean.Role;

import java.util.List;

/**
 * @author 王朔
 * Description:
 * @date: 2020.04.09 8:49
 * @since JDK 1.8
 */
public interface RoleService {
    Role getRoleByRoleId(Integer roleId);

    List<Role> findAll();
}
