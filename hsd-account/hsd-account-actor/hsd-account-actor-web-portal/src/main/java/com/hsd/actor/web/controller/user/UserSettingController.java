package com.hsd.actor.web.controller.user;

import com.hsd.account.actor.api.IPushService;
import com.hsd.account.actor.api.user.IUserService;
import com.hsd.account.actor.dto.PushDto;
import com.hsd.account.actor.dto.user.UserDto;
import com.hsd.framework.Response;
import com.hsd.framework.util.ValidatorUtil;
import com.hsd.framework.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Api(description = "客户设置")
@RestController
@Slf4j
public class UserSettingController extends BaseController {
    private static final String acPrefix = "/api/account/actor/user/setting/";
    @Autowired
    private IUserService userService;
    @Autowired
    private IPushService pushService;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
//    /**
//     * <p> 信息详情。
//     */
//    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info/{id}")
//    @ApiOperation(value = "信息详情")
//   public Response info(@PathVariable("id") Long id) {
//        log.info("UserController info.........");
//        Response result = new Response();
//        try {
//            if(id==null) throw new RuntimeException("参数异常!");
//            UserDto dto = new UserDto(){{setId(id);}};
//            result.data = userService.findDataById(dto);
//        } catch (Exception e) {
//            result = Response.error(e.getMessage());
//        }
//        return result;
//    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = acPrefix + "phone/bind/sms")
    @ApiOperation(value = "手机号绑定-短信验证码")
    public Response phoneBindSms(@RequestParam("accid") Long accid,@RequestParam("cellphone") String cellphone,@RequestParam("imgCaptcha") String imgCaptcha) {
        log.info("UserSettingController phoneBindSms.........");
        Response result = new Response("success");
        try {
            if (accid == null || ValidatorUtil.isEmpty(cellphone) || ValidatorUtil.isEmpty(imgCaptcha)) return Response.error("参数获取异常!");
            if (!"ufgb".equals(imgCaptcha)) return Response.error("验证码不正确!");
            UserDto dto=new UserDto();
            dto.setId(accid);
            dto.setType(UserDto.userType.USER.getCode());
            dto = userService.findDataById(dto);
            if (dto == null) return Response.error("账号不存在!");
            dto.setCellphone(cellphone);
            UserDto finalDto = dto;
            pushService.captchaSms(new PushDto() {{
                setPrefix("user:setting:phone:captcha:bind:");
                setCellphone(finalDto.getCellphone());
                setCaptcha(imgCaptcha);
            }});
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = acPrefix + "phone/verify/sms")
    @ApiOperation(value = "手机号验证-短信验证码")
    public Response phoneVerifySms(@RequestParam("accid") Long accid,@RequestParam("imgCaptcha") String imgCaptcha) {
        log.info("UserSettingController phoneVerifySms.........");
        Response result = new Response("success");
        try {
            if (accid == null || ValidatorUtil.isEmpty(imgCaptcha)) return Response.error("参数获取异常!");
            if (!"ufgb".equals(imgCaptcha)) return Response.error("验证码不正确!");
            UserDto dto=new UserDto();
            dto.setId(accid);
            dto.setType(UserDto.userType.USER.getCode());
            dto = userService.findDataById(dto);
            if (dto == null) return Response.error("账号不存在!");
            UserDto finalDto = dto;
            pushService.captchaSms(new PushDto() {{
                setPrefix("user:setting:phone:captcha:update:verify:");
                setCellphone(finalDto.getCellphone());
                setCaptcha(imgCaptcha);
            }});
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = acPrefix + "phone/update/sms")
    @ApiOperation(value = "手机号修改-短信验证码")
    public Response phoneUpdateSms(@RequestParam("accid") Long accid,@RequestParam("cellphone") String cellphone,@RequestParam("imgCaptcha") String imgCaptcha) {
        log.info("UserSettingController phoneUpdateSms.........");
        Response result = new Response("success");
        try {
            if (accid == null || ValidatorUtil.isEmpty(cellphone) || ValidatorUtil.isEmpty(imgCaptcha)) return Response.error("参数获取异常!");
            if (!"ufgb".equals(imgCaptcha)) return Response.error("验证码不正确!");
            pushService.captchaSms(new PushDto() {{
                setPrefix("user:setting:phone:captcha:update:");
                setCellphone(cellphone);
                setCaptcha(imgCaptcha);
            }});
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = acPrefix + "phone/verify")
    @ApiOperation(value = "手机号-验证")
    public Response phoneVerify(@RequestParam("accid") Long accid,@RequestParam("smsCaptcha") String smsCaptcha) {
        log.info("UserSettingController phoneBind.........");
        Response result = new Response("success");
        try {
            if (accid == null || ValidatorUtil.isEmpty(smsCaptcha)) return Response.error("参数获取异常!");
            UserDto dto=new UserDto();
            dto.setId(accid);
            dto.setType(UserDto.userType.USER.getCode());
            dto = userService.findDataById(dto);
            if (dto == null) return Response.error("账号不存在!");
            UserDto finalDto = dto;
            pushService.verifyCaptchaSms(new PushDto() {{
                setPrefix("user:setting:phone:captcha:update:verify:");
                setCellphone(finalDto.getCellphone());
                setCaptcha(smsCaptcha);
            }});
            redisTemplate.opsForValue().set("user:setting:phone:captcha:update:verify:"+accid,"1",30, TimeUnit.MINUTES);//校验成功状态 有效期30分钟
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = acPrefix + "phone/bind")
    @ApiOperation(value = "手机号-绑定")
    public Response phoneBind(@RequestParam("accid") Long accid,@RequestParam("cellphone") String cellphone,@RequestParam("smsCaptcha") String smsCaptcha) {
        log.info("UserSettingController phoneBind.........");
        Response result = new Response("success");
        try {
            if (accid == null || ValidatorUtil.isEmpty(cellphone) || ValidatorUtil.isEmpty(smsCaptcha)) return Response.error("参数获取异常!");
            pushService.verifyCaptchaSms(new PushDto() {{
                setPrefix("user:setting:phone:captcha:bind:");
                setCellphone(cellphone);
                setCaptcha(smsCaptcha);
            }});
            UserDto dto=new UserDto();
            dto.setId(accid);
            dto.setType(UserDto.userType.USER.getCode());
            dto = userService.findDataById(dto);
            if (dto == null) return Response.error("账号不存在!");
            if(ValidatorUtil.notEmpty(dto.getCellphone()))  return Response.error("已绑定手机号,不能重新绑定!");
            userService.phoneBind(new UserDto(){{setId(accid);setCellphone(cellphone);}});
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = acPrefix + "phone/update")
    @ApiOperation(value = "手机号-修改")
    public Response phoneUpdate(@RequestParam("accid") Long accid,@RequestParam("cellphone") String cellphone,@RequestParam("smsCaptcha") String smsCaptcha) {
        log.info("UserSettingController phoneUpdate.........");
        Response result = new Response("success");
        try {
            if (accid == null || ValidatorUtil.isEmpty(cellphone) || ValidatorUtil.isEmpty(smsCaptcha)) return Response.error("参数获取异常!");

            if(!"1".equals(redisTemplate.opsForValue().get("user:setting:phone:captcha:update:verify:"+accid)))  return Response.error("原手机验证过期或未验证!");

            pushService.verifyCaptchaSms(new PushDto() {{
                setPrefix("user:setting:phone:captcha:update:");
                setCellphone(cellphone);
                setCaptcha(smsCaptcha);
            }});
            UserDto dto=new UserDto(){{setId(accid);setCellphone(cellphone);}};
            userService.phoneBind(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = acPrefix + "pwd/login/update")
    @ApiOperation(value = "登录密码-修改")
    public Response pwdLoginUpdate(@RequestParam("accid") Long accid,@RequestParam("oldpwd") String oldpwd,@RequestParam("newpwd") String newpwd) {
        log.info("UserSettingController pwdLoginUpdate.........");
        Response result = new Response("success");
        try {
            if (accid == null || ValidatorUtil.isEmpty(oldpwd) || ValidatorUtil.isEmpty(newpwd)) return Response.error("参数获取异常!");
            userService.updatePwd(new UserDto(){{setId(accid);setPwd(oldpwd);setNewpwd(newpwd);}});
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = acPrefix + "pwd/trade/update")
    @ApiOperation(value = "交易密码-修改")
    public Response pwdTradeUpdate(@RequestParam("accid") Long accid,@RequestParam("oldpwd") String oldpwd,@RequestParam("newpwd") String newpwd) {
        log.info("UserSettingController pwdTradeUpdate.........");
        Response result = new Response("success");
        try {
            if (accid == null || ValidatorUtil.isEmpty(oldpwd) || ValidatorUtil.isEmpty(newpwd)) return Response.error("参数获取异常!");
            userService.updatePwd(new UserDto(){{setId(accid);setPwd(oldpwd);setNewpwd(newpwd);}});
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

}