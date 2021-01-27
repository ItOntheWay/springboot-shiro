package com.leju.yunke.virtual.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leju.yunke.virtual.cons.Const;
import com.leju.yunke.virtual.entity.Resources;
import com.leju.yunke.virtual.mapper.ResourcesMapper;
import com.leju.yunke.virtual.service.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
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
//        map.put("deleted",0);
        return resourcesMapper.getMenusByCondition(map);
    }

    @Override
    public PageInfo<Resources> queryList(Map map) {
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
        List<Resources> list = resourcesMapper.getAll(map);
        return new PageInfo<>(list);
    }

    @Override
    public boolean save(Resources resources) {
        return resourcesMapper.insertSelective(resources)>0;
    }

    @Override
    public boolean updateDeletedByPrimaryKey(Integer id) {
        return resourcesMapper.updateDeletedByPrimaryKey(id)>0;
    }

    @Override
    public Resources getByPrimaryKey(Integer id) {
        return resourcesMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateByPrimaryKey(Resources resources) {
        return resourcesMapper.updateByPrimaryKeySelective(resources)>0;
    }

    @Override
    @Transactional
    public boolean resourcesBatchdel(String ids) {
        String[] array = ids.split(",");
        Arrays.stream(array).forEach(i -> {
            Integer id = Integer.valueOf(i);
            int num = resourcesMapper.updateDeletedByPrimaryKey(id);
        });
        return true;
    }
}
