package com.leju.yunke.virtual.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leju.yunke.virtual.entity.User;
import com.leju.yunke.virtual.mapper.UserMapper;
import com.leju.yunke.virtual.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    @Override
    public PageInfo<User> queryUserList(Map map) {
        Integer pageNum = Integer.valueOf(map.get("pageNum").toString());
        Integer pageSize = Integer.valueOf(map.get("pageSize").toString());
        Integer offset = 0 ;
        if(pageNum <=0){
            offset = 0;
        }else{
            offset = (pageNum - 1) * pageSize;
        }
        map.put("offset",offset);
        PageHelper.startPage(pageNum,pageSize);
        List<User> list = userMapper.queryUserList(map);
        return new PageInfo<>(list);
    }

    @Override
    public boolean saveUser(User user) {
        return userMapper.insertSelective(user)>0;
    }

    @Override
    public int updateByPrimaryKey(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int deletedByPrimaryKey(Integer userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    @Transactional
    public boolean userBatchdel(String ids){
        String[] array = ids.split(",");
        Arrays.stream(array).forEach(i -> {
            Integer id = Integer.valueOf(i);
            int num = userMapper.deleteByPrimaryKey(id);
        });
        return true;
    }
}
