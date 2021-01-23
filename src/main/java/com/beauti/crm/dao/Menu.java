package com.beauti.crm.dao;

import lombok.Data;

import java.util.Date;

/**
 * @author jingpb
 * @version 1.0
 * @date 2020/12/29 15:29
 */
@Data
public class Menu {
    private Integer id;
    private String name;
    private String url;
    private Integer parentId;
    private Date createTime;
    private Date updateTime;
}
