package com.leju.yunke.virtual.cons;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author jingpb
 * @version 1.0
 * @date 2021/1/23 21:54
 */
public class Const {
    public static final String USER_SESSION_KEY = "user";

    //db数据记录是否被删除
    public static final Integer DB_DELETED_YES = 1; //是
    public static final Integer DB_DELETED_NO = 0;//否

    //db数据记录是否可用
    public static final Integer DB_STATUS_NO = 0;//不可用
    public static final Integer DB_STATUS_YES = 1;//可用

    public static Map<Integer, String> allStatuss = Maps.newLinkedHashMap();
    static {
        allStatuss.put(0, "停用");
        allStatuss.put(1, "正常");
    }

    public static Map<String, String> allResourcesType = Maps.newLinkedHashMap();
    static {
        allResourcesType.put("menu", "菜单");
        allResourcesType.put("button", "按钮");
    }

}
