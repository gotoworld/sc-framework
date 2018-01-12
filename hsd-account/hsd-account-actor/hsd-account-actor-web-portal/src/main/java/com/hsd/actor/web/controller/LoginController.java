package com.hsd.actor.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.hsd.account.actor.api.identity.IIdentityService;
import com.hsd.account.actor.api.user.IUserAppService;
import com.hsd.account.actor.api.user.IUserLoginLogService;
import com.hsd.account.actor.api.user.IUserService;
import com.hsd.account.actor.dto.identity.IdentityDto;
import com.hsd.account.actor.dto.user.UserAppDto;
import com.hsd.account.actor.dto.user.UserDto;
import com.hsd.account.actor.dto.user.UserLoginLogDto;
import com.hsd.account.staff.api.sys.ISysAppService;
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

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    private IIdentityService identityService;
    @Autowired
    private ISysAppService sysAppService;
    @Autowired
    private IUserAppService userAppService;
    @Autowired
    private RedisHelper redisHelper;
    /**
     * <p>用户登录
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "/login")
    @ApiOperation(value = "登录")
    public Response login(@RequestParam("account") String account, @RequestParam("password") String password,@RequestParam("appId") String appId) {
        log.info("LoginController login");
        Response result = new Response(0, "success");
        try {
            if (ValidatorUtil.isNullEmpty(account) || ValidatorUtil.isNullEmpty(password)) {
                return Response.error("用户名或密码不能为空!");
            }
            SysAppDto sysAppDto = sysAppService.findDataById(new SysAppDto(){{setId(appId);}});
            if (sysAppDto == null){
                return  Response.error("应用系统不存在!");
            }
            UserDto userDto = userService.findUserByAccount(account, UserDto.userType.USER.getCode());
            String pwdhex = MD5.pwdMd5Hex(password);
            if (userDto == null || !(account.equals(userDto.getAccount()) && pwdhex.equals(userDto.getPwd()))) {
                return Response.error("登录失败,用户名或密码错误!");
            }
            userService.lastLogin(userDto);



            //查询APP员工表
            UserAppDto userAppDto = userAppService.getSaveDataByAppIdAndUserId(new UserAppDto(){{setUserId(userDto.getId());setAppId(sysAppDto.getId());}});
            userDto.setAppUserId(userAppDto.getId());
            userDto.setAppId(sysAppDto.getId());
            userDto.setAppName(sysAppDto.getName());

            String subject = JwtUtil.generalSubject(userDto);
            String authorizationToken = JwtUtil.createJWT(JwtUtil.UserType.USER, CommonConstant.JWT_ID, subject, CommonConstant.JWT_TTL);

            Map<String, Object> data = new HashMap<>();
            data.put("tokenExpMillis", System.currentTimeMillis() + CommonConstant.JWT_TTL_REFRESH);
            data.put("authorizationToken", authorizationToken);
            redisHelper.set("u:"+userDto.getId()+":"+userDto.getAppUserId()+"",JwtUtil.parseJWT(authorizationToken).getIssuedAt().getTime(),CommonConstant.JWT_TTL, TimeUnit.MILLISECONDS);
            UserDto userJon=JSONObject.parseObject(subject, UserDto.class);

            if (ValidatorUtil.notEmpty(userDto.getCellphone())){
                userJon.setCellphone(userDto.getCellphone().replaceAll("(\\d{3})\\d{5}(\\d{3})", "$1****$2"));
                userJon.setIsCellphoneYN(1);
            }else{
                userJon.setIsCellphoneYN(0);
            }
            if (ValidatorUtil.notEmpty(userDto.getEmail())){
                userJon.setEmail(userDto.getEmail().substring(0, 1) + "****" + userDto.getEmail().substring(userDto.getEmail().indexOf("@") - 1, userDto.getEmail().length()));
                userJon.setIsEmailYN(1);
            }else{
                userJon.setIsEmailYN(0);
            }
            if(ValidatorUtil.notEmpty(userDto.getTradePwd())){
                userJon.setIsTradePwdYN(1);
            } else{
                userJon.setIsTradePwdYN(0);
            }
            IdentityDto identityDto=identityService.findDataById(new IdentityDto(){{setUserId(userDto.getId());}});
            if(identityDto!=null){
                userJon.setRealName(identityDto.getRealName());
                userJon.setCredentialNumber(identityDto.getCredentialNumber().substring(0, 3) + "***********" + identityDto.getCredentialNumber().substring(identityDto.getCredentialNumber().length()- 4, identityDto.getCredentialNumber().length()));
                userJon.setIsIdentityYN(1);
            }else{
                userJon.setIsIdentityYN(0);
            }

            data.put("user",userJon );
            try {
                //读取session中的用户
                UserLoginLogDto logDto = new UserLoginLogDto();
                logDto.setType(0);//类型0登录1登出
                logDto.setIpAddr(IpUtil.getIpAddr(request));//请求的IP
                logDto.setUserId(userDto.getId());//id
                logDto.setUserName(userDto.getName());//用户名称
                logDto.setUserName(userDto.getName());//员工名称
                logDto.setAppStaffId(userDto.getAppUserId());//app用户id
                logDto.setAppId(userDto.getAppId());//系统ID
                logDto.setAppName(userDto.getAppName());

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
        Response result = new Response(0, "success");
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
                logDto.setAppName(actor.getAppName());
                logDto.setAppUserId(actor.getAppUserId());
                logDto.setAppId(actor.getAppId());
//            logDto.setDeviceMac(IpUtil.getMACAddress(logDto.getIpAddr()));//MAC地址
                loginLogService.saveOrUpdateData(logDto);

                redisHelper.del("u:"+actor.getId()+":"+actor.getAppUserId()+"");
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
        Response result = new Response(0, "success");
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
            data.put("XCache", request.getSession().getId());
            redisHelper.set("u:"+actor.getId()+":"+actor.getAppUserId()+"",JwtUtil.parseJWT(refreshToken).getIssuedAt().getTime(), CommonConstant.JWT_TTL, TimeUnit.MILLISECONDS);
            result.data = data;
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}
