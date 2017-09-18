package com.hsd.actor.web.controller.user;

import com.hsd.account.actor.api.actor.IMemberService;
import com.hsd.account.actor.api.identity.IIdentityService;
import com.hsd.account.actor.api.user.IUserService;
import com.hsd.account.actor.dto.actor.MemberDto;
import com.hsd.account.actor.dto.identity.IdentityDto;
import com.hsd.account.actor.dto.user.UserDto;
import com.hsd.framework.Response;
import com.hsd.framework.util.JwtUtil;
import com.hsd.framework.util.ValidatorUtil;
import com.hsd.framework.web.controller.BaseController;
import com.hsd.util.api.msg.IPushService;
import com.hsd.util.dto.msg.PushDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

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
    private IIdentityService identityService;
    @Autowired
    private IMemberService memberService;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    /**获取当前登录客户的id*/
    private Long getCurrentUserId() throws Exception {
        return JwtUtil.getSubject(UserDto.class).getId();
    }
    @RequestMapping(method = RequestMethod.GET, value = acPrefix + "info")
    @ApiOperation(value = "获取信息")
   public Response info() {
        log.info("UserSettingController info.........");
        Response result = new Response();
        try {
           UserDto user=userService.findUserByAccount(JwtUtil.getSubject(UserDto.class).getAccount(), UserDto.userType.USER.getCode());
           UserDto userJon=new UserDto();
            userJon.setId(user.getId());
            userJon.setAccount(user.getAccount());
            if (ValidatorUtil.notEmpty(user.getCellphone())){
                userJon.setCellphone(user.getCellphone().replaceAll("(\\d{3})\\d{5}(\\d{3})", "$1****$2"));
                userJon.setIsCellphoneYN(1);
            }else{
                userJon.setIsCellphoneYN(0);
            }
            if (ValidatorUtil.notEmpty(user.getEmail())){
                userJon.setEmail(user.getEmail().substring(0, 1) + "****" + user.getEmail().substring(user.getEmail().indexOf("@") - 1, user.getEmail().length()));
                userJon.setIsEmailYN(1);
            }else{
                userJon.setIsEmailYN(0);
            }
            if(ValidatorUtil.notEmpty(user.getTradePwd())){
                userJon.setIsTradePwdYN(1);
            } else{
                userJon.setIsTradePwdYN(0);
            }
            IdentityDto identityDto=identityService.findDataById(new IdentityDto(){{setUserId(user.getId());}});
            if(identityDto!=null){
                userJon.setRealName(identityDto.getRealName());
                userJon.setCredentialNumber(identityDto.getCredentialNumber().substring(0, 3) + "***********" + identityDto.getCredentialNumber().substring(identityDto.getCredentialNumber().length()- 4, identityDto.getCredentialNumber().length()));
                userJon.setIsIdentityYN(1);
            }else{
                userJon.setIsIdentityYN(0);
            }
            userJon.setIsMemberPerfectYN(memberService.isDataYN(new MemberDto(){{setUserId(user.getId());}}));

            result.data = userJon;
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = acPrefix + "phone/bind/sms")
    @ApiOperation(value = "手机号绑定-短信验证码")
    public Response phoneBindSms(@RequestParam("cellphone") String cellphone,@RequestParam("imgCaptcha") String imgCaptcha) {
        log.info("UserSettingController phoneBindSms.........");
        Response result = new Response("success");
        try {
            if ( ValidatorUtil.isEmpty(cellphone) || ValidatorUtil.isEmpty(imgCaptcha)) return Response.error("参数获取异常!");
            if (!"ufgb".equals(imgCaptcha)) return Response.error("验证码不正确!");

            UserDto dto=new UserDto();
            dto.setId(getCurrentUserId());
            dto.setType(UserDto.userType.USER.getCode());
            dto = userService.findDataById(dto);
            if (dto == null) return Response.error("账号不存在!");
            dto.setCellphone(cellphone);
            UserDto finalDto = dto;
            pushService.captchaSms(new PushDto() {{
                setPrefix("user:setting:phone:captcha:bind:");
                setCellphone(finalDto.getCellphone());
            }});
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = acPrefix + "phone/verify/sms")
    @ApiOperation(value = "手机号验证-短信验证码")
    public Response phoneVerifySms(@RequestParam("imgCaptcha") String imgCaptcha) {
        log.info("UserSettingController phoneVerifySms.........");
        Response result = new Response("success");
        try {
            if (ValidatorUtil.isEmpty(imgCaptcha)) return Response.error("参数获取异常!");
            if (!"ufgb".equals(imgCaptcha)) return Response.error("验证码不正确!");
            UserDto dto=new UserDto();
            dto.setId(getCurrentUserId());
            dto.setType(UserDto.userType.USER.getCode());
            dto = userService.findDataById(dto);
            if (dto == null) return Response.error("账号不存在!");
            UserDto finalDto = dto;
            pushService.captchaSms(new PushDto() {{
                setPrefix("user:setting:phone:captcha:update:verify:");
                setCellphone(finalDto.getCellphone());
            }});
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = acPrefix + "phone/update/sms")
    @ApiOperation(value = "手机号修改-短信验证码")
    public Response phoneUpdateSms(@RequestParam("cellphone") String cellphone,@RequestParam("imgCaptcha") String imgCaptcha) {
        log.info("UserSettingController phoneUpdateSms.........");
        Response result = new Response("success");
        try {
            if ( ValidatorUtil.isEmpty(cellphone) || ValidatorUtil.isEmpty(imgCaptcha)) return Response.error("参数获取异常!");
            if (!"ufgb".equals(imgCaptcha)) return Response.error("验证码不正确!");
            pushService.captchaSms(new PushDto() {{
                setPrefix("user:setting:phone:captcha:update:");
                setCellphone(cellphone);
            }});
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = acPrefix + "phone/verify")
    @ApiOperation(value = "手机号-验证")
    public Response phoneVerify(@RequestParam("smsCaptcha") String smsCaptcha) {
        log.info("UserSettingController phoneBind.........");
        Response result = new Response("success");
        try {
            if ( ValidatorUtil.isEmpty(smsCaptcha)) return Response.error("参数获取异常!");
            Long userId= getCurrentUserId();
            UserDto dto=new UserDto();
            dto.setId(userId);
            dto.setType(UserDto.userType.USER.getCode());
            dto = userService.findDataById(dto);
            if (dto == null) return Response.error("账号不存在!");
            UserDto finalDto = dto;
            pushService.verifyCaptchaSms(new PushDto() {{
                setPrefix("user:setting:phone:captcha:update:verify:");
                setCellphone(finalDto.getCellphone());
                setCaptcha(smsCaptcha);
            }});
            redisTemplate.opsForValue().set("user:setting:phone:captcha:update:verify:"+userId,"1",30, TimeUnit.MINUTES);//校验成功状态 有效期30分钟
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = acPrefix + "phone/bind")
    @ApiOperation(value = "手机号-绑定")
    public Response phoneBind(@RequestParam("cellphone") String cellphone,@RequestParam("smsCaptcha") String smsCaptcha) {
        log.info("UserSettingController phoneBind.........");
        Response result = new Response("success");
        try {
            if ( ValidatorUtil.isEmpty(cellphone) || ValidatorUtil.isEmpty(smsCaptcha)) return Response.error("参数获取异常!");
            Long userId= getCurrentUserId();
            pushService.verifyCaptchaSms(new PushDto() {{
                setPrefix("user:setting:phone:captcha:bind:");
                setCellphone(cellphone);
                setCaptcha(smsCaptcha);
            }});
            UserDto dto=new UserDto();
            dto.setId(userId);
            dto.setType(UserDto.userType.USER.getCode());
            dto = userService.findDataById(dto);
            if (dto == null) return Response.error("账号不存在!");
            if(ValidatorUtil.notEmpty(dto.getCellphone()))  return Response.error("已绑定手机号,不能重复操作!");
            userService.phoneBind(new UserDto(){{setId(userId);setCellphone(cellphone);}});
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = acPrefix + "phone/update")
    @ApiOperation(value = "手机号-修改")
    public Response phoneUpdate(@RequestParam("cellphone") String cellphone,@RequestParam("smsCaptcha") String smsCaptcha) {
        log.info("UserSettingController phoneUpdate.........");
        Response result = new Response("success");
        try {
            if ( ValidatorUtil.isEmpty(cellphone) || ValidatorUtil.isEmpty(smsCaptcha)) return Response.error("参数获取异常!");
            Long userId= getCurrentUserId();
            String verifyFlag= (String) redisTemplate.opsForValue().get("user:setting:phone:captcha:update:verify:"+userId);
            if(verifyFlag==null || !"1".equals(verifyFlag))  return Response.error("原手机验证过期或未验证!");

            pushService.verifyCaptchaSms(new PushDto() {{
                setPrefix("user:setting:phone:captcha:update:");
                setCellphone(cellphone);
                setCaptcha(smsCaptcha);
            }});
            UserDto dto=new UserDto(){{setId(userId);setCellphone(cellphone);}};
            userService.phoneBind(dto);

            redisTemplate.opsForValue().getOperations().delete("user:setting:phone:captcha:update:verify:"+userId);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = acPrefix + "pwd/login/update")
    @ApiOperation(value = "登录密码-修改")
    public Response pwdLoginUpdate(@RequestParam("oldpwd") String oldpwd,@RequestParam("newpwd") String newpwd) {
        log.info("UserSettingController pwdLoginUpdate.........");
        Response result = new Response("success");
        try {
            if ( ValidatorUtil.isEmpty(oldpwd) || ValidatorUtil.isEmpty(newpwd)) return Response.error("参数获取异常!");
            userService.updatePwd(new UserDto(){{setId(getCurrentUserId());setPwd(oldpwd);setNewpwd(newpwd);}});
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = acPrefix + "identity")
    @ApiOperation(value = "实名认证")
    public Response identity(@ModelAttribute IdentityDto dto) {
        log.info("UserSettingController identity.........");
        Response result = new Response("success");
        try {
            if (dto==null || ValidatorUtil.isEmpty(dto.getRealName()) || ValidatorUtil.isEmpty(dto.getCredentialNumber()) || ValidatorUtil.isEmpty(dto.getImgCaptcha())) return Response.error("参数获取异常!");
            if (!"ufgb".equals(dto.getImgCaptcha())) return Response.error("验证码不正确!");
            Long userId= getCurrentUserId();
            dto.setUserId(userId);
            dto.setCreateId(userId);
            userService.identity(dto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = acPrefix + "pwd/trade/setting")
    @ApiOperation(value = "交易密码-设置")
    public Response pwdTradeSetting(@RequestParam("tradePwd") String tradePwd) {
        log.info("UserSettingController pwdTradeSetting.........");
        Response result = new Response("success");
        try {
            if ( ValidatorUtil.isEmpty(tradePwd)) return Response.error("参数获取异常!");
            userService.pwdTradeSetting(new UserDto(){{setId(getCurrentUserId());setTradePwd(tradePwd);}});
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = acPrefix + "pwd/trade/update")
    @ApiOperation(value = "交易密码-修改")
    public Response pwdTradeUpdate(@RequestParam("oldTradePwd") String oldTradePwd, @RequestParam("newTradePwd") String newTradePwd) {
        log.info("UserSettingController pwdTradeUpdate.........");
        Response result = new Response("success");
        try {
            if ( ValidatorUtil.isEmpty(oldTradePwd) || ValidatorUtil.isEmpty(newTradePwd)) return Response.error("参数获取异常!");
            userService.pwdTradeUpdate(new UserDto(){{setId(getCurrentUserId());setTradePwd(oldTradePwd);setNewpwd(newTradePwd);}});
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = acPrefix + "pwd/trade/reset/verify/sms")
    @ApiOperation(value = "找回交易密码-验证短信验证码-推送")
    public Response pwdTradeResetVerifySms(@RequestParam("imgCaptcha") String imgCaptcha) {
        log.info("UserSettingController pwdTradeResetVerifySms.........");
        Response result = new Response("success");
        try {
            if (ValidatorUtil.isEmpty(imgCaptcha)) return Response.error("参数获取异常!");
            if (!"ufgb".equals(imgCaptcha)) return Response.error("验证码不正确!");
            Long userId= getCurrentUserId();
            UserDto dto=new UserDto();
            dto.setId(userId);
            dto.setType(UserDto.userType.USER.getCode());
            dto = userService.findDataById(dto);
            if (dto == null) return Response.error("账号不存在!");

            UserDto finalDto = dto;
            pushService.captchaSms(new PushDto() {{
                setPrefix("user:setting:trade:reset:captcha:verify:");
                setCellphone(finalDto.getCellphone());
            }});
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = acPrefix + "pwd/trade/reset/verify")
    @ApiOperation(value = "找回交易密码-验证短信验证码-验证")
    public Response pwdTradeResetVerify(@RequestParam("credentialNumber") String credentialNumber,@RequestParam("smsCaptcha") String smsCaptcha) {
        log.info("UserSettingController pwdTradeResetVerify.........");
        Response result = new Response("success");
        try {
            if ( ValidatorUtil.isEmpty(credentialNumber) || ValidatorUtil.isEmpty(smsCaptcha)) return Response.error("参数获取异常!");
            Long userId= getCurrentUserId();

            UserDto userDto= userService.findDataById(new UserDto(){{setId(userId);}});
            pushService.verifyCaptchaSms(new PushDto() {{
                setPrefix("user:setting:trade:reset:captcha:verify:");
                setCellphone(userDto.getCellphone());
                setCaptcha(smsCaptcha);
            }});

            IdentityDto identityDto=identityService.findDataById(new IdentityDto(){{setUserId(userId);}});
            if(identityDto==null)  return Response.error("请先进行实名认证!");
            if(!(""+credentialNumber).equals(identityDto.getCredentialNumber()))  return Response.error("请输入正确的身份证号!");

            redisTemplate.opsForValue().set("user:setting:trade:reset:captcha:setting:"+userId,"1",30, TimeUnit.MINUTES);//校验成功状态 有效期30分钟
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = acPrefix + "pwd/trade/reset/setting")
    @ApiOperation(value = "找回交易密码-密码重置")
    public Response pwdTradeResetSetting(@RequestParam("tradePwd") String tradePwd) {
        log.info("UserSettingController pwdTradeResetSetting.........");
        Response result = new Response("success");
        try {
            if ( ValidatorUtil.isEmpty(tradePwd)) return Response.error("参数获取异常!");
            Long userId= getCurrentUserId();
            String verifyFlag= (String) redisTemplate.opsForValue().get("user:setting:trade:reset:captcha:setting:"+userId);
            if(verifyFlag==null || !"1".equals(verifyFlag))  return Response.error("找回交易密码-前置验证未通过!");
            userService.pwdTradeResetSetting(new UserDto(){{setId(userId);setTradePwd(tradePwd);}});

            redisTemplate.opsForValue().getOperations().delete("user:setting:trade:reset:captcha:setting:"+userId);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = acPrefix + "email/bind")
    @ApiOperation(value = "邮箱绑定")
    public Response emailBind(@RequestParam("email") String email,@RequestParam("imgCaptcha") String imgCaptcha) {
        log.info("UserSettingController emailBind.........");
        Response result = new Response("success");
        try {
            if (ValidatorUtil.isEmpty(email) || ValidatorUtil.isEmpty(imgCaptcha)) return Response.error("参数获取异常!");
            Long userId= getCurrentUserId();
            UserDto userDto= userService.findDataById(new UserDto(){{setId(userId);}});
            if (!"ufgb".equals(imgCaptcha)) return Response.error("验证码不正确!");
            userDto.setEmail(email);
            userService.emailBind(userDto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = acPrefix + "email/verify")
    @ApiOperation(value = "邮箱验证")
    public Response emailVerify(@RequestParam("imgCaptcha") String imgCaptcha) {
        log.info("UserSettingController emailVerify.........");
        Response result = new Response("success");
        try {
            if (ValidatorUtil.isEmpty(imgCaptcha)) return Response.error("参数获取异常!");
            if (!"ufgb".equals(imgCaptcha)) return Response.error("验证码不正确!");
            Long userId= getCurrentUserId();
            UserDto userDto= userService.findDataById(new UserDto(){{setId(userId);}});
            pushService.captchaEmail(new PushDto() {{
                setPrefix("user:setting:email:verify:");
                setEmail(userDto.getEmail());
            }});
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, value = acPrefix + "email/update")
    @ApiOperation(value = "邮箱修改")
    public Response emailUpdate(@RequestParam("email") String email,@RequestParam("emailCaptcha") String emailCaptcha) {
        log.info("UserSettingController emailUpdate.........");
        Response result = new Response("success");
        try {
            if (ValidatorUtil.isEmpty(email) || ValidatorUtil.isEmpty(emailCaptcha)) return Response.error("参数获取异常!");
            Long userId= getCurrentUserId();
            UserDto userDto= userService.findDataById(new UserDto(){{setId(userId);}});
            pushService.verifyCaptchaEmail(new PushDto() {{
                setPrefix("user:setting:email:verify:");
                setEmail(userDto.getEmail());
                setCaptcha(emailCaptcha);
            }});
            userDto.setEmail(email);
            userService.emailBind(userDto);
        } catch (Exception e) {
            result = Response.error(e.getMessage());
        }
        return result;
    }
}