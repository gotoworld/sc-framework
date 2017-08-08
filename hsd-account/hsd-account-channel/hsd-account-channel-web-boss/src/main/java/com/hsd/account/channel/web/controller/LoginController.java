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
package com.hsd.account.channel.web.controller;

import com.hsd.framework.annotation.NoAuthorize;
import com.hsd.web.controller.BaseController;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>登录登出action
 */
@Api(description = "登录/登出")
@RestController
@Slf4j
@NoAuthorize
public class LoginController extends BaseController {
    private static final String acPrefix = "/boss/account/channel/sign/";
//    @Autowired
//    private IRoleSourceService roleSourceService;
//    /**
//     * <p>用户登录
//     */
//    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix+"/login")
//    @ApiOperation(value = "登录")
//    public Response login(@RequestParam("account") String account , @RequestParam("password")String password ) throws Exception {
//        log.info("LoginController login");
//        Response result = new Response();
//            try {
//                if (ValidatorUtil.isNullEmpty(account) || ValidatorUtil.isNullEmpty(password)) {
//                    return Response.error("用户名或密码不能为空!");
//                }
//                OrgUserDto orgUser = (OrgUserDto) getAuth().getSession().getAttribute(CommonConstant.SESSION_KEY_USER);
//                if(orgUser!=null && !account.equals(orgUser.getAccount())){
//                    try {getAuth().logout();} catch (Exception e1) {}//注销之前的用户
//                }
//                MyShiroUserToken token = new MyShiroUserToken(account, password, MyShiroUserToken.UserType.admin);
//                getAuth().login(token);
//                getAuth().hasRole("say me");
//
//                if(orgUser==null||!account.equals(orgUser.getAccount())) {
//                    orgUser = roleSourceService.findUserByLoginName(account,token.getUserType().getId());
//                    SecurityUtils.getSubject().getSession().setAttribute(CommonConstant.SESSION_KEY_USER, orgUser);
//                    SecurityUtils.getSubject().getSession().setAttribute(CommonConstant.SESSION_KEY_USERNAME, orgUser.getAccount()+":"+orgUser.getName());
//                    SecurityUtils.getSubject().getSession().setAttribute(token.getUserType().getCacheKey(), orgUser);
//                    roleSourceService.lastLogin(orgUser);
//                }
//                orgUser.setSid((String) getAuth().getSession().getId());
//                String subject = JwtUtil.generalSubject(orgUser);
//                String authorizationToken = JwtUtil.createJWT(CommonConstant.JWT_ID, subject, CommonConstant.JWT_TTL,(String) getAuth().getSession().getId());
//
//                SimpleAuthorizationInfo authorizationInfo= (SimpleAuthorizationInfo) SecurityUtils.getSubject().getSession().getAttribute("SimpleAuthorizationInfo");
//                if(authorizationInfo==null){
//                    authorizationInfo=new SimpleAuthorizationInfo();
//                    if (0==(orgUser.getType()) && roleSourceService.isSuperAdmin(orgUser) > 0) {
//                        //超级管理员标记
//                        orgUser.setIissuperman(1);
//                        SecurityUtils.getSubject().getSession().setAttribute("isSuper", "1");
//                    }
//                    //2.获取角色集合
//                    List<AuthRoleDto> roleList = roleSourceService.getRoleListByUId(orgUser);
//                    if (roleList != null) {
//                        for (AuthRoleDto role : roleList) {
//                            authorizationInfo.addRole(role.getName());
//                        }
//                    }
//                    //3.获取功能集合
//                    List<AuthPermDto> permList = roleSourceService.getPermListByUId(orgUser);
//                    if (permList != null) {
//                        for (AuthPermDto perm : permList) {
//                            if (perm.getMatchStr() != null && !"".equals(perm.getMatchStr())) {
//                                authorizationInfo.addStringPermission(perm.getMatchStr());
//                            }
//                        }
//                    }
//                    SecurityUtils.getSubject().getSession().setAttribute("SimpleAuthorizationInfo", authorizationInfo);
//                }
//
//                Map<String,Object> data = new HashMap<>();
//                data.put("tokenExpMillis", System.currentTimeMillis() + CommonConstant.JWT_TTL_REFRESH);
//                data.put("authorizationToken", authorizationToken);
//                data.put("user", JSONObject.parseObject(subject, OrgUserDto.class));
//
//                data.put("authorizationInfoPerms", authorizationInfo.getStringPermissions());
//                data.put("authorizationInfoRoles",authorizationInfo.getRoles());
//                data.put("sid",getAuth().getSession().getId());
//                result.data = data;
//                return result;
//            } catch (UnknownAccountException | IncorrectCredentialsException ex) {
//                try {getAuth().logout();} catch (Exception e1) {}
//                result = Response.error("登录失败,用户名或密码错误1!");
//            }catch (Exception ex) {
//                try {getAuth().logout();} catch (Exception e1) {}
//                log.error("登录失败,原因未知", ex);
//                result = Response.error("登录失败,服务器异常3!");
//            }
//        return result;
//    }
//    /**
//     * <p>用户登出
//     */
//    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "logout")
//    @ApiOperation(value = "登出")
//    public Response logout() {
//        log.info("UserController logout.........");
//        Response result = new Response();
//        try {
//            log.debug(getAuth().getPrincipal() + "准备退出!");
//            getAuth().logout();
//            request.getSession().invalidate();
//        } catch (Exception e) {
//            result = Response.error(e.getMessage());
//        }
//        return result;
//    }
//
//    /**
//     * <p> 刷新token。
//     */
//    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "refreshToken")
//    @ApiOperation(value = "刷新token")
//    public Response refreshToken() {
//        log.info("UserController refreshToken.........");
//        Response result = new Response();
//        try {
//            String authorization = request.getHeader(CommonConstant.JWT_HEADER_TOKEN_KEY);
//            Claims claims = JwtUtil.parseJWT(authorization);
//
//            Map data = new HashMap<>();
//            String json = claims.getSubject();
//            OrgUserDto user = JSONObject.parseObject(json, OrgUserDto.class);
//            String subject = JwtUtil.generalSubject(user);
//            String refreshToken = JwtUtil.createJWT(CommonConstant.JWT_ID, subject, CommonConstant.JWT_TTL,(String) getAuth().getSession().getId());
//
//            data.put("tokenExpMillis", System.currentTimeMillis() + CommonConstant.JWT_TTL_REFRESH);
//            data.put("authorizationToken", refreshToken);
//            result.data = data;
//        } catch (Exception e) {
//            result = Response.error(e.getMessage());
//        }
//        return result;
//    }
}
