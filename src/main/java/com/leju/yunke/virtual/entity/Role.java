package com.leju.yunke.virtual.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author jingpb
 * @version 1.0
 * @date 2021/1/23 18:37
 */
@Data
public class Role {
    private Integer id;
    private String name;
    private String code;
    private Integer status;
    private Date createTime;
    private Date updateTime;
}
