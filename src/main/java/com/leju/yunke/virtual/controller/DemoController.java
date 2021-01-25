package com.leju.yunke.virtual.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

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
