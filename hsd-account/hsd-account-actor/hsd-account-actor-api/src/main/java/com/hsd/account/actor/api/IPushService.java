package com.hsd.account.actor.api;

import com.hsd.account.actor.dto.PushDto;
import com.hsd.framework.Response;
import com.hsd.framework.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>消息推送 业务处理接口类。
 */
@FeignClient(value = "${feign.name.account.actor}",configuration = FeignConfiguration.class)//, fallback = TestServiceHystrix.class)
public interface IPushService {
    String acPrefix = "/feign/account/actor/IPushService";
    /**
     * <p>验证码-短信推送。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/captchaSms")
    public Response captchaSms(PushDto dto) throws Exception;
    /**
     * <p>验证码-邮件推送。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/captchaEmail")
    public Response captchaEmail(PushDto dto) throws Exception;
    /**
     * <p>验证码-短信校验。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/verifyCaptchaSms")
    public Response verifyCaptchaSms(PushDto dto) throws Exception;
    /**
     * <p>验证码-邮件校验。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/verifyCaptchaEmail")
    public Response verifyCaptchaEmail(PushDto dto) throws Exception;
}