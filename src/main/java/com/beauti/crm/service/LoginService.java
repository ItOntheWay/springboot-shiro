package com.beauti.crm.service;

import com.beauti.crm.dao.User;

/**
 * @author jingpb
 * @version 1.0
 * @date 2020/12/29 14:28
 */
public interface LoginService {

    User findByName(String userName);
}
