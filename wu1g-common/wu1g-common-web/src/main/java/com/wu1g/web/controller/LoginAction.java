/*
 * 后台登陆总入口
 *
 * VERSION  DATE        BY              REASON
 * -------- ----------- --------------- ------------------------------------------
 * 1.00     2015.01.13  wuxiaogang      程序・发布
 * -------- ----------- --------------- ------------------------------------------
 * Copyright 2015 baseos System. - All Rights Reserved.
 *
 */
package com.wu1g.web.controller;

import com.wu1g.framework.util.CommonConstant;
import com.wu1g.framework.util.ValidatorUtil;
import com.wu1g.framework.web.controller.BaseController;
import com.wu1g.framework.web.controller.SessionUtil;
import com.wu1g.org.vo.OrgUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import java.util.Locale;

/**
 * <p>
 * 台登陆总入口 action
 * <ol>
 * [提供机能]
 * <li>登录
 * <li>登出
 */
@Controller
@RequestMapping(value = "/admin")
@Slf4j
public class LoginAction extends BaseController {
	private static final long				serialVersionUID	= -6103432072290645133L;

	/** 默认的构造函数 */
	public LoginAction() {
		log.info( "LoginAction constructed" );
	}

	/**
	 * <p>
	 * init
	 * </p>
	 * <ol>
	 * [功能概要] <li>init。
	 * </ol>
	 * 
	 * @return 转发字符串
	 */
	@RequestMapping(value = "/init")
	public String init() throws Exception {
		log.info( "HomeAction init" );

		return "admin/login";
	}

	 /**
	 * <p>
	 * 无权限页面
	 * </p>
	 * <ol>
	 * [功能概要] <li>无权限页面。
	 * </ol>
	 *
	 * @return 转发字符串
	 */
	 @RequestMapping(value="/noauth")
	 public String noauth() throws Exception {
	 log.info("HomeAction noauth");
	
	 return "error/noauth";
	 }

	/**
	 * <p>
	 * 用户登录
	 * </p>
	 * <ol>
	 * [功能概要] <li>登陆。
	 * </ol>
	 * 
	 * @return 转发字符串
	 */
	@RequestMapping(value = "/login")
	public String login() throws Exception {
		log.info( "HomeAction login" );

		String userid = request.getParameter( "userid" );
		String password = request.getParameter( "password" );

		if (ValidatorUtil.isNullEmpty( userid ) || ValidatorUtil.isNullEmpty( password )) {
			request.setAttribute( "msg", "用户名或密码不能为空!" );
			return "admin/login";
		}

		// if (!VerifyUtil.checkVeifyCode( request, "authCode" )) {
		// request.setAttribute( "msg", "验证码校验失败!" );
		// return "admin/login";
		// }
		UsernamePasswordToken token = new UsernamePasswordToken( userid, password );

		try {
			getAuth().login( token );

			String remember=request.getParameter( "remember" );
			if("1".equals( remember )){
	            Cookie cookie = new Cookie("user", userid+"=remember="+password);  
                cookie.setMaxAge(7*24*60*60);//7天免登陆          
                response.addCookie(cookie);  
			}
			OrgUser user = (OrgUser) getAuth().getSession().getAttribute( CommonConstant.SESSION_KEY_USER );
			// System.out.println(user);
			session.setAttribute( CommonConstant.SESSION_KEY_USER, user );
			session.setAttribute( "_language", "zh_CN" );

			return "redirect:/h/index";
		} catch (UnknownAccountException ex) {
			// username provided was not found
			request.setAttribute( "msg", "登录失败,用户名或密码错误!" );
		} catch (IncorrectCredentialsException ex) {
			// for the username provided
			request.setAttribute( "msg", "登录失败,用户名或密码错误!" );
		} catch (Exception ex) {
			log.error( "登录失败,原因未知", ex );
			request.setAttribute( "msg", "登录失败,服务器异常!" );
		}

		getAuth().getSession().setAttribute( CommonConstant.SESSION_KEY_USER, null );

		return "admin/login";
	}

	/**
	 * <p>
	 * 切换界面语言
	 * </p>
	 * <ol>
	 * [功能概要] <li>zh_CN,中文
	 * </ol>
	 * 
	 * @return 转发字符串
	 */
	@RequestMapping(value = "/switchlanguage")
	public String switchlanguage() throws Exception {
		log.info( "HomeAction switchlanguage" );

		// String o = (String) session.getAttribute( "_language" );
		String language = request.getParameter( "language" );

		if ("en_US".equals( language )) {
			session.setAttribute( "_language", "en_US" );
		} else {
			session.setAttribute( "_language", "zh_CN" );
		}

		// response.getWriter().print("1");
		return null;
	}

	/**
	 * <p>
	 * 用户登出
	 * </p>
	 * <ol>
	 * [功能概要] <li>登出。
	 * </ol>
	 * 
	 * @return 转发字符串
	 */
	@RequestMapping(value = "/logout")
	public String logout() throws Exception {
		log.info( "LoginAction logout" );
		getAuth().logout();
		log.debug( getAuth().getPrincipal() + "你已安全退出!" );
		// 清空用户登录信息
		SessionUtil.clearAdminSession( request );

		return "redirect:/admin/init";
	}

	// test
	public static void main(String[] args) {
		Locale[] locales = Locale.getAvailableLocales();

		// 获得所有已安装语言环境的数组
		for (int i = 0; i < locales.length; i++) {
			// 使用for循环进行遍历
			System.out.println( locales[ i ] ); // 输出语言环境及对应的国家/地区代码
		}
	}
}
