package com.leju.yunke.virtual.service;

import com.leju.yunke.virtual.entity.Resources;

import java.util.List;

/**
 * @author jingpb
 * @version 1.0
 * @date 2021/1/23 22:35
 */
public interface ResourcesService {
    List<Resources>  listAll();
    List<Resources>  listByRoleId(Integer roleId);

    /**
     * 根据用户角色获取左侧菜单
     * */
    List<Resources> getMenusByRoleId(Integer roleId);
}
