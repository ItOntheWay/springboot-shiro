package com.leju.yunke.virtual.service;


import com.github.pagehelper.PageInfo;
import com.leju.yunke.virtual.entity.User;

import java.util.Map;

/**
 * @author jingpb
 * @version 1.0
 * @date 2021/1/23 18:27
 */
public interface UserService {
    User getByUserName(String userName);
    User getByPrimaryKey(Integer userId);

    PageInfo<User> queryUserList(Map map);
    boolean saveUser(User user);
    int updateByPrimaryKey(User user);
    int deletedByPrimaryKey(Integer userId);
    boolean userBatchdel(String ids);
}
