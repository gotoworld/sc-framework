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
package com.wu1g.framework.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import cn.com.baseos.bean.auth.AuthPerm;
import cn.com.baseos.bean.auth.AuthRole;
import cn.com.baseos.bean.org.OrgUser;
import cn.com.baseos.common.CommonConstant;
import cn.com.baseos.service.auth.IRoleSourceService;

public class MyShiroRealm extends AuthorizingRealm {
	private static final transient Logger log = Logger.getLogger("shiro");
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
		String username =(String) principals.fromRealm(getName()).iterator().next();
//		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();  
		if(username==null){
			return null;
		}
		//1.根据用户登录名获取用户信息
//		OrgUser userBean=roleSourceService.findUserByLoginName(username);
		OrgUser userBean=(OrgUser) SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.SESSION_KEY_USER);
		if(userBean==null){
			return null;
		}
//		//存放用户信息  信息传递.
//		Subject subject = SecurityUtils.getSubject();
//		subject.getSession().setAttribute(CommonConstant.SESSION_KEY_USER,userBean);
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		Map dto=new HashMap();
		dto.put("uid",userBean.getId());
		if(roleSourceService.isSuperAdmin(dto)>0){
			//超级管理员标记
			dto.put("iissuperman",1);
			SecurityUtils.getSubject().getSession().setAttribute("isSuper","1");
		}
		//2.获取角色集合
		List<AuthRole> roleList=roleSourceService.getRoleListByUId(dto);
		if(roleList!=null){
			for(AuthRole role:roleList){
				info.addRole(role.getName());
			}
		}
		//3.获取功能集合
		List<AuthPerm> permList=roleSourceService.getPermListByUId(dto);
		if(permList!=null){
			for(AuthPerm perm:permList){
				if(perm.getMatchStr()!=null && !"".equals(perm.getMatchStr())){
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
		OrgUser userBean=roleSourceService.findUserByLoginName(username);
		if(userBean!=null){
			//缓存用户信息 减少一步查询数据库
			SecurityUtils.getSubject().getSession().setAttribute(CommonConstant.SESSION_KEY_USER,userBean);
			//创建AuthenticationInfo对象并返回  
			return new SimpleAuthenticationInfo(username,userBean.getPwd(), getName());
		}
		return null;
	}
}
