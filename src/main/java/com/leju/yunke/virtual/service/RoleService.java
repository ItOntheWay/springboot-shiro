package com.leju.yunke.virtual.service;

import com.leju.yunke.virtual.entity.Role;

import java.util.List;

/**
 * @author jingpb
 * @version 1.0
 * @date 2021/1/23 22:17
 */
public interface RoleService {
    Role getRolesByUserId(Integer userId);

    /**
     * 查询可用状态所有角色合集
     * */
    List<Role> getAllUsedRoles();
}
