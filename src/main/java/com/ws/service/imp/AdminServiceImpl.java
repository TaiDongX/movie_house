package com.ws.service.imp;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.ws.bean.Admin;
import com.ws.bean.AdminExample;
import com.ws.mapper.AdminMapper;
import com.ws.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 王朔
 * Description:
 * @date: 2020.04.09 8:47
 * @since JDK 1.8
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;
    @Autowired
    AdminExample adminExample;

    /**
     * 根据登录名密码获取管理员信息
     * @param loginName
     * @param pwd
     * @return
     */
    @Override
    public Admin getAdminByLoginNameAndPassword(String loginName, String pwd) {

        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andLoginNameEqualTo(loginName);
        criteria.andPasswordEqualTo(pwd);
        criteria.andStatusEqualTo(1);
        List<Admin> list = adminMapper.selectByExample(adminExample);
        adminExample.clear();
        return list != null && list.size() != 0 ? list.get(0) : null;
    }

    @Override
    public Admin getAdminById(Integer opAdmin) {
        return adminMapper.selectByPrimaryKey(opAdmin);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminMapper.selectByExample(null);
    }

    /**
     * 添加管理员
     * @param admin
     */
    @Transactional
    @Override
    public void addAdmin(Admin admin) {
        adminMapper.insertSelective(admin);
    }

    @Override
    public Boolean isExisted(String name) {
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andLoginNameEqualTo(name);
        List<Admin> list =  adminMapper.selectByExample(adminExample);
        adminExample.clear();
        return list != null && list.size() != 0;
    }

    @Transactional
    @Override
    public void updateAdmin(Admin admin) {
        adminMapper.updateByPrimaryKeySelective(admin);
    }

}
