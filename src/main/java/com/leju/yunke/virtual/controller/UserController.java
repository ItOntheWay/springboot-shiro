package com.leju.yunke.virtual.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.leju.yunke.virtual.comm.ResultStatus;
import com.leju.yunke.virtual.entity.Resources;
import com.leju.yunke.virtual.entity.Role;
import com.leju.yunke.virtual.entity.User;
import com.leju.yunke.virtual.res.Result;
import com.leju.yunke.virtual.service.RoleService;
import com.leju.yunke.virtual.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @author jingpb
 * @version 1.0
 * @date 2021/1/25 16:25
 */
@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping(value = {"", "/users"})
    public String users(Model model) {
        List<Role> roleList = roleService.getAllUsedRoles();
        model.addAttribute("roles",roleList);
        return "user/user-list";
    }
    @RequestMapping("/user/list")
    @ResponseBody
    public Result userList(String username, String city, Integer role , Integer page, Integer limit){
        Result result = Result.ok();
        //用户组条件
//        String roleId = param.getString("city");
        Map map = new HashMap<>();
        if(!Objects.isNull(role)){
            map.put("roleId",role);
        }
        if(!StringUtils.isEmpty(username)){
            map.put("userName",username);
        }
        if(!StringUtils.isEmpty(city)){
            map.put("city",city);
        }
        if(StringUtils.isEmpty(page+"") || page<=0){
            page =1;
        }
        map.put("pageNum",page);
        if(StringUtils.isEmpty(limit+"") || limit<=0){
            limit =10;
        }
        map.put("pageSize",limit);
        PageInfo<User> users = userService.queryUserList(map);
        result.setData(users.getList());
        result.setCount((int)users.getTotal());
        return result;
    }

    @RequestMapping("/user/create")
    public String userCreate(Model model) {
        List<Role> roleList = roleService.getAllUsedRoles();
        model.addAttribute("roles",roleList);
        return "user/user-add";
    }

    @RequestMapping("/user/save")
    @ResponseBody
    public Result userSave(String username,String password,String email,Integer roleId) {
        Result result = Result.ok();
        User user = new User();
        user.setUserName(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setRoleId(roleId);
        boolean res = userService.saveUser(user);
        if(res){
            return result;
        }else{
            return new Result(ResultStatus.SAVE_FAIL);
        }
    }

    @RequestMapping("/user/delete")
    @ResponseBody
    public Result userDelete(Integer id) {
        Result result = Result.ok();
        boolean res = userService.deletedByPrimaryKey(id)>0;
        if(res){
            return result;
        }else{
            return new Result(ResultStatus.DELETE_FAIL);
        }
    }

    @RequestMapping("/user/edit")
    public String userEdit(Model model,Integer id) {
        User user = userService.getByPrimaryKey(id);
        List<Role> roleList = roleService.getAllUsedRoles();
        model.addAttribute("roles",roleList);
        model.addAttribute("user",user);
        return "user/user-edit";
    }

    @RequestMapping("/user/update")
    @ResponseBody
    public Result userUpdate(Integer id,String username,String password,String email,Integer roleId) {
        Result result = Result.ok();
        User user = new User();
        user.setId(id);
        user.setUserName(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setRoleId(roleId);
        boolean res = userService.updateByPrimaryKey(user)>0;
        if(res){
            return result;
        }else{
            return new Result(ResultStatus.SAVE_FAIL);
        }
    }

    @RequestMapping("/user/batchdel")
    @ResponseBody
    public Result userBatchdel(String ids) {
        if(StringUtils.isEmpty(ids)){
            return new Result(ResultStatus.DELETE_FAIL);
        }
        Result result = Result.ok();
        boolean res = userService.userBatchdel(ids);
        if(res){
            return result;
        }else{
            return new Result(ResultStatus.SAVE_FAIL);
        }
    }
}
