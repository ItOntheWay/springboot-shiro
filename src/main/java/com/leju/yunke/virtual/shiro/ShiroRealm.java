package com.leju.yunke.virtual.shiro;

import com.alibaba.fastjson.JSON;
import com.leju.yunke.virtual.cons.Const;
import com.leju.yunke.virtual.entity.Resources;
import com.leju.yunke.virtual.entity.Role;
import com.leju.yunke.virtual.entity.User;
import com.leju.yunke.virtual.enums.RoleCodeEnum;
import com.leju.yunke.virtual.service.ResourcesService;
import com.leju.yunke.virtual.service.RoleService;
import com.leju.yunke.virtual.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author jingpb
 * @version 1.0
 * @date 2021/1/23 16:55
 *   自定义域
 */
public class ShiroRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ResourcesService resourcesService;

    /**
     * 提供账户信息返回认证信息（用户的角色信息集合）
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取用户名
        String userName = (String) authenticationToken.getPrincipal();
        User user = userService.getByUserName(userName);
        if (Objects.isNull(user)) {
            throw new UnknownAccountException("账号不存在！");
        }

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                user.getId(),
                user.getPassword(),
                ByteSource.Util.bytes(userName),
                getName()
        );
        return simpleAuthenticationInfo;

    }

    /**
     * 权限认证，为当前登录的Subject授予角色和权限（角色的权限信息集合）
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Integer userId = (Integer) SecurityUtils.getSubject().getPrincipal();
        // 赋予角色
        Role role = roleService.getRolesByUserId(userId);
        info.addRole(role.getCode());
        // 赋予权限
        List<Resources> resourcesList = null;
        // ROOT用户默认拥有所有权限
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute(Const.USER_SESSION_KEY);
        logger.info("user-->{}", JSON.toJSONString(user));
        if (RoleCodeEnum.ROOT.toString().equalsIgnoreCase(role.getCode())) {
            resourcesList = resourcesService.listAll();
        } else {
            resourcesList = resourcesService.listByRoleId(role.getId());
        }

        if (!CollectionUtils.isEmpty(resourcesList)) {
            Set<String> permissionSet = new HashSet<>();
            for (Resources resources : resourcesList) {
                String permission = null;
                if (!StringUtils.isEmpty(permission = resources.getPermission())) {
                    permissionSet.addAll(Arrays.asList(permission.trim().split(",")));
                }
            }
            info.setStringPermissions(permissionSet);
        }
        logger.info("用户授权-->{}", JSON.toJSONString(info));
        return info;
    }
}
