package com.hsd.account.channel.api.channel;

import com.hsd.account.channel.dto.channel.ChannelTypeDto;
import com.hsd.framework.Response;
import com.hsd.framework.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Map;
/**
 * <p>渠道商类型 业务处理接口类。
 */
@FeignClient(value = "${feign.name.account.channel}",configuration = FeignConfiguration.class)//, fallback = TestServiceHystrix.class)
public interface IChannelTypeService {
    String acPrefix = "/feign/account/channel/IChannelTypeService";
    /**
     * <p>信息编辑。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/saveOrUpdateData")
    public Response saveOrUpdateData(ChannelTypeDto dto) throws Exception;

    /**
     * <p>物理删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/deleteData")
    public String deleteData(ChannelTypeDto dto) throws Exception;


    /**
     * <li>逻辑删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/deleteDataById")
    public String deleteDataById(ChannelTypeDto dto) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsPage")
    public PageInfo findDataIsPage(ChannelTypeDto dto) throws Exception;

    /**
     * <p>信息列表。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsList")
    public List<ChannelTypeDto> findDataIsList(ChannelTypeDto dto) throws Exception;


    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataById")
    public ChannelTypeDto findDataById(ChannelTypeDto dto) throws Exception;
}