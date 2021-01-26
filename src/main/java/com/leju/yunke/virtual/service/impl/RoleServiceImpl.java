package com.leju.yunke.virtual.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public Role getByPrimaryKey(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> getAllUsedRoles() {
        Map map = new HashMap<>();
        map.put("deleted", Const.DB_DELETED_NO);
        map.put("status", Const.DB_STATUS_YES);
        return roleMapper.getAll(map);
    }

    @Override
    public PageInfo<Role> queryList(Map map) {
        Integer pageNum = Integer.valueOf(map.get("pageNum").toString());
        Integer pageSize = Integer.valueOf(map.get("pageSize").toString());
        Integer offset = 0 ;
        if(pageNum <=0){
            offset = 0;
        }else{
            offset = (pageNum - 1) * pageSize;
        }
        map.put("offset",offset);
        map.put("deleted", Const.DB_DELETED_NO);
        PageHelper.startPage(pageNum,pageSize);
        List<Role> list = roleMapper.queryList(map);
        return new PageInfo<>(list);
    }

    @Override
    public boolean saveRole(Role role) {

        return roleMapper.insertSelective(role)>0;
    }

    @Override
    public boolean deleteByPrimaryKey(Integer id) {
        return roleMapper.deleteByPrimaryKey(id)>0;
    }

    @Override
    public boolean updateByPrimaryKey(Role role) {
        return roleMapper.updateByPrimaryKeySelective(role)>0;
    }

    @Override
    public boolean updateDeletedByPrimaryKey(Integer id) {
        return roleMapper.updateDeletedByPrimaryKey(id)>0;
    }
}
