package com.beauti.crm.dao;

import lombok.Data;

/**
 * @author jingpb
 * @version 1.0
 * @date 2020/12/29 15:26
 *  角色菜单
 */
@Data
public class RoleMenu {
    private Integer roleId;
    private Integer menuId;
    private Menu menu;
}
