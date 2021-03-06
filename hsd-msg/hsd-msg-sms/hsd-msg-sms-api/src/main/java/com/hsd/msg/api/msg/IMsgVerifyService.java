package com.hsd.msg.api.msg;

import com.github.pagehelper.PageInfo;
import com.hsd.framework.Response;
import com.hsd.framework.config.FeignConfiguration;
import com.hsd.msg.dto.msg.MsgVerifyDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * <p>信息验证 业务处理接口类。
 */
@FeignClient(value = "${feign.name.msg.sms}",configuration = FeignConfiguration.class)//, fallback = TestServiceHystrix.class)
public interface IMsgVerifyService {
    String acPrefix = "/feign/util/IMsgVerifyService";
    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsPage")
    PageInfo findDataIsPage(MsgVerifyDto dto) throws Exception;
    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataById")
    MsgVerifyDto findDataById(MsgVerifyDto dto) throws Exception;

    /**
     * <p>图片认证码-验证
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/verifyImgCode")
    boolean verifyImgCode(MsgVerifyDto dto) throws Exception;
    /**
     * <p>验证码-推送-短信/邮件
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/pushVerifyCode")
    Response pushVerifyCode(MsgVerifyDto dto) throws Exception;
    /**
     * <p>验证码-校验-短信/邮件
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/checkVerifyCode")
    Response checkVerifyCode(MsgVerifyDto dto) throws Exception;
}