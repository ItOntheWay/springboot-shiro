package com.leju.yunke.virtual.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.leju.yunke.virtual.comm.ResultStatus;
import com.leju.yunke.virtual.cons.Const;
import com.leju.yunke.virtual.entity.Resources;
import com.leju.yunke.virtual.res.Result;
import com.leju.yunke.virtual.service.ResourcesService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jingpb
 * @version 1.0
 * @date 2021/1/27 10:00
 * 资源crud
 */
@Controller
public class ResourcesController {
    private static final Logger logger = LoggerFactory.getLogger(ResourcesController.class);

    @Autowired
    private ResourcesService resourcesService;

    @GetMapping(value = {"", "/resources"})
    public String roles(Model model) {
        model.addAttribute("allResourcesType", Const.allResourcesType);
        return "resources/resources-list";
    }
    @RequestMapping("/resources/list")
    @ResponseBody
    public Result resourcesList(String name, String type, Integer page, Integer limit){
        Result result = Result.ok();
        //用户组条件
        Map map = new HashMap<>();
        if(!StringUtils.isEmpty(name)){
            map.put("name",name);
        }
        if(!StringUtils.isEmpty(type)){
            map.put("type",type);
        }
        if(StringUtils.isEmpty(page+"") || page<=0){
            page =1;
        }
        map.put("pageNum",page);
        if(StringUtils.isEmpty(limit+"") || limit<=0 ){
            limit =10;
        }
        map.put("pageSize",limit);
        PageInfo<Resources> resources = resourcesService.queryList(map);
        result.setData(resources.getList());
        result.setCount((int)resources.getTotal());
        JSONObject args = new JSONObject().fluentPut("allStatuss",Const.allStatuss)
                .fluentPut("allResourcesType", Const.allResourcesType);
        result.setArgs(args);
        return result;
    }

    @RequestMapping("/resources/create")
    public String resourcesCreate(Model model) {
        model.addAttribute("allResourcesType", Const.allResourcesType);
        //TODO 父资源
        /*User user = (User) SecurityUtils.getSubject().getSession().getAttribute(Const.USER_SESSION_KEY);
        List<Resources> parentResources=null;
        if(Objects.isNull(user)){
            parentResources = resourcesService.getMenusByRoleId(1);
        }else {
            parentResources = resourcesService.getMenusByRoleId(user.getRoleId());
        }
        model.addAttribute("parentResources", parentResources);*/
        return "resources/resources-add";
    }

    @RequestMapping("/resources/save")
    @ResponseBody
    public Result resourcesSave(String name,String type,String url,String permission,Integer parentId,Integer sort) {
        Result result = Result.ok();
        Resources resources = new Resources();
        resources.setName(name);
        resources.setType(type);
        resources.setUrl(url);
        resources.setPermission(permission);
        resources.setParentId(parentId);
        resources.setSort(sort);
        boolean res = resourcesService.save(resources);
        if(res){
            return result;
        }else{
            return new Result(ResultStatus.SAVE_FAIL);
        }
    }

    /**
     * 资源的删除只做逻辑删除，否则需要清空角色关联资源关系
     * */
    @RequestMapping("/resources/delete")
    @ResponseBody
    public Result resourcesDelete(Integer id) {
        Result result = Result.ok();
        boolean res = resourcesService.updateDeletedByPrimaryKey(id);
        if(res){
            return result;
        }else{
            return new Result(ResultStatus.DELETE_FAIL);
        }
    }

    @RequestMapping("/resources/edit")
    public String resourcesEdit(Model model,Integer id) {
        Resources resources = resourcesService.getByPrimaryKey(id);
        model.addAttribute("resources",resources);
        model.addAttribute("allResourcesType", Const.allResourcesType);
        return "resources/resources-edit";
    }

    @RequestMapping("/resources/update")
    @ResponseBody
    public Result resourcesUpdate(Integer id,String name,String type,String url,String permission,Integer parentId,Integer sort) {
        Result result = Result.ok();
        Resources resources = new Resources();
        resources.setId(id);
        resources.setName(name);
        resources.setType(type);
        resources.setUrl(url);
        resources.setPermission(permission);
        resources.setParentId(parentId);
        resources.setSort(sort);
        boolean res = resourcesService.updateByPrimaryKey(resources);
        if(res){
            return result;
        }else{
            return new Result(ResultStatus.SAVE_FAIL);
        }
    }

    @RequestMapping("/resources/batchdel")
    @ResponseBody
    public Result resourcesBatchdel(String ids) {
        //TODO 资源批量删除时候，清空角色关联资源
        if(StringUtils.isEmpty(ids)){
            return new Result(ResultStatus.DELETE_FAIL);
        }
        Result result = Result.ok();
        boolean res = resourcesService.resourcesBatchdel(ids);
        if(res){
            return result;
        }else{
            return new Result(ResultStatus.SAVE_FAIL);
        }
    }
}
