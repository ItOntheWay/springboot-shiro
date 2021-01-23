package com.beauti.crm.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jingpb
 * @version 1.0
 * @date 2020/12/23 16:37
 */
@Controller
//@RequestMapping(path = "/api/v1")
public class DemoController {
    private static final Logger log = LoggerFactory.getLogger(DemoController.class);

    /**
     * 示例 localhost:8080 跳转templates下index.html
     * */
    /*@RequestMapping("/index")
    public String index(){
        return "/index";
    }*/
    /**
     * 示例 localhost:8080 跳转templates下login.html
     * */
    /*@RequestMapping("/")
    public String login(){
        return "/login";
    }*/
}
