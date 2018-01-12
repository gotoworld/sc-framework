package com.hsd.account.staff.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.hsd.account.staff.api.auth.IRoleSourceService;
import com.hsd.account.staff.api.org.IOrgLogLoginService;
import com.hsd.account.staff.api.sys.ISysAppService;
import com.hsd.account.staff.api.org.IOrgStaffAppService;
import com.hsd.account.staff.dto.auth.AuthPermDto;
import com.hsd.account.staff.dto.auth.AuthRoleDto;
import com.hsd.account.staff.dto.org.OrgLogLoginDto;
import com.hsd.account.staff.dto.org.OrgStaffAppDto;
import com.hsd.account.staff.dto.org.OrgStaffDto;
import com.hsd.account.staff.dto.sys.SysAppDto;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.NoAuthorize;
import com.hsd.framework.cache.util.RedisHelper;
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

import java.util.*;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    private ISysAppService sysAppService;
    @Autowired
    private IOrgStaffAppService userAppService;
    @Autowired
    private RedisHelper redisHelper;


    /**
     * <p>员工登录
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "/login")
    @ApiOperation(value = "登录")
    public Response login(@RequestParam("account") String account, @RequestParam("password") String password,@RequestParam("appId") String appId) {
        log.info("LoginController login");
        Response result = new Response();
        try {
            if (ValidatorUtil.isNullEmpty(account) || ValidatorUtil.isNullEmpty(password)) {
                return Response.error("员工名或密码不能为空!");
            }
            OrgStaffDto orgStaffDto = roleSourceService.findStaffByAccount(account, 0);
            String pwdhex = MD5.pwdMd5Hex(password);
            if (orgStaffDto==null || !(account.equals(orgStaffDto.getAccount()) && pwdhex.equals(orgStaffDto.getPwd()))) {
                return Response.error("登录失败,员工名或密码错误!");
            }
            SysAppDto sysAppDto = sysAppService.findDataById(new SysAppDto(){{setId(appId);}});
            if (sysAppDto == null){
                return  Response.error("应用系统不存在!");
            }
            roleSourceService.lastLogin(orgStaffDto);

            if (0 == (orgStaffDto.getType()) && roleSourceService.isSuperAdmin(orgStaffDto) > 0) {
                //超级管理员标记
                orgStaffDto.setIissuperman(1);
                request.getSession().setAttribute("isSuper", "1");
            }
            //3.获取应用集合
            List<SysAppDto> appList = roleSourceService.getAppListByStaffId(orgStaffDto);
            boolean appFlag=false;
            if(appList!=null){
                for (SysAppDto app : appList) {
                    if(appId.equals(app.getId())){
                        appFlag=true;break;
                    }
                }
            }
            if(!appFlag) return Response.error("登录失败,应用权限受限!");

            //查询APP员工表
            OrgStaffAppDto userAppDto = userAppService.getSaveDataByAppIdAndStaffId(new OrgStaffAppDto(){{setStaffId(orgStaffDto.getId());setAppId(sysAppDto.getId());}});
            orgStaffDto.setAppStaffId(userAppDto.getId());
            orgStaffDto.setAppId(sysAppDto.getId());
            orgStaffDto.setAppName(sysAppDto.getName());

            Map<String, Set<String>> authorizationInfo = new HashMap();
            authorizationInfo.put("roles", new HashSet<>());
            authorizationInfo.put("permissions", new HashSet<>());
            //2.获取角色集合
            List<AuthRoleDto> roleList = roleSourceService.getRoleListByStaffId(orgStaffDto);
            if (roleList != null) {
                for (AuthRoleDto role : roleList) {
                    authorizationInfo.get("roles").add(role.getName());
                }
            }
            //3.获取功能集合
            List<AuthPermDto> permList = roleSourceService.getPermListByStaffId(orgStaffDto);
            if (permList != null) {
                for (AuthPermDto perm : permList) {
                    if (perm.getMatchStr() != null && !"".equals(perm.getMatchStr())) {
                        authorizationInfo.get("permissions").add(perm.getMatchStr());
                    }
                }
            }
            orgStaffDto.setAuthorizationInfoPerms(authorizationInfo.get("permissions"));
            orgStaffDto.setAuthorizationInfoRoles(authorizationInfo.get("roles"));
            String subject = JwtUtil.generalSubject(orgStaffDto);
            String authorizationToken = JwtUtil.createJWT(JwtUtil.UserType.STAFF,CommonConstant.JWT_ID, subject, CommonConstant.JWT_TTL);

            Map<String, Object> data = new HashMap<>();
            data.put("tokenExpMillis", System.currentTimeMillis() + CommonConstant.JWT_TTL_REFRESH);
            data.put("authorizationToken", authorizationToken);
            data.put("app", appList);
            data.put("staff", JSONObject.parseObject(subject, OrgStaffDto.class));
            data.put("XCache", request.getSession().getId());

            redisHelper.set("u:"+orgStaffDto.getId()+":"+orgStaffDto.getAppStaffId()+"",JwtUtil.parseJWT(authorizationToken).getIssuedAt().getTime(),CommonConstant.JWT_TTL, TimeUnit.MILLISECONDS);

            try {
                //读取session中的员工
                OrgLogLoginDto logDto = new OrgLogLoginDto();
                logDto.setType(0);//类型0登录1登出
                logDto.setIpAddr(IpUtil.getIpAddr(request));//请求的IP
                logDto.setStaffId(orgStaffDto.getId());//id
                logDto.setStaffName(orgStaffDto.getName());//员工名称
                logDto.setAppStaffId(orgStaffDto.getAppStaffId());//app用户id
                logDto.setAppId(orgStaffDto.getAppId());//系统ID
                logDto.setAppName(orgStaffDto.getAppName());
//                logDto.setDeviceMac(IpUtil.getMACAddress(logDto.getIpAddr()));//MAC地址
                logLoginService.saveOrUpdateData(logDto);
            } catch (Exception e) {
            }
            result.data = data;
        } catch (SecurityException e) {
            result = Response.error("登录失败,员工名或密码错误1!");
        } catch (Exception ex) {
            log.error("登录失败,原因未知", ex);
            result = Response.error("登录失败,服务器异常3!");
        }
        return result;
    }

    /**
     * <p>员工登出
     */
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "logout")
    @ApiOperation(value = "登出")
    public Response logout() {
        log.info("StaffController logout.........");
        Response result = new Response();
        try {
            log.debug("准备退出!");
            try {
                String authorization = request.getHeader(CommonConstant.JWT_HEADER_TOKEN_KEY);
                Claims claims = JwtUtil.parseJWT(authorization);
                String json = claims.getSubject();
                OrgStaffDto orgStaffDto = JSONObject.parseObject(json, OrgStaffDto.class);

                OrgLogLoginDto logDto = new OrgLogLoginDto();
                logDto.setType(1);//类型0登录1登出
                logDto.setIpAddr(IpUtil.getIpAddr(request));//请求的IP
                logDto.setStaffId(orgStaffDto.getId());//id
                logDto.setStaffName(orgStaffDto.getName());//员工名称
                logDto.setAppName(orgStaffDto.getAppName());
                logDto.setAppStaffId(orgStaffDto.getAppStaffId());
                logDto.setAppId(orgStaffDto.getAppId());
//            logDto.setDeviceMac(IpUtil.getMACAddress(logDto.getIpAddr()));//MAC地址
                logLoginService.saveOrUpdateData(logDto);

                redisHelper.del("u:"+orgStaffDto.getId()+":"+orgStaffDto.getAppStaffId()+"");
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
        log.info("StaffController refreshToken.........");
        Response result = new Response();
        try {
            String authorization = request.getHeader(CommonConstant.JWT_HEADER_TOKEN_KEY);
            Claims claims = JwtUtil.parseJWT(authorization);

            Map data = new HashMap<>();
            String json = claims.getSubject();
            OrgStaffDto orgStaffDto = JSONObject.parseObject(json, OrgStaffDto.class);
            String subject = JwtUtil.generalSubject(orgStaffDto);
            String refreshToken = JwtUtil.createJWT(JwtUtil.UserType.STAFF,CommonConstant.JWT_ID, subject, CommonConstant.JWT_TTL);

            data.put("tokenExpMillis", System.currentTimeMillis() + CommonConstant.JWT_TTL_REFRESH);
            data.put("authorizationToken", refreshToken);
            data.put("XCache", request.getSession().getId());
            redisHelper.set("u:"+orgStaffDto.getId()+":"+orgStaffDto.getAppStaffId()+"",JwtUtil.parseJWT(refreshToken).getIssuedAt().getTime(), CommonConstant.JWT_TTL, TimeUnit.MILLISECONDS);
            result.data = data;
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }


}
