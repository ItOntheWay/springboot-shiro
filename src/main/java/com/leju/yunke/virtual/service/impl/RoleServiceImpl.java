package com.leju.yunke.virtual.service.impl;

import com.leju.yunke.virtual.cons.Const;
import com.leju.yunke.virtual.entity.Role;
import com.leju.yunke.virtual.mapper.RoleMapper;
import com.leju.yunke.virtual.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jingpb
 * @version 1.0
 * @date 2021/1/23 22:19
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public Role getRolesByUserId(Integer userId) {

        return roleMapper.getRolesByUserId(userId);
    }

    @Override
    public List<Role> getAllUsedRoles() {
        Map map = new HashMap<>();
        map.put("deleted", Const.DB_DELETED_NO);
        map.put("status", Const.DB_STATUS_YES);
        return roleMapper.getAll(map);
    }
}
