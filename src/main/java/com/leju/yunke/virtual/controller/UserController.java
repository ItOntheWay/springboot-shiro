package com.leju.yunke.virtual.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.leju.yunke.virtual.entity.Resources;
import com.leju.yunke.virtual.entity.User;
import com.leju.yunke.virtual.res.Result;
import com.leju.yunke.virtual.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    @GetMapping(value = {"", "/users"})
    public String users(Model model) {
        /*String userName = param.getString("username");
        String city = param.getString("city");
        //用户组条件
//        String roleId = param.getString("city");
        Map map = new HashMap<>();
        if(!StringUtils.isEmpty(userName)){
            map.put("userName",userName);
        }
        if(!StringUtils.isEmpty(city)){
            map.put("city",city);
        }
        PageInfo<User> users = userService.queryUserList(map);
        model.addAttribute("users",users);*/
        return "user/user-list";
    }
    @RequestMapping("/user/list")
    @ResponseBody
    public Result userList(String username,String city,Integer pageNum,Integer pageSize){
        Result result = Result.ok();
        /*String userName = param.getString("username");
        String city = param.getString("city");
        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");*/
        //用户组条件
//        String roleId = param.getString("city");
        Map map = new HashMap<>();
        if(!StringUtils.isEmpty(username)){
            map.put("userName",username);
        }
        if(!StringUtils.isEmpty(city)){
            map.put("city",city);
        }
        if(Objects.isNull(pageNum)){
            pageNum =1;
            map.put("pageNum",pageNum);
        }
        if(Objects.isNull(pageSize)){
            pageSize =2;
            map.put("pageSize",pageSize);
        }
        PageInfo<User> users = userService.queryUserList(map);
        result.setData(users.getList());
        result.setCount((int)users.getTotal());
        logger.info("res->{}", JSON.toJSONString(result));
        return result;
    }
}
