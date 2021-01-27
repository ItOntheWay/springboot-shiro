package com.leju.yunke.virtual.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.leju.yunke.virtual.cons.Const;
import com.leju.yunke.virtual.entity.ResourceNode;
import com.leju.yunke.virtual.entity.Role;
import com.leju.yunke.virtual.entity.RoleResources;
import com.leju.yunke.virtual.mapper.RoleMapper;
import com.leju.yunke.virtual.mapper.RoleResourcesMapper;
import com.leju.yunke.virtual.service.RoleService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author jingpb
 * @version 1.0
 * @date 2021/1/23 22:19
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleResourcesMapper roleResourcesMapper;
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

    @Override
    public List<ResourceNode> getAllResourceNode(Integer roleId) {
        List<ResourceNode> resources = getAuthorizedResourceNodeAsRole(roleId,1);
        List<ResourceNode> result = Lists.newArrayList();
        try {
            Map<Integer, ResourceNode> dtoMap = Maps.newHashMap();
            for (ResourceNode resource : resources) {
                dtoMap.put(resource.getId(), resource);
            }
            for (Map.Entry<Integer, ResourceNode> entry : dtoMap.entrySet()) {
                ResourceNode node = entry.getValue();
                if (node.getParentId() == null || node.getParentId() == 0) {
                    result.add(node);
                } else {
                    if (dtoMap.get(node.getParentId()) != null) {
                        dtoMap.get(node.getParentId()).addChild(node);
                        Collections.sort(dtoMap.get(node.getParentId()).getChildren(),new ResourceNode.ResourceNodeComparator());
                    }
                }
            }
            resources.clear();
            resources = null;
            dtoMap.clear();
            dtoMap = null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("获取授权资源列表失败：" + e.getMessage());
        }
        return result;
    }

    @Override
    @Transactional
    public boolean roleUpdate(Role role, String resourceIds) {
        //修改角色
        int num = roleMapper.updateByPrimaryKeySelective(role);
        //修改角色关联资源，删除旧资源，新增新资源
        roleResourcesMapper.delAllByRoleId(role.getId());
        List<RoleResources> list = new ArrayList<RoleResources>();
        String[] ressourcesIds = resourceIds.split(",");
        if(ressourcesIds.length>0){
            for(int i=0;i<ressourcesIds.length;i++){
                RoleResources roleResources = new RoleResources();
                roleResources.setRoleId(role.getId());
                roleResources.setResourcesId(Integer.valueOf(ressourcesIds[i]));
                list.add(roleResources);
            }
        }
        if(!CollectionUtils.isEmpty(list)){
            roleResourcesMapper.batchAdd(list);
        }
        return true;
    }

    public List<ResourceNode> getAuthorizedResourceNodeAsRole(Integer roleId,Integer type) {
        List<ResourceNode> resourceNodes = Lists.newArrayList();
        List<Map<String, Object>> list = Lists.newArrayList();
        if(type == 1){
            list = roleMapper.selectByRoleid(roleId);
        }else{
            list = roleMapper.select2ByRoleid(roleId);
        }
        for(Map<String, Object> m:list){
            ResourceNode node = new ResourceNode();
            node.setId(((Long) m.get("id")).intValue());
            node.setParentId(((Long)m.get("parent_id")).intValue());
//            node.setIcon((String)m.get("icon"));
            node.setName((String)m.get("name"));
            node.setType((String)m.get("type"));
            node.setUrl((String)m.get("url"));
            node.setDeleted( Integer.valueOf(m.get("deleted").toString()));
            node.setChecked(m.get("role_id") != null);
            node.setCreateTime((Date)m.get("create_time"));
            resourceNodes.add(node);
        }
        return resourceNodes;
    }
}
