package com.leju.yunke.virtual.controller;

import com.leju.yunke.virtual.entity.Resources;
import com.leju.yunke.virtual.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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
        return "user/user-list";
    }
}
