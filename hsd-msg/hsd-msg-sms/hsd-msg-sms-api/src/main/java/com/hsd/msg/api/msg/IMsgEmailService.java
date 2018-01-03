package com.hsd.msg.api.msg;

import com.github.pagehelper.PageInfo;
import com.hsd.framework.config.FeignConfiguration;
import com.hsd.msg.dto.msg.MsgEmailDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * <p>邮件推送 业务处理接口类。
 */
@FeignClient(value = "${feign.name.msg.sms}",configuration = FeignConfiguration.class)//, fallback = TestServiceHystrix.class)
public interface IMsgEmailService {
    String acPrefix = "/feign/util/IMsgEmailService";
    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsPage")
    PageInfo findDataIsPage(MsgEmailDto dto) throws Exception;

    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataById")
    MsgEmailDto findDataById(MsgEmailDto dto) throws Exception;
}