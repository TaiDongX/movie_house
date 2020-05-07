package com.ws.service;

import com.ws.bean.Admin;

import java.util.List;


/**
 * @author 王朔
 * Description:
 * @date: 2020.04.09 8:47
 * @since JDK 1.8
 */
public interface AdminService {
    Admin getAdminByLoginNameAndPassword(String loginName,String pwd);

    Admin getAdminById(Integer opAdmin);

    List<Admin> getAllAdmins();

    void addAdmin(Admin admin);

    Boolean isExisted(String name);

    void updateAdmin(Admin admin);
}
