package com.hsd.account.staff.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.hsd.account.staff.api.auth.IRoleSourceService;
import com.hsd.account.staff.api.org.IOrgLogLoginService;
import com.hsd.account.staff.dto.auth.AuthPermDto;
import com.hsd.account.staff.dto.auth.AuthRoleDto;
import com.hsd.account.staff.dto.org.OrgLogLoginDto;
import com.hsd.account.staff.dto.org.OrgUserDto;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.NoAuthorize;
import com.hsd.framework.security.MD5;
import com.hsd.framework.util.CommonConstant;
import com.hsd.framework.util.IpUtil;
import com.hsd.framework.util.JwtUtil;
import com.hsd.framework.util.ValidatorUtil;
import com.hsd.web.controller.BaseController;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * <p>登录登出action
 */
@Api(description = "登录/登出")
@RestController
@Slf4j
@NoAuthorize
public class LoginController extends BaseController {
    private static final String acPrefix = "/boss/account/staff/sign/";
    @Autowired
    private IRoleSourceService roleSourceService;
    @Autowired
    private IOrgLogLoginService logLoginService;

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
            OrgUserDto orgUser = roleSourceService.findUserByAccount(account, 0);
            String pwdhex = MD5.pwdMd5Hex(password);
            if (orgUser==null || !(account.equals(orgUser.getAccount()) && pwdhex.equals(orgUser.getPwd()))) {
                return Response.error("登录失败,用户名或密码错误!");
            }
            roleSourceService.lastLogin(orgUser);

            Map<String, Set<String>> authorizationInfo = new HashMap();
            authorizationInfo.put("roles", new HashSet<>());
            authorizationInfo.put("permissions", new HashSet<>());
            if (0 == (orgUser.getType()) && roleSourceService.isSuperAdmin(orgUser) > 0) {
                //超级管理员标记
                orgUser.setIissuperman(1);
                request.getSession().setAttribute("isSuper", "1");
            }
            //2.获取角色集合
            List<AuthRoleDto> roleList = roleSourceService.getRoleListByUserId(orgUser);
            if (roleList != null) {
                for (AuthRoleDto role : roleList) {
                    authorizationInfo.get("roles").add(role.getName());
                }
            }
            //3.获取功能集合
            List<AuthPermDto> permList = roleSourceService.getPermListByUserId(orgUser);
            if (permList != null) {
                for (AuthPermDto perm : permList) {
                    if (perm.getMatchStr() != null && !"".equals(perm.getMatchStr())) {
                        authorizationInfo.get("permissions").add(perm.getMatchStr());
                    }
                }
            }
            orgUser.setAuthorizationInfoPerms(authorizationInfo.get("permissions"));
            orgUser.setAuthorizationInfoRoles(authorizationInfo.get("roles"));
            String subject = JwtUtil.generalSubject(orgUser);
            String authorizationToken = JwtUtil.createJWT(CommonConstant.JWT_ID, subject, CommonConstant.JWT_TTL);

            Map<String, Object> data = new HashMap<>();
            data.put("tokenExpMillis", System.currentTimeMillis() + CommonConstant.JWT_TTL_REFRESH);
            data.put("authorizationToken", authorizationToken);
            data.put("user", JSONObject.parseObject(subject, OrgUserDto.class));

            data.put("authorizationInfoPerms", authorizationInfo.get("permissions"));
            data.put("authorizationInfoRoles", authorizationInfo.get("roles"));

            try {
                //读取session中的用户
                OrgUserDto user = orgUser;
                OrgLogLoginDto logDto = new OrgLogLoginDto();
                logDto.setType(0);//类型0登录1登出
                logDto.setIpAddr(IpUtil.getIpAddr(request));//请求的IP
                logDto.setUserId(user.getId());//用户id
                logDto.setUserName(user.getName());//员工名称
//                logDto.setDeviceMac(IpUtil.getMACAddress(logDto.getIpAddr()));//MAC地址
                logLoginService.saveOrUpdateData(logDto);
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
        log.info("UserController logout.........");
        Response result = new Response();
        try {
            log.debug("准备退出!");
            try {
                String authorization = request.getHeader(CommonConstant.JWT_HEADER_TOKEN_KEY);
                Claims claims = JwtUtil.parseJWT(authorization);
                String json = claims.getSubject();
                OrgUserDto user = JSONObject.parseObject(json, OrgUserDto.class);

                OrgLogLoginDto logDto = new OrgLogLoginDto();
                logDto.setType(1);//类型0登录1登出
                logDto.setIpAddr(IpUtil.getIpAddr(request));//请求的IP
                logDto.setUserId(user.getId());//用户id
                logDto.setUserName(user.getName());//员工名称
//            logDto.setDeviceMac(IpUtil.getMACAddress(logDto.getIpAddr()));//MAC地址
                logLoginService.saveOrUpdateData(logDto);
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
        log.info("UserController refreshToken.........");
        Response result = new Response();
        try {
            String authorization = request.getHeader(CommonConstant.JWT_HEADER_TOKEN_KEY);
            Claims claims = JwtUtil.parseJWT(authorization);

            Map data = new HashMap<>();
            String json = claims.getSubject();
            OrgUserDto user = JSONObject.parseObject(json, OrgUserDto.class);
            String subject = JwtUtil.generalSubject(user);
            String refreshToken = JwtUtil.createJWT(CommonConstant.JWT_ID, subject, CommonConstant.JWT_TTL);

            data.put("tokenExpMillis", System.currentTimeMillis() + CommonConstant.JWT_TTL_REFRESH);
            data.put("authorizationToken", refreshToken);
            result.data = data;
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}
