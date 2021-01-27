package com.leju.yunke.virtual.entity;

import lombok.Data;

import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author jingpb
 * @version 1.0
 * @date 2021/1/27 14:55
 */
@Data
public class ResourceNode {
    private Integer id;
    private Integer parentId;
    private String name;
    private String type;
    private String url;
    private String permission;
    private Date createTime;
    private Integer deleted;
//    private String icon;
    private int sort = 1;
    private boolean checked = false;
    private List<ResourceNode> children = new LinkedList<ResourceNode>();
    public Object s1;

    public void addChild(ResourceNode node) {
        this.children.add(node);
    }

    public static class ResourceNodeComparator implements Comparator<ResourceNode> {
        @Override
        public int compare(ResourceNode node1, ResourceNode node2) {

            int sort1 = node1.getSort();
            int sort2 = node2.getSort();
            long createTime1 = node1.getCreateTime().getTime();
            long createTime2 = node2.getCreateTime().getTime();

            // 判断大小，相等则使用下一个属性对比
            if(sort1 > sort2){
                return 1;
            }else if(sort1 < sort2){
                return -1;
            }
            return createTime1 < createTime2 ? -1 : (createTime1 == createTime2 ? 0 : 1);
        }
    }
}
