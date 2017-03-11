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
import com.wu1g.vo.org.OrgUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import java.util.Locale;

/**
 * <p>登录登出action
 */
@Controller
@RequestMapping(value = "/admin")
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
        log.info("HomeController init");

        return "admin/login";
    }

    /**
     * <p>无权限页面
     */
    @RequestMapping(method = {RequestMethod.GET}, value = "/noauth")
    public String noauth() throws Exception {
        log.info("HomeController noauth");

        return "error/noauth";
    }

    /**
     * <p>用户登录
     */
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = "/login")
    public String login() throws Exception {
        log.info("HomeController login");

        String accid = request.getParameter("accid");
        String password = request.getParameter("password");

        if (ValidatorUtil.isNullEmpty(accid) || ValidatorUtil.isNullEmpty(password)) {
            request.setAttribute("msg", "用户名或密码不能为空!");
            return "admin/login";
        }

        // if (!VerifyUtil.checkVeifyCode( request, "authCode" )) {
        // request.setAttribute( "msg", "验证码校验失败!" );
        // return "admin/login";
        // }

        try {
            UsernamePasswordToken token = new UsernamePasswordToken(accid, password);
            getAuth().login(token);
            String remember = request.getParameter("remember");
            if ("1".equals(remember)) {
                Cookie cookie = new Cookie("orgUser", accid + "=remember=" + password);
                cookie.setMaxAge(7 * 24 * 60 * 60);//7天免登陆
                response.addCookie(cookie);
            }
            OrgUser orgUser = (OrgUser) getAuth().getSession().getAttribute(CommonConstant.SESSION_KEY_USER);
            // System.out.println(orgUser);
            session.setAttribute(CommonConstant.SESSION_KEY_USER, orgUser);
            session.setAttribute("_language", "zh_CN");

            return "redirect:/h/index";
        } catch (UnknownAccountException ex) {
            // username provided was not found
            request.setAttribute("msg", "登录失败,用户名或密码错误!");
        } catch (IncorrectCredentialsException ex) {
            // for the username provided
            request.setAttribute("msg", "登录失败,用户名或密码错误!");
        } catch (Exception ex) {
            log.error("登录失败,原因未知", ex);
            request.setAttribute("msg", "登录失败,服务器异常!");
        }

        getAuth().getSession().setAttribute(CommonConstant.SESSION_KEY_USER, null);

        return "admin/login";
    }

    /**
     * <p>切换界面语言
     */
    @RequestMapping(method = {RequestMethod.GET}, value = "/switchlanguage")
    public String switchlanguage() throws Exception {
        log.info("HomeController switchlanguage");

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
            // 清空用户登录信息
            request.getSession().invalidate();
            getAuth().logout();
            log.debug(getAuth().getPrincipal() + "你已安全退出!");
        } catch (Exception e) {
            log.error("清空登录缓存异常!", e);
        }
        return "admin/login";
    }

    // test
    public static void main(String[] args) {
        Locale[] locales = Locale.getAvailableLocales();

        // 获得所有已安装语言环境的数组
        for (int i = 0; i < locales.length; i++) {
            // 使用for循环进行遍历
            System.out.println(locales[i]); // 输出语言环境及对应的国家/地区代码
        }
    }
}
