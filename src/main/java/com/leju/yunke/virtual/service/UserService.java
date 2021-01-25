package com.leju.yunke.virtual.service;


import com.leju.yunke.virtual.entity.User;

/**
 * @author jingpb
 * @version 1.0
 * @date 2021/1/23 18:27
 */
public interface UserService {
    User getByUserName(String userName);
    User getByPrimaryKey(Integer userId);
}
