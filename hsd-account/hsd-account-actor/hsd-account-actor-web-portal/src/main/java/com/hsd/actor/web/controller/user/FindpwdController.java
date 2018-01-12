package com.hsd.actor.web.controller.user;

import com.hsd.account.actor.api.user.IUserService;
import com.hsd.account.actor.dto.user.UserDto;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.NoAuthorize;
import com.hsd.framework.cache.util.RedisHelper;
import com.hsd.framework.util.ValidatorUtil;
import com.hsd.framework.web.controller.BaseController;
import com.hsd.msg.api.msg.IMsgVerifyService;
import com.hsd.msg.dto.msg.MsgVerifyDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@Api(description = "找回密码")
@RestController
@Slf4j
@NoAuthorize
public class FindpwdController extends BaseController {
    private static final long serialVersionUID = -528422099490438672L;
    @Autowired
    private IUserService userService;
    @Autowired
    private IMsgVerifyService msgVerifyService;
    @Autowired
    private RedisHelper redisHelper;
    private static final String acPrefix = "/api/account/actor/findpwd/";

    private static final String findPwdStatePreix = "captcha:find:pwd:success:";

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = acPrefix + "getAccount")
    @ApiOperation(value = "获取账户")
    public Response getAccount(@ModelAttribute UserDto dto) {
        log.info("FindpwdController getAccount.........");
        Response result = new Response(0, "success");
        try {
            if (dto == null || ValidatorUtil.isEmpty(dto.getAccount()) || ValidatorUtil.isEmpty(dto.getImgCaptchaCode())) return Response.error("参数有误!");

            UserDto finalDto = dto;
            MsgVerifyDto verifyDto = new MsgVerifyDto() {{
                setImgCaptchaId(finalDto.getImgCaptchaId());
                setImgCaptchaCode(finalDto.getImgCaptchaCode());
            }};
            msgVerifyService.verifyImgCode(verifyDto);

            dto.setType(UserDto.userType.USER.getCode());
            dto = userService.getAccount(dto);
            if (dto == null) return Response.error("账号不存在!");
            if (ValidatorUtil.notEmpty(dto.getCellphone()))
                dto.setCellphone(dto.getCellphone().replaceAll("(\\d{3})\\d{5}(\\d{3})", "$1****$2"));
            if (ValidatorUtil.notEmpty(dto.getEmail()))
                dto.setEmail(dto.getEmail().substring(0, 1) + "****" + dto.getEmail().substring(dto.getEmail().indexOf("@") - 1, dto.getEmail().length()));
            result.data = dto;
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * <p>验证码-短信推送
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "/send/captcha/sms")
    @ApiOperation(value = "验证码-短信推送")
    public Response captchaSms(@RequestParam("accid") Long accid, @RequestParam("imgCaptchaId") String imgCaptchaId, @RequestParam("imgCaptchaCode") String imgCaptchaCode) {
        log.info("LoginController captchaSms");
        Response result = new Response(0, "success");
        try {
            if (accid==null || ValidatorUtil.isNullEmpty(imgCaptchaId) || ValidatorUtil.isNullEmpty(imgCaptchaCode)) {
                return Response.error("参数有误!");
            }
            MsgVerifyDto verifyDto = new MsgVerifyDto() {{
                setImgCaptchaId(imgCaptchaId);
                setImgCaptchaCode(imgCaptchaCode);
            }};
            msgVerifyService.verifyImgCode(verifyDto);

            UserDto userDto = userService.findDataById(new UserDto(){{setId(accid);}});
            if(userDto==null) return Response.error("账号不存在!");

            verifyDto.setSmsType(0);
            verifyDto.setIpAddress(getIpAddr());
            verifyDto.setSmsAddress(userDto.getCellphone());
            result = msgVerifyService.pushVerifyCode(verifyDto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    /**
     * <p>验证码-邮件推送
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = acPrefix + "/send/captcha/email")
    @ApiOperation(value = "验证码-邮件推送")
    public Response captchaEmail(@RequestParam("accid") Long accid) {
        log.info("LoginController captchaEmail");
        Response result = new Response(0, "success");
        try {
            if (accid==null) {
                return Response.error("参数有误!");
            }
            MsgVerifyDto verifyDto = new MsgVerifyDto();// {{
//                setImgCaptchaId(imgCaptchaId);
//                setImgCaptchaCode(imgCaptchaCode);
//            }};
//            msgVerifyService.verifyImgCode(verifyDto);

            UserDto userDto = userService.findDataById(new UserDto(){{setId(accid);}});
            if(userDto==null) return Response.error("账号不存在!");

            verifyDto.setSmsType(1);
            verifyDto.setIpAddress(getIpAddr());
            verifyDto.setSmsAddress(userDto.getEmail());
            result = msgVerifyService.pushVerifyCode(verifyDto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = acPrefix + "verifyCaptchaSms")
    @ApiOperation(value = "验证码校验-短信")
    public Response verifyCaptchaSms(@ModelAttribute UserDto dto) {
        log.info("FindpwdController verifyCaptchaSms.........");
        Response result = new Response(0, "success");
        try {
            if (dto == null || (dto.getId()==null) || ValidatorUtil.isEmpty(dto.getCaptcha()))
                return Response.error("参数有误!");
            String captcha=dto.getCaptcha();
            dto.setType(UserDto.userType.USER.getCode());
            dto = userService.findDataById(dto);
            if (dto == null) return Response.error("账号不存在!");
            UserDto finalDto = dto;

            MsgVerifyDto verifyDto = new MsgVerifyDto();
            verifyDto.setSmsType(0);
            verifyDto.setSmsAddress(finalDto.getCellphone());
            verifyDto.setVerifyCode(captcha);
            result.data = msgVerifyService.checkVerifyCode(verifyDto);

            redisHelper.set(findPwdStatePreix + finalDto.getId(), "1", 5, TimeUnit.MINUTES);//校验成功状态 有效期5分钟
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = acPrefix + "verifyCaptchaEmail")
    @ApiOperation(value = "验证码校验-邮件")
    public Response verifyCaptchaEmail(@ModelAttribute UserDto dto) {
        log.info("FindpwdController verifyCaptchaEmail.........");
        Response result = new Response(0, "success");
        try {
            if (dto == null || ValidatorUtil.isEmpty(dto.getAccount()) || ValidatorUtil.isEmpty(dto.getCaptcha()))
                return Response.error("参数有误!");
            String captcha=dto.getCaptcha();
            dto.setType(UserDto.userType.USER.getCode());
            dto = userService.findDataById(dto);
            if (dto == null) return Response.error("账号不存在!");
            UserDto finalDto = dto;

            MsgVerifyDto verifyDto = new MsgVerifyDto();
            verifyDto.setSmsType(1);
            verifyDto.setSmsAddress(finalDto.getEmail());
            verifyDto.setVerifyCode(captcha);
            result.data = msgVerifyService.checkVerifyCode(verifyDto);

            redisHelper.set(findPwdStatePreix + finalDto.getId(), "1", 5, TimeUnit.MINUTES);//校验成功状态 有效期5分钟
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = acPrefix + "verifystate")
    @ApiOperation(value = "状态验证-找回密码前置条件已通过")
    public Response verifystate(@RequestParam("accid") Long accid) {
        log.info("FindpwdController verifystate.........");
        Response result = new Response(0, "success");
        try {
            if (accid == null ) return Response.error("参数有误!");
            if(!"1".equals(redisHelper.get(findPwdStatePreix + accid)))  return Response.error("身份验证未通过或已过期,请先进行身份验证!");
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = acPrefix + "restPwd")
    @ApiOperation(value = "密码修改")
    public Response restPwd(@ModelAttribute UserDto dto) {
        log.info("FindpwdController restPwd.........");
        Response result = new Response(0, "success");
        try {
            if (dto == null || dto.getId()==null || ValidatorUtil.isEmpty(dto.getPwd())) return Response.error("参数有误!");
            if(!"1".equals(redisHelper.get(findPwdStatePreix + dto.getId())))  return Response.error("身份验证未通过或已过期,请先进行身份验证!");
            result=userService.restPwd(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}