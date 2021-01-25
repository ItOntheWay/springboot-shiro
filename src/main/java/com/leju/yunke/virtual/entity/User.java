package com.leju.yunke.virtual.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private String id;
    private String userName;
    private String password;
    private Integer roleId;

    private Date createTime;

    private Date updateTime;

    private Integer deleted;

    private String email;

}