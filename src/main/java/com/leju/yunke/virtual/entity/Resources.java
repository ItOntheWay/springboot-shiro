package com.leju.yunke.virtual.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author jingpb
 * @version 1.0
 * @date 2021/1/23 18:38
 */
@Data
public class Resources {
    private Integer id;
    private String name;
    private String type;
    private String url;
    private String permission;
    private Integer parentId;
    private Integer sort;
    private Integer external;
    private Integer available;
    private String icon;
    private Date createTime;
    private Date updateTime;

    private List<Resources> nodes;
}
