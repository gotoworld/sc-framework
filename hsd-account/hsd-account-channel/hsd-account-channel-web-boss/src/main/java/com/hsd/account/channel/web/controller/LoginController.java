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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hsd.account.channel.api.channel.IChannelInfoService;
import com.hsd.account.channel.dto.channel.ChannelInfoDto;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.NoAuthorize;
import com.hsd.framework.security.MD5;
import com.hsd.framework.util.ValidatorUtil;
import com.hsd.framework.web.controller.BaseController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

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
//    private IChannelInfoService channelInfoService;
//    /**
//     * <p>用户登录
//     */
//    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix+"/login")
//    @ApiOperation(value = "登录")
//    public Response login(@RequestParam("account") String account , @RequestParam("pwd")String pwd ) throws Exception {
//        log.info("LoginController login");
//        Response result = new Response();
//            try {
//                if (ValidatorUtil.isNullEmpty(account) || ValidatorUtil.isNullEmpty(pwd)) {
//                    return Response.error("用户名或密码不能为空!");
//                }
//                ChannelInfoDto channelInfoUser = channelInfoService.findUserByAccount(account);
//                String pwdhex = MD5.pwdMd5Hex(pwd);
//                if (channelInfoUser==null || !(account.equals(channelInfoUser.getAccount()) && pwdhex.equals(channelInfoUser.getPwd()))) {
//                    return Response.error("登录失败,用户名或密码错误!");
//                }
//
//
//            }catch (Exception ex) {
//                log.error("登录失败,原因未知", ex);
//                result = Response.error("登录失败,服务器异常3!");
//            }
//        return result;
//    }
//    /**
//     * <p>渠道商登出
//     */
//    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "logout")
//    @ApiOperation(value = "登出")
//    public Response logout() {
//        log.info("channelController logout.........");
//        Response result = new Response();
//        try {
//            log.debug("准备退出!");
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
////    /**
////     * <p> 刷新token。
////     */
////    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "refreshToken")
////    @ApiOperation(value = "刷新token")
////    public Response refreshToken() {
////        log.info("StaffController refreshToken.........");
////        Response result = new Response();
////        try {
////            String authorization = request.getHeader(CommonConstant.JWT_HEADER_TOKEN_KEY);
////            Claims claims = JwtUtil.parseJWT(authorization);
////
////            Map data = new HashMap<>();
////            String json = claims.getSubject();
////            OrgStaffDto staff = JSONObject.parseObject(json, OrgStaffDto.class);
////            String subject = JwtUtil.generalSubject(staff);
////            String refreshToken = JwtUtil.createJWT(CommonConstant.JWT_ID, subject, CommonConstant.JWT_TTL,(String) getAuth().getSession().getId());
////
////            data.put("tokenExpMillis", System.currentTimeMillis() + CommonConstant.JWT_TTL_REFRESH);
////            data.put("authorizationToken", refreshToken);
////            result.data = data;
////        } catch (Exception e) {
////            result = Response.error(e.getMessage());
////        }
////        return result;
////    }
//    /**
//     * @param id
//     * @param pwd
//     * 修改密码
//     */
//    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix+"/modifyPwd")
//    @ApiOperation(value = "修改密码")
//    public Response modifyPwd(@RequestParam("id") Long id,@RequestParam("pwd") String pwd , @RequestParam("newpwd")String newpwd) throws Exception{
//    	log.info("channelController modifyPwd ........");
//    	Response result = new Response();
//    	ChannelInfoDto dto = new ChannelInfoDto();
//		dto.setId(id);
//    	try {
//			if (ValidatorUtil.isNullEmpty(newpwd)) {return Response.error("密码不能为空！");}
//			ChannelInfoDto channelInfo = channelInfoService.findDataById(dto);
//			if (MD5.pwdMd5Hex(pwd).equals(channelInfo.getPwd())) {return Response.error("旧密码错误！");}
//			dto.setPwd(newpwd);
//			channelInfoService.updataChannel(dto);
//		} catch (Exception e) {
//			result = Response.error(e.getMessage());
//		}
//		return result;
//
//    }
}
