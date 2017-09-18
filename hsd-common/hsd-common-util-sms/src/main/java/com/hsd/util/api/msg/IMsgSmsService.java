package com.hsd.util.api.msg;

import com.github.pagehelper.PageInfo;
import com.hsd.framework.Response;
import com.hsd.framework.config.FeignConfiguration;
import com.hsd.util.dto.msg.MsgSmsDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
/**
 * <p>短信推送 业务处理接口类。
 */
@FeignClient(value = "${feign.name.util.sms}",configuration = FeignConfiguration.class)//, fallback = TestServiceHystrix.class)
public interface IMsgSmsService {
    String acPrefix = "/feign/util/IMsgSmsService";
    /**
     * <p>信息编辑。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/saveOrUpdateData")
    public Response saveOrUpdateData(MsgSmsDto dto) throws Exception;

    /**
     * <p>物理删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/deleteData")
    public String deleteData(MsgSmsDto dto) throws Exception;



    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsPage")
    public PageInfo findDataIsPage(MsgSmsDto dto) throws Exception;

    /**
     * <p>信息列表。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsList")
    public List<MsgSmsDto> findDataIsList(MsgSmsDto dto) throws Exception;


    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataById")
    public MsgSmsDto findDataById(MsgSmsDto dto) throws Exception;
}