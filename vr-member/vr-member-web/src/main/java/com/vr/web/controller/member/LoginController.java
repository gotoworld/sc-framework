/*
 * 会员登陆总入口
 *
 * VERSION  DATE        BY              REASON
 * -------- ----------- --------------- ------------------------------------------
 * 1.00     2015.01.13  wuxiaogang      程序・发布
 * -------- ----------- --------------- ------------------------------------------
 * Copyright 2015 baseos System. - All Rights Reserved.
 *
 */
package com.vr.web.controller.member;

import com.vr.framework.util.CommonConstant;
import com.vr.framework.util.ValidatorUtil;
import com.vr.vo.shiro.MyShiroUserToken;
import com.vr.vo.org.OrgUser;
import com.vr.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>登录登出action
 */
@Controller("MemberLoginController")
@RequestMapping(value = "/m")
@Slf4j
public class LoginController extends BaseController {
    private static final long serialVersionUID = -6103432072290645133L;

    /**
     * 默认的构造函数
     */
    public LoginController() {
        log.info("LoginController constructed");
    }

    /**
     * <p>init
     */
    @RequestMapping(method = {RequestMethod.GET}, value = "/init")
    public String init() throws Exception {
        log.info("LoginController init");

        return "member/login";
    }

    /**
     * <p>无权限页面
     */
    @RequestMapping(method = {RequestMethod.GET}, value = "/noauth")
    public String noauth() throws Exception {
        log.info("LoginController noauth");

        return "error/noauth";
    }

    /**
     * <p>用户登录
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/login")
    public String login() throws Exception {
        log.info("LoginController login");

        String accid = request.getParameter("accid");
        String password = request.getParameter("password");

        if (ValidatorUtil.isNullEmpty(accid) || ValidatorUtil.isNullEmpty(password)) {
            request.setAttribute("msg", "用户名或密码不能为空!");
            return "member/login";
        }

        // if (!VerifyUtil.checkVeifyCode( request, "authCode" )) {
        // request.setAttribute( "msg", "验证码校验失败!" );
        // return "member/login";
        // }

        try {
            UsernamePasswordToken token = new MyShiroUserToken(accid, password, MyShiroUserToken.UserType.member);
            getAuth().login(token);
//            String remember = request.getParameter("remember");
//            if ("1".equals(remember)) {
//                Cookie cookie = new Cookie("orgUser", accid + "=remember=" + password);
//                cookie.setMaxAge(7 * 24 * 60 * 60);//7天免登陆
//                response.addCookie(cookie);
//            }
            OrgUser orgUser = (OrgUser) getAuth().getSession().getAttribute(CommonConstant.SESSION_KEY_USER_MEMBER);
            session.setAttribute(CommonConstant.SESSION_KEY_USER_MEMBER, orgUser);
            session.setAttribute("_language", "zh_CN");
            return "redirect:/m/index";
        } catch (UnknownAccountException ex) {
            // username provided was not found
            request.setAttribute("msg", "登录失败,用户名或密码错误1!");
        } catch (IncorrectCredentialsException ex) {
            // for the username provided
            request.setAttribute("msg", "登录失败,用户名或密码错误2!");
        } catch (Exception ex) {
            log.error("登录失败,原因未知", ex);
            request.setAttribute("msg", "登录失败,服务器异常3!");
        }

        getAuth().getSession().setAttribute(CommonConstant.SESSION_KEY_USER_MEMBER, null);
        return "member/login";
    }

    /**
     * <p>切换界面语言
     */
    @RequestMapping(method = {RequestMethod.GET}, value = "/switchlanguage")
    public String switchlanguage() throws Exception {
        log.info("LoginController switchlanguage");

        // String o = (String) session.getAttribute( "_language" );
        String language = request.getParameter("language");

        if ("en_US".equals(language)) {
            session.setAttribute("_language", "en_US");
        } else {
            session.setAttribute("_language", "zh_CN");
        }

        // response.getWriter().print("1");
        return null;
    }

    /**
     * <p>用户登出
     */
    @RequestMapping(method = {RequestMethod.GET}, value = "/logout")
    public String logout() {
        log.info("LoginController logout");
        try {
            log.debug(getAuth().getPrincipal() + "准备退出!");
            // 清空用户登录信息
            request.getSession().invalidate();
            getAuth().logout();
        } catch (Exception e) {
            log.error("清空登录缓存异常!", e);
        }
        return "redirect:/m/init";
    }
}