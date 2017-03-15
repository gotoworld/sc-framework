/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.wu1g.web.controller;

import com.wu1g.api.auth.IRoleSourceService;
import com.wu1g.framework.util.IpUtils;
import com.wu1g.framework.util.ReflectUtil;
import com.wu1g.vo.auth.AuthPerm;
import com.wu1g.vo.auth.AuthRole;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.vo.org.OrgUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ShiroRealm extends AuthorizingRealm {
    public ShiroRealm() {}
    public ShiroRealm(CacheManager cacheManager, CredentialsMatcher matcher) {
        super(cacheManager, matcher);
    }
    /**
     * 角色资源 业务处理。
     */
    @Autowired
    private IRoleSourceService roleSourceService;

    /**
     * 这里编写授权代码
     * 认证回调函数,登录时调用. 是判断用户是否有权限执行某操作
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info(" 由于加入了缓存, 此处只会load一次：doGetAuthorizationInfo.................");
//		得到 doGetAuthenticationInfo 方法中传入的凭证
        String username = (String) principals.fromRealm(getName()).iterator().next();
//		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();  
        if (username == null) {
            return null;
        }
        //1.根据用户登录名获取用户信息
//		OrgUser orgUserBean=roleSourceService.findUserByLoginName(username);
        OrgUser orgUserBean = (OrgUser) SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.SESSION_KEY_USER);
        if (orgUserBean == null) {
            return null;
        }
//		//存放用户信息  信息传递.
//		Subject subject = SecurityUtils.getSubject();
//		subject.getSession().setAttribute(CommonConstant.SESSION_KEY_USER,orgUserBean);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Map dto = new HashMap();
        dto.put("uid", orgUserBean.getId());
        if (roleSourceService.isSuperAdmin(dto) > 0) {
            //超级管理员标记
            dto.put("iissuperman", 1);
            SecurityUtils.getSubject().getSession().setAttribute("isSuper", "1");
        }
        //2.获取角色集合
        List<AuthRole> roleList = roleSourceService.getRoleListByUId(dto);
        if (roleList != null) {
            for (AuthRole role : roleList) {
                info.addRole(role.getName());
            }
        }
        //3.获取功能集合
        List<AuthPerm> permList = roleSourceService.getPermListByUId(dto);
        if (permList != null) {
            for (AuthPerm perm : permList) {
                if (perm.getMatchStr() != null && !"".equals(perm.getMatchStr())) {
                    info.addStringPermission(perm.getMatchStr());
                }
            }
        }
        //4.返回SimpleAuthorizationInfo对象
        return info;

//		if (StringUtils.equals("admin", username)) {
//			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//			// 这个确定页面中<shiro:hasRole>标签的name的值
//			info.addRole("admin");
//			// 这个就是页面中 <shiro:hasPermission> 标签的name的值
//			info.addStringPermission("user:add");  
//            info.addStringPermission("user:edit");  
//            info.addStringPermission("user:update");  
//            info.addStringPermission("user:delete");  
//            info.addStringPermission("user:query");  
//
//			return info;
//		} else if (StringUtils.equals("test", username)) {
//			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//
//			// 这个确定页面中<shiro:hasRole>标签的name的值
//			info.addRole("test");
//			// 这个就是页面中 <shiro:hasPermission> 标签的name的值, 这个串 随便的,不过我还是认为 白衣的好。
//			info.addStringPermission("user:query");  
//
//			return info;
//		} else {
//			return null;
//		}
    }

    /**
     * 这里编写认证代码
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken anthToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) anthToken;

        String username = token.getUsername();

        log.info("====================doGetAuthenticationInfo begin ==========================");
        log.info("username: " + username);
//		log.info("password: "+token.getPassword());
        log.info("principal: " + token.getPrincipal());
        log.info("======================doGetAuthenticationInfo end ========================");
//		if (StringUtils.equals("admin", username)) {
//			return new SimpleAuthenticationInfo("admin", "e10adc3949ba59abbe56e057f20f883e", getName());
//		}
        //几种用法 1.缓存所有用户信息 2.使用数据库
        //2.获取根据用户登录名获取用户信息
        OrgUser orgUserBean = roleSourceService.findUserByLoginName(username);
        if (orgUserBean != null) {
            //缓存用户信息 减少一步查询数据库
            SecurityUtils.getSubject().getSession().setAttribute(CommonConstant.SESSION_KEY_USER, orgUserBean);
            //创建AuthenticationInfo对象并返回
            return new SimpleAuthenticationInfo(username, orgUserBean.getPwd(), getName());
        }
        return null;
    }
}
