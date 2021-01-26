package com.leju.yunke.virtual.mapper;

import com.leju.yunke.virtual.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    List<User> pageData(Map map);
    int pageCount(Map map);
    List<User> queryUserList(Map map);

    int updateByPrimaryKeySelective(User record);
    int deleteByPrimaryKey(@Param("id")Integer id);
}
