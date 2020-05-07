package com.ws.service.imp;

import com.ws.bean.Fun;
import com.ws.bean.FunExample;
import com.ws.bean.Role;
import com.ws.bean.RoleExample;
import com.ws.mapper.FunMapper;
import com.ws.mapper.RoleMapper;
import com.ws.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 王朔
 * Description:
 * @date: 2020.04.09 8:49
 * @since JDK 1.8
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;
    @Autowired
    RoleExample roleExample;

    @Autowired
    FunMapper funMapper;
    @Autowired
    FunExample funExample;

    /**
     * 根据角色id获取角色及相应的权限
     * @param roleId
     * @return
     */
    @Override
    public Role getRoleByRoleId(Integer roleId) {

        Role role = roleMapper.selectByPrimaryKey(roleId);
        List<Fun> funList = funMapper.getFunListByRoleId(roleId);
        // 获取对应的权限及子权限
        for (Fun fun : funList) {
            FunExample.Criteria criteria = funExample.createCriteria();
            criteria.andParentFunIdEqualTo(fun.getFunId());
            List<Fun> subFuns =funMapper.selectByExample(funExample);
            fun.setSubFuns(subFuns);
            funExample.clear();
        }
        role.setFunList(funList);
        return role;
    }

    @Override
    public List<Role> findAll() {
        return roleMapper.selectByExample(null);
    }

}
