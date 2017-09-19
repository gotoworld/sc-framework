package com.hsd.util.api.msg;

import com.github.pagehelper.PageInfo;
import com.hsd.framework.config.FeignConfiguration;
import com.hsd.util.dto.msg.MsgSmsDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * <p>短信推送 业务处理接口类。
 */
@FeignClient(value = "${feign.name.util.sms}",configuration = FeignConfiguration.class)//, fallback = TestServiceHystrix.class)
public interface IMsgSmsService {
    String acPrefix = "/feign/util/IMsgSmsService";

    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsPage")
    public PageInfo findDataIsPage(MsgSmsDto dto) throws Exception;

    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataById")
    public MsgSmsDto findDataById(MsgSmsDto dto) throws Exception;
}