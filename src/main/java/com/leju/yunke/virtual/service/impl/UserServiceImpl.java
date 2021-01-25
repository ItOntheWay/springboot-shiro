package com.leju.yunke.virtual.service.impl;

import com.leju.yunke.virtual.entity.User;
import com.leju.yunke.virtual.mapper.UserMapper;
import com.leju.yunke.virtual.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jingpb
 * @version 1.0
 * @date 2021/1/23 18:27
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getByUserName(String userName) {
        return userMapper.getByUserName(userName);
    }

    @Override
    public User getByPrimaryKey(Integer userId) {
        return userMapper.getByPrimaryKey(userId);
    }
}
