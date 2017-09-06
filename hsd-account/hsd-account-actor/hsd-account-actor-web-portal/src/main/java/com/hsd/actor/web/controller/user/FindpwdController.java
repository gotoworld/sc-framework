package com.hsd.actor.web.controller.user;

import com.hsd.account.actor.api.IPushService;
import com.hsd.account.actor.api.user.IUserService;
import com.hsd.account.actor.dto.PushDto;
import com.hsd.account.actor.dto.user.UserDto;
import com.hsd.framework.Response;
import com.hsd.framework.annotation.NoAuthorize;
import com.hsd.framework.util.ValidatorUtil;
import com.hsd.framework.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
    private IPushService pushService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private static final String acPrefix = "/api/account/actor/findpwd/";

    private static final String findPwdStatePreix = "captcha:find:pwd:success:";
    private static final String captchaPreix = "captcha:find:pwd:";

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = acPrefix + "getAccount")
    @ApiOperation(value = "获取账户")
    public Response getAccount(@ModelAttribute UserDto dto) {
        log.info("FindpwdController getAccount.........");
        Response result = new Response("success");
        try {
            if (dto == null || ValidatorUtil.isEmpty(dto.getAccount()) || ValidatorUtil.isEmpty(dto.getCaptcha()))
                return Response.error("参数获取异常!");
            //验证码确认
            if (!"ufgb".equals(dto.getCaptcha())) return Response.error("验证码不正确!");
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
    public Response captchaSms(@RequestParam("accid") Long accid, @RequestParam("captcha") String captcha) throws Exception {
        log.info("LoginController captchaSms");
        Response result = new Response();
        try {
            if (accid==null || ValidatorUtil.isNullEmpty(captcha)) {
                return Response.error("参数异常!");
            }
            //验证码确认
            if (!"ufgb".equals(captcha)) return Response.error("验证码不正确!");
            UserDto userDto = userService.findDataById(new UserDto(){{setId(accid);}});
            if(userDto==null) return Response.error("账号不存在!");
            result = pushService.captchaSms(new PushDto(){{setPrefix(captchaPreix);setCellphone(userDto.getCellphone());}});
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
    public Response captchaEmail(@RequestParam("accid") Long accid, @RequestParam("captcha") String captcha) throws Exception {
        log.info("LoginController captchaEmail");
        Response result = new Response();
        try {
            if (accid==null || ValidatorUtil.isNullEmpty(captcha)) {
                return Response.error("参数异常!");
            }
            //验证码确认
            if (!"ufgb".equals(captcha)) return Response.error("验证码不正确!");
            UserDto userDto = userService.findDataById(new UserDto(){{setId(accid);}});
            if(userDto==null) return Response.error("账号不存在!");
            result = pushService.captchaEmail(new PushDto(){{setPrefix(captchaPreix);setEmail(userDto.getEmail());}});
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = acPrefix + "verifyCaptchaSms")
    @ApiOperation(value = "验证码校验-短信")
    public Response verifyCaptchaSms(@ModelAttribute UserDto dto) {
        log.info("FindpwdController verifyCaptchaSms.........");
        Response result = new Response("success");
        try {
            if (dto == null || (dto.getId()==null) || ValidatorUtil.isEmpty(dto.getCaptcha()))
                return Response.error("获取异常!");
            String captcha=dto.getCaptcha();
            dto.setType(UserDto.userType.USER.getCode());
            dto = userService.findDataById(dto);
            if (dto == null) return Response.error("账号不存在!");
            UserDto finalDto = dto;
            pushService.verifyCaptchaSms(new PushDto() {{
                setPrefix(captchaPreix);
                setCellphone(finalDto.getCellphone());
                setCaptcha(captcha);
            }});

            redisTemplate.opsForValue().set(findPwdStatePreix + finalDto.getId(), "1", 30, TimeUnit.MINUTES);//校验成功状态 有效期5分钟
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = acPrefix + "verifyCaptchaEmail")
    @ApiOperation(value = "验证码校验-邮件")
    public Response verifyCaptchaEmail(@ModelAttribute UserDto dto) {
        log.info("FindpwdController verifyCaptchaEmail.........");
        Response result = new Response("success");
        try {
            if (dto == null || ValidatorUtil.isEmpty(dto.getAccount()) || ValidatorUtil.isEmpty(dto.getCaptcha()))
                return Response.error("参数获取异常!");
            String captcha=dto.getCaptcha();
            dto.setType(UserDto.userType.USER.getCode());
            dto = userService.findDataById(dto);
            if (dto == null) return Response.error("账号不存在!");
            UserDto finalDto = dto;
            pushService.verifyCaptchaEmail(new PushDto() {{
                setPrefix(captchaPreix);
                setEmail(finalDto.getEmail());
                setCaptcha(captcha);
            }});
            redisTemplate.opsForValue().set(findPwdStatePreix + finalDto.getId(), "1", 30, TimeUnit.MINUTES);//校验成功状态 有效期30分钟
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = acPrefix + "verifystate")
    @ApiOperation(value = "状态验证-找回密码前置条件已通过")
    public Response verifystate(@RequestParam("accid") Long accid) {
        log.info("FindpwdController verifystate.........");
        Response result = new Response("success");
        try {
            if (accid == null ) return Response.error("参数获取异常!");
            if(!"1".equals(redisTemplate.opsForValue().get(findPwdStatePreix + accid)))  return Response.error("身份验证未通过或已过期,请先进行身份验证!");
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = acPrefix + "update")
    @ApiOperation(value = "密码修改")
    public Response update(@ModelAttribute UserDto dto) {
        log.info("FindpwdController update.........");
        Response result = new Response("success");
        try {
            if (dto == null || dto.getId()==null || ValidatorUtil.isEmpty(dto.getPwd())) return Response.error("参数获取异常!");
            if(!"1".equals(redisTemplate.opsForValue().get(findPwdStatePreix + dto.getId())))  return Response.error("身份验证未通过或已过期,请先进行身份验证!");
            result=userService.restPwd(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}