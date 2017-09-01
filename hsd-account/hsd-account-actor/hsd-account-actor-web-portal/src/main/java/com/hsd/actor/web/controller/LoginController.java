package com.hsd.actor.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.hsd.account.actor.api.user.IUserLoginLogService;
import com.hsd.account.actor.api.user.IUserService;
import com.hsd.account.actor.dto.user.UserDto;
import com.hsd.account.actor.dto.user.UserLoginLogDto;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.NoAuthorize;
import com.hsd.framework.security.MD5;
import com.hsd.framework.util.CommonConstant;
import com.hsd.framework.util.IpUtil;
import com.hsd.framework.util.JwtUtil;
import com.hsd.framework.util.ValidatorUtil;
import com.hsd.framework.web.controller.BaseController;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
@Api(description = "登录/登出")
@RestController
@Slf4j
@NoAuthorize
public class LoginController extends BaseController {
    private static final String acPrefix = "/api/account/actor/sign/";
    @Autowired
    private IUserService userService;
    @Autowired
    private IUserLoginLogService loginLogService;

    /**
     * <p>用户登录
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "/login")
    @ApiOperation(value = "登录")
    public Response login(@RequestParam("account") String account, @RequestParam("password") String password) throws Exception {
        log.info("LoginController login");
        Response result = new Response();
        try {
            if (ValidatorUtil.isNullEmpty(account) || ValidatorUtil.isNullEmpty(password)) {
                return Response.error("用户名或密码不能为空!");
            }
            UserDto user = userService.findUserByAccount(account, UserDto.userType.USER.getCode());
            String pwdhex = MD5.pwdMd5Hex(password);
            if (user == null || !(account.equals(user.getAccount()) && pwdhex.equals(user.getPwd()))) {
                return Response.error("登录失败,用户名或密码错误!");
            }
            userService.lastLogin(user);

            String subject = JwtUtil.generalSubject(user);
            String authorizationToken = JwtUtil.createJWT(JwtUtil.UserType.USER, CommonConstant.JWT_ID, subject, CommonConstant.JWT_TTL);

            Map<String, Object> data = new HashMap<>();
            data.put("tokenExpMillis", System.currentTimeMillis() + CommonConstant.JWT_TTL_REFRESH);
            data.put("authorizationToken", authorizationToken);
            data.put("user", JSONObject.parseObject(subject, UserDto.class));

            try {
                //读取session中的用户
                UserDto actor = user;
                UserLoginLogDto logDto = new UserLoginLogDto();
                logDto.setType(0);//类型0登录1登出
                logDto.setIpAddr(IpUtil.getIpAddr(request));//请求的IP
                logDto.setUserId(actor.getId());//id
                logDto.setUserName(actor.getName());//用户名称
//                logDto.setDeviceMac(IpUtil.getMACAddress(logDto.getIpAddr()));//MAC地址
                loginLogService.saveOrUpdateData(logDto);
            } catch (Exception e) {
            }
            result.data = data;
        } catch (SecurityException e) {
            result = Response.error("登录失败,用户名或密码错误1!");
        } catch (Exception ex) {
            log.error("登录失败,原因未知", ex);
            result = Response.error("登录失败,服务器异常3!");
        }
        return result;
    }

    /**
     * <p>用户登出
     */
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "logout")
    @ApiOperation(value = "登出")
    public Response logout() {
        log.info("LoginController logout.........");
        Response result = new Response();
        try {
            log.debug("准备退出!");
            try {
                String authorization = request.getHeader(CommonConstant.JWT_HEADER_TOKEN_KEY);
                Claims claims = JwtUtil.parseJWT(authorization);
                String json = claims.getSubject();
                UserDto actor = JSONObject.parseObject(json, UserDto.class);

                UserLoginLogDto logDto = new UserLoginLogDto();
                logDto.setType(1);//类型0登录1登出
                logDto.setIpAddr(IpUtil.getIpAddr(request));//请求的IP
                logDto.setUserId(actor.getId());//id
                logDto.setUserName(actor.getName());//用户名称
//            logDto.setDeviceMac(IpUtil.getMACAddress(logDto.getIpAddr()));//MAC地址
                loginLogService.saveOrUpdateData(logDto);
            } catch (Exception e) {
            }
            try {
                request.getSession().invalidate();
            } catch (Exception e) {
            }
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
        log.info("LoginController refreshToken.........");
        Response result = new Response();
        try {
            String authorization = request.getHeader(CommonConstant.JWT_HEADER_TOKEN_KEY);
            Claims claims = JwtUtil.parseJWT(authorization);

            Map data = new HashMap<>();
            String json = claims.getSubject();
            UserDto actor = JSONObject.parseObject(json, UserDto.class);
            String subject = JwtUtil.generalSubject(actor);
            String refreshToken = JwtUtil.createJWT(JwtUtil.UserType.USER, CommonConstant.JWT_ID, subject, CommonConstant.JWT_TTL);

            data.put("tokenExpMillis", System.currentTimeMillis() + CommonConstant.JWT_TTL_REFRESH);
            data.put("authorizationToken", refreshToken);
            result.data = data;
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}
