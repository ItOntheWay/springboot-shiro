package com.beauti.crm.dao;

import lombok.Data;

import java.util.Date;

/**
 * @author jingpb
 * @version 1.0
 * @date 2020/12/29 14:32
 */
@Data
public class User {
    private Integer id;
    private String userName;
    private String password;
    private Integer roleId;
    private Date createTime;
    private Date updateTime;
    private Integer deleted;
    private Role role;
}
