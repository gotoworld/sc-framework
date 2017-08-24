package com.hsd.account.actor.web.controller;

import com.hsd.framework.annotation.NoAuthorize;
import com.hsd.framework.web.controller.BaseController;
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
    private static final String acPrefix = "/boss/account/actor/sign/";
//    @Autowired
//    private IOrgLogLoginService logLoginService;
//
//    /**
//     * <p>员工登录
//     */
//    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "/login")
//    @ApiOperation(value = "登录")
//    public Response login(@RequestParam("account") String account, @RequestParam("password") String password) throws Exception {
//        log.info("LoginController login");
//        Response result = new Response();
//        try {
//            if (ValidatorUtil.isNullEmpty(account) || ValidatorUtil.isNullEmpty(password)) {
//                return Response.error("员工名或密码不能为空!");
//            }
//            OrgActorDto orgActor = roleSourceService.findActorByAccount(account, 0);
//            String pwdhex = MD5.pwdMd5Hex(password);
//            if (orgActor==null || !(account.equals(orgActor.getAccount()) && pwdhex.equals(orgActor.getPwd()))) {
//                return Response.error("登录失败,员工名或密码错误!");
//            }
//            roleSourceService.lastLogin(orgActor);
//
//            Map<String, Set<String>> authorizationInfo = new HashMap();
//            authorizationInfo.put("roles", new HashSet<>());
//            authorizationInfo.put("permissions", new HashSet<>());
//            if (0 == (orgActor.getType()) && roleSourceService.isSuperAdmin(orgActor) > 0) {
//                //超级管理员标记
//                orgActor.setIissuperman(1);
//                request.getSession().setAttribute("isSuper", "1");
//            }
//            //2.获取角色集合
//            List<AuthRoleDto> roleList = roleSourceService.getRoleListByActorId(orgActor);
//            if (roleList != null) {
//                for (AuthRoleDto role : roleList) {
//                    authorizationInfo.get("roles").add(role.getName());
//                }
//            }
//            //3.获取功能集合
//            List<AuthPermDto> permList = roleSourceService.getPermListByActorId(orgActor);
//            if (permList != null) {
//                for (AuthPermDto perm : permList) {
//                    if (perm.getMatchStr() != null && !"".equals(perm.getMatchStr())) {
//                        authorizationInfo.get("permissions").add(perm.getMatchStr());
//                    }
//                }
//            }
//            orgActor.setAuthorizationInfoPerms(authorizationInfo.get("permissions"));
//            orgActor.setAuthorizationInfoRoles(authorizationInfo.get("roles"));
//            String subject = JwtUtil.generalSubject(orgActor);
//            String authorizationToken = JwtUtil.createJWT(CommonConstant.JWT_ID, subject, CommonConstant.JWT_TTL);
//
//            Map<String, Object> data = new HashMap<>();
//            data.put("tokenExpMillis", System.currentTimeMillis() + CommonConstant.JWT_TTL_REFRESH);
//            data.put("authorizationToken", authorizationToken);
//            data.put("actor", JSONObject.parseObject(subject, OrgActorDto.class));
//
//            data.put("authorizationInfoPerms", authorizationInfo.get("permissions"));
//            data.put("authorizationInfoRoles", authorizationInfo.get("roles"));
//
//            try {
//                //读取session中的员工
//                OrgActorDto actor = orgActor;
//                OrgLogLoginDto logDto = new OrgLogLoginDto();
//                logDto.setType(0);//类型0登录1登出
//                logDto.setIpAddr(IpUtil.getIpAddr(request));//请求的IP
//                logDto.setActorId(actor.getId());//id
//                logDto.setActorName(actor.getName());//员工名称
////                logDto.setDeviceMac(IpUtil.getMACAddress(logDto.getIpAddr()));//MAC地址
//                logLoginService.saveOrUpdateData(logDto);
//            } catch (Exception e) {
//            }
//            result.data = data;
//        } catch (SecurityException e) {
//            result = Response.error("登录失败,员工名或密码错误1!");
//        } catch (Exception ex) {
//            log.error("登录失败,原因未知", ex);
//            result = Response.error("登录失败,服务器异常3!");
//        }
//        return result;
//    }
//
//    /**
//     * <p>员工登出
//     */
//    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "logout")
//    @ApiOperation(value = "登出")
//    public Response logout() {
//        log.info("ActorController logout.........");
//        Response result = new Response();
//        try {
//            log.debug("准备退出!");
//            try {
//                String authorization = request.getHeader(CommonConstant.JWT_HEADER_TOKEN_KEY);
//                Claims claims = JwtUtil.parseJWT(authorization);
//                String json = claims.getSubject();
//                OrgActorDto actor = JSONObject.parseObject(json, OrgActorDto.class);
//
//                OrgLogLoginDto logDto = new OrgLogLoginDto();
//                logDto.setType(1);//类型0登录1登出
//                logDto.setIpAddr(IpUtil.getIpAddr(request));//请求的IP
//                logDto.setActorId(actor.getId());//id
//                logDto.setActorName(actor.getName());//员工名称
////            logDto.setDeviceMac(IpUtil.getMACAddress(logDto.getIpAddr()));//MAC地址
//                logLoginService.saveOrUpdateData(logDto);
//            } catch (Exception e) {
//            }
//            try {
//                request.getSession().invalidate();
//            } catch (Exception e) {
//            }
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
//        log.info("ActorController refreshToken.........");
//        Response result = new Response();
//        try {
//            String authorization = request.getHeader(CommonConstant.JWT_HEADER_TOKEN_KEY);
//            Claims claims = JwtUtil.parseJWT(authorization);
//
//            Map data = new HashMap<>();
//            String json = claims.getSubject();
//            OrgActorDto actor = JSONObject.parseObject(json, OrgActorDto.class);
//            String subject = JwtUtil.generalSubject(actor);
//            String refreshToken = JwtUtil.createJWT(CommonConstant.JWT_ID, subject, CommonConstant.JWT_TTL);
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