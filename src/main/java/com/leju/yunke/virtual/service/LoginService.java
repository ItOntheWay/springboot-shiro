package com.leju.yunke.virtual.service;

import com.leju.yunke.virtual.entity.User;

/**
 * @author jingpb
 * @version 1.0
 * @date 2020/12/29 14:28
 */
public interface LoginService {

    User findByName(String userName);
}
