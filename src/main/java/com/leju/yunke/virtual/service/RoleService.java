package com.leju.yunke.virtual.service;

import com.github.pagehelper.PageInfo;
import com.leju.yunke.virtual.entity.ResourceNode;
import com.leju.yunke.virtual.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * @author jingpb
 * @version 1.0
 * @date 2021/1/23 22:17
 */
public interface RoleService {
    /**
     * 根据用id获取角色
     * */
    Role getRolesByUserId(Integer userId);

    /**
     * 根据主键查询
     * */
    Role getByPrimaryKey(Integer id);
    /**
     * 查询可用状态所有角色合集
     * */
    List<Role> getAllUsedRoles();
    /**
     * 分页查询
     * */
    PageInfo<Role> queryList(Map map);

    boolean saveRole(Role role);

    boolean deleteByPrimaryKey(Integer id);

    boolean updateByPrimaryKey(Role role);

    /**
     * 逻辑删除
     * */
    boolean updateDeletedByPrimaryKey(Integer id);

    /**
     * 角色管理资源树
     * */
    List<ResourceNode> getAllResourceNode(Integer roleId);

    /**
     * 角色管理 角色资源关联关系
     * */

    boolean roleUpdate(Role role,String resourceIds);

}
