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
package com.hsd.account.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.hsd.framework.Response;
import com.hsd.framework.util.CommonConstant;
import com.hsd.framework.util.JwtUtil;
import com.hsd.framework.util.ValidatorUtil;
import com.hsd.vo.org.OrgUser;
import com.hsd.vo.shiro.MyShiroUserToken;
import com.hsd.web.controller.BaseController;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>登录登出action
 */
@Api(description = "会员 登录/登出")
@RestController
@Slf4j
public class LoginController extends BaseController {
    private static final String acPrefix = "/api/account/user/";
    @Autowired
    private JwtUtil jwt;
    /**
     * <p>用户登录
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix+"/login")
    @ApiOperation(value = "登录")
    public Response login(@RequestParam("accid") String accid , @RequestParam("password")String password ) throws Exception {
        log.info("LoginController login");
        Response result = new Response();
        try{
            if (ValidatorUtil.isNullEmpty(accid) || ValidatorUtil.isNullEmpty(password)) {
                return Response.error("用户名或密码不能为空!");
            }
            try {
                UsernamePasswordToken token = new MyShiroUserToken(accid, password, MyShiroUserToken.UserType.member);
                getAuth().login(token);

                OrgUser user = (OrgUser) getAuth().getSession().getAttribute(CommonConstant.SESSION_KEY_USER_MEMBER);
                session.setAttribute(CommonConstant.SESSION_KEY_USER_MEMBER, user);
                String subject = JwtUtil.generalSubject(user);
                String authorizationToken = jwt.createJWT(CommonConstant.JWT_ID, subject, CommonConstant.JWT_TTL);

               getAuth().hasRole("say me");

                Map<String,Object> data = new HashMap<>();
                data.put("tokenExpMillis", System.currentTimeMillis() + CommonConstant.JWT_TTL_REFRESH);
                data.put("authorizationToken", authorizationToken);
                data.put("user", JSONObject.parseObject(subject, OrgUser.class));
                AuthorizationInfo authorizationInfo= (AuthorizationInfo) getAuth().getSession().getAttribute("SimpleAuthorizationInfo");
                data.put("authorizationInfoPerms", authorizationInfo.getStringPermissions());
                data.put("authorizationInfoRoles",authorizationInfo.getRoles());
                data.put("sid",getAuth().getSession().getId());
                result.data = data;
                return result;
            } catch (UnknownAccountException | IncorrectCredentialsException ex) {
                result = Response.error("登录失败,用户名或密码错误1!");
            }catch (Exception ex) {
                log.error("登录失败,原因未知", ex);
                result = Response.error("登录失败,服务器异常3!");
            }
        } catch (Exception e) {
            getAuth().getSession().setAttribute(CommonConstant.SESSION_KEY_USER_MEMBER, null);
            result = Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * <p>用户登出
     */
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "logout")
    @ApiOperation(value = "登出")
    public Response logout() {
        log.info("UserController logout.........");
        Response result = new Response();
        try {
            log.debug(getAuth().getPrincipal() + "准备退出!");
            // 清空用户登录信息
            request.getSession().invalidate();
            getAuth().logout();
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    /**
     * <p> 刷新token。
     */
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "refreshToken")
    @ApiOperation(value = "刷新token")
    public Response refreshToken() {
        log.info("UserController refreshToken.........");
        Response result = new Response();
        try {
            String authorization = request.getHeader(CommonConstant.JWT_HEADER_TOKEN_KEY);
            Claims claims = jwt.parseJWT(authorization);

            Map data = new HashMap<>();
            String json = claims.getSubject();
            OrgUser user = JSONObject.parseObject(json, OrgUser.class);
            String subject = JwtUtil.generalSubject(user);
            String refreshToken = jwt.createJWT(CommonConstant.JWT_ID, subject, CommonConstant.JWT_TTL);

            data.put("tokenExpMillis", System.currentTimeMillis() + CommonConstant.JWT_TTL_REFRESH);
            data.put("authorizationToken", refreshToken);
            result.data = data;
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}
