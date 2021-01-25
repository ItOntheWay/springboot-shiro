package com.leju.yunke.virtual.service;

import com.leju.yunke.virtual.entity.Role;

/**
 * @author jingpb
 * @version 1.0
 * @date 2021/1/23 22:17
 */
public interface RoleService {
    Role getRolesByUserId(Integer userId);
}
