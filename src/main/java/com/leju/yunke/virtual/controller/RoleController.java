package com.leju.yunke.virtual.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.leju.yunke.virtual.comm.ResultStatus;
import com.leju.yunke.virtual.cons.Const;
import com.leju.yunke.virtual.entity.ResourceNode;
import com.leju.yunke.virtual.entity.Role;
import com.leju.yunke.virtual.res.Result;
import com.leju.yunke.virtual.service.RoleService;
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
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author jingpb
 * @version 1.0
 * @date 2021/1/26 16:01
 * 角色CRUD操作,
 * 角色的删除做逻辑删除操作，防止角色关联资源出现垃圾数据
 */
@Controller
public class RoleController {
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @GetMapping(value = {"", "/roles"})
    public String roles(Model model) {
        model.addAttribute("allStatuss", Const.allStatuss);
        return "role/role-list";
    }
    @RequestMapping("/role/list")
    @ResponseBody
    public Result roleList(String name, String code, Integer status , Integer page, Integer limit){
        Result result = Result.ok();
        //用户组条件
        Map map = new HashMap<>();
        if(!StringUtils.isEmpty(name)){
            map.put("name",name);
        }
        if(!StringUtils.isEmpty(code)){
            map.put("code",code);
        }
        if(!Objects.isNull(status)){
            map.put("status",status);
        }
        if(StringUtils.isEmpty(page+"") || page<=0){
            page =1;
        }
        map.put("pageNum",page);
        if(StringUtils.isEmpty(limit+"") || limit<=0){
            limit =10;
        }
        map.put("pageSize",limit);
        PageInfo<Role> roles = roleService.queryList(map);
        result.setData(roles.getList());
        result.setCount((int)roles.getTotal());
        JSONObject args = new JSONObject().fluentPut("allStatuss",Const.allStatuss);
        result.setArgs(args);
        return result;
    }

    @RequestMapping("/role/create")
    public String roleCreate(Model model) {
        model.addAttribute("allStatuss", Const.allStatuss);
        return "role/role-add";
    }

    @RequestMapping("/role/save")
    @ResponseBody
    public Result roleSave(String name,String code,Integer status) {
        Result result = Result.ok();
        Role role = new Role();
        role.setName(name);
        role.setCode(code);
        role.setStatus(status);
        boolean res = roleService.saveRole(role);
        if(res){
            return result;
        }else{
            return new Result(ResultStatus.SAVE_FAIL);
        }
    }

    /**
     * 角色的删除只做逻辑删除，否则需要清空角色绑定资源
     * */
    @RequestMapping("/role/delete")
    @ResponseBody
    public Result roleDelete(Integer id) {
        Result result = Result.ok();
        boolean res = roleService.updateDeletedByPrimaryKey(id);
        if(res){
            return result;
        }else{
            return new Result(ResultStatus.DELETE_FAIL);
        }
    }

    @RequestMapping("/role/edit")
    public String roleEdit(Model model,Integer id) {
        //TODO 编辑资源
//        List<Role> roles = roleService.getAllUsedRoles();
        Role role = roleService.getByPrimaryKey(id);
        model.addAttribute("role",role);
//        model.addAttribute("roles",roles);
        model.addAttribute("allStatuss", Const.allStatuss);
        return "role/role-edit";
    }

    /**
     * 修改角色资源
     * */
    @RequestMapping("/role/update")
    @ResponseBody
    public Result roleUpdate(Integer id,String name,Integer status,String code,String resourceIds) {
        Result result = Result.ok();
        Role role = new Role();
        role.setId(id);
        role.setCode(code);
        role.setName(name);
        role.setStatus(status);
        boolean res = roleService.roleUpdate(role,resourceIds);
        if(res){
            return result;
        }else{
            return new Result(ResultStatus.SAVE_FAIL);
        }
    }

    /**
     * 角色管理权限树
     * */
    @RequestMapping("/role/getAllResourceNode")
    @ResponseBody
    public Result getAllResourceNode(Integer roleId) {
        Result result = Result.ok();
        List<ResourceNode> list = roleService.getAllResourceNode(roleId);
        result.setData(list);
        return result;
    }
}
