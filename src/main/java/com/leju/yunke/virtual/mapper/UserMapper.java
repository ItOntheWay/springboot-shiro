package com.leju.yunke.virtual.mapper;

import com.leju.yunke.virtual.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author jingpb
 * @version 1.0
 * @date 2021/1/23 17:49
 */
@Mapper
public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);

    User getByUserName(@Param("userName") String userName);

    User getByPrimaryKey(@Param("id") Integer id);
}
