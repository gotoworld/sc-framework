/*
 *  用户免登陆
 *
 * VERSION  DATE        BY              REASON
 * -------- ----------- --------------- ------------------------------------
 * 1.00     2016年9月25日  wu1g119      程序.发布
 * -------- ----------- --------------- ------------------------------------
 * Copyright 2016 baseos  System. - All Rights Reserved.
 *
 */
package com.wu1g.framework.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.com.baseos.common.CommonConstant;
import cn.com.baseos.filter.ExceptionAndExecuteTimeFilter;

/**
 *<p>用户免登陆
 */
@ControllerAdvice
public class UserRememberInterceptor  implements HandlerInterceptor{
	private static final Log	log	= LogFactory.getLog( UserRememberInterceptor.class );
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.debug( "==>UserRememberInterceptor>>preHandle" );
		if (SecurityUtils.getSubject().getSession().getAttribute( CommonConstant.SESSION_KEY_USER )==null) {
			log.info( "用户登录信息获取失败,尝试获取免登陆信息登录!" );
	    	 Cookie[] cookies = request.getCookies();
	    	 String[] cooks = null;  
	         for (Cookie coo : cookies) {
	             String aa = coo.getValue();  
	             cooks = aa.split("=remember=");  
	             if (cooks.length == 2) {
	            	try {
		                UsernamePasswordToken token = new UsernamePasswordToken( cooks[0], cooks[1] );
		                SecurityUtils.getSubject().login( token );
		                return true;
	        		} catch (Exception ex) {
	        			log.error( "登录失败,原因未知", ex );
	        			request.setAttribute( "msg", "登录失败,"+ex.getMessage() );
	        			response.sendRedirect("/admin/login");
	        			return false;
	        		}
	             }  
	         }  
	     }
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		
	}

}


