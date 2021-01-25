package com.leju.yunke.virtual.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author jingpb
 * @version 1.0
 * @date 2021/1/23 18:40
 */
@Data
public class RoleResources {
    private Integer id;
    private Integer roleId;
    private Integer resourcesId;
    private Date createTime;
    private Date updateTime;
}
