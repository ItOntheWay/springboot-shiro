package com.leju.yunke.virtual.service.impl;

import com.leju.yunke.virtual.entity.Resources;
import com.leju.yunke.virtual.mapper.ResourcesMapper;
import com.leju.yunke.virtual.service.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jingpb
 * @version 1.0
 * @date 2021/1/23 22:35
 */
@Service
public class ResourcesServiceImpl implements ResourcesService {
    @Autowired
    private ResourcesMapper resourcesMapper;


    @Override
    public List<Resources> listAll() {
        return resourcesMapper.listAll();
    }

    @Override
    public List<Resources> listByRoleId(Integer roleId) {
        return resourcesMapper.listByRoleId(roleId);
    }

    @Override
    public List<Resources> getMenusByRoleId(Integer roleId) {
        Map map = new HashMap();
        map.put("roleId",roleId);
        map.put("type","menu");
        return resourcesMapper.getMenusByCondition(map);
    }
}
