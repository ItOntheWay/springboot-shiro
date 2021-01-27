package com.leju.yunke.virtual.mapper;

import com.leju.yunke.virtual.entity.RoleResources;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author jingpb
 * @version 1.0
 * @date 2021/1/23 18:43
 */
@Mapper
public interface RoleResourcesMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(RoleResources record);

    int insertSelective(RoleResources record);

    RoleResources selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleResources record);

    int updateByPrimaryKey(RoleResources record);

    int delAllByRoleId(@Param("roleId") Integer roleId);
    int batchAdd(@Param("list") List<RoleResources> list);

}
