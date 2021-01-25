package com.leju.yunke.virtual.service.impl;

import com.leju.yunke.virtual.entity.User;
import com.leju.yunke.virtual.service.LoginService;
import org.springframework.stereotype.Service;

/**
 * @author jingpb
 * @version 1.0
 * @date 2020/12/29 14:29
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public User findByName(String userName) {
        return null;
    }
}
