package com.leju.yunke.virtual.controller;

import com.leju.yunke.virtual.cons.Const;
import com.leju.yunke.virtual.entity.Resources;
import com.leju.yunke.virtual.entity.User;
import com.leju.yunke.virtual.service.ResourcesService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author jingpb
 * @version 1.0
 * @date 2021/1/25 10:29
 * 页面跳转控制类
 */
@Controller
public class RenderController {

    @Autowired
    private ResourcesService resourcesService;

    @GetMapping(value = {"", "/login"})
    public String login() {
        return "login";
    }

    @RequiresAuthentication
    @GetMapping(value = {"", "/index"})
    public String index(Model model) {
//        model.addAttribute("data1","参数1");
        //根据权限获取登陆用户左侧菜单栏
//        User user = (User) SecurityUtils.getSubject().getSession().getAttribute(Const.USER_SESSION_KEY);
//        List<Resources> menus =  resourcesService.getMenusByRoleId(user.getRoleId());
        List<Resources> menus =  resourcesService.getMenusByRoleId(1);
        model.addAttribute("menus",menus);
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute(Const.USER_SESSION_KEY);
        model.addAttribute("user",user);
        return "index";
    }

    @GetMapping(value = {"", "/console"})
    public String console() {

        return "console";
    }
}
