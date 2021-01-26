package com.leju.yunke.virtual.mapper;


import com.leju.yunke.virtual.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author jingpb
 * @version 1.0
 * @date 2021/1/23 18:42
 */
@Mapper
public interface RoleMapper {
    int insert(Role record);

    int insertSelective(Role record);

    Role getRolesByUserId(@Param("userId") Integer userId);

    List<Role> getAll(Map map);
}
