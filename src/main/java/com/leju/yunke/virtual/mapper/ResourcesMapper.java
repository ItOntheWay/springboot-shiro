package com.leju.yunke.virtual.mapper;

import com.leju.yunke.virtual.entity.Resources;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author jingpb
 * @version 1.0
 * @date 2021/1/23 18:43
 */
@Mapper
public interface ResourcesMapper {
    List<Resources> listAll();
    List<Resources> listByRoleId(@Param("roleId") Integer roleId);

    List<Resources> getMenusByCondition(Map map);

    List<Resources> getSubMenusByParentId(@Param("resourcesId") Integer resourcesId);
}
