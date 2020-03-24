package com.ws.service.imp;

import com.ws.bean.User;
import com.ws.bean.UserExample;
import com.ws.mapper.MovieMapper;
import com.ws.mapper.UserMapper;
import com.ws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 王朔
 * Description:
 * @date: 2020.03.20 16:29
 * @since JDK 1.8
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserExample userExample;
    @Override
    public User findUserByLoginName(String loginName) {

        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andLoginNameEqualTo(loginName);
        List<User> user = userMapper.selectByExample(userExample);
        userExample.clear();

        return user.size() != 0 ? user.get(0) : null;


    }

    @Transactional
    @Override
    public void insertUser(User user) throws Exception{
        userMapper.insertSelective(user);
    }

    @Override
    public void updateUser(User user){
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andLoginNameEqualTo(user.getLoginName());
        userMapper.updateByExample(user,userExample);
        userExample.clear();
    }
}
