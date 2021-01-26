package com.leju.yunke.virtual.controller;

import com.alibaba.fastjson.JSONObject;
import com.leju.yunke.virtual.res.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jingpb
 * @version 1.0
 * @date 2021/1/23 22:05
 */
@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * 登录
     *
     * @param
     * @param
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public Result submitLogin(String username,String password) {
        /*String username = data.getString("username");
        String password = data.getString("password");*/
        /*boolean rememberMe = data.getBoolean("rememberMe");
        String kaptcha =data.getString("kaptcha");*/
        Result result = Result.ok();
//        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        try {
            // 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            // 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            // 所以这一步在调用login(token)方法时,它会走到xxRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            currentUser.login(token);
            return result;
        } catch (Exception e) {
            logger.error("登录失败，用户名[{}]", username, e);
            token.clear();
            return Result.error(e);
        }
    }
}
