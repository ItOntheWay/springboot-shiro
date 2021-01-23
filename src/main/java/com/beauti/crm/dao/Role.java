package com.beauti.crm.dao;

import lombok.Data;

import java.util.List;

/**
 * @author jingpb
 * @version 1.0
 * @date 2020/12/29 15:23
 */
@Data
public class Role {
    private Integer id;
    private String name;
    private String code;
    private List<RoleMenu> permission;
}
