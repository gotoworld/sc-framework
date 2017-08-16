package com.hsd.account.channel.api.channel;

import com.hsd.account.channel.dto.channel.ChannelInfoDto;
import com.hsd.framework.Response;
import com.hsd.framework.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Map;
/**
 * <p>渠道商信息 业务处理接口类。
 */
@FeignClient(value = "${feign.name.account.channel}",configuration = FeignConfiguration.class)//, fallback = TestServiceHystrix.class)
public interface IChannelInfoService {
    String acPrefix = "/feign/account/channel/IChannelInfoService";
    /**
     * <p>信息编辑。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/saveOrUpdateData")
    public Response saveOrUpdateData(ChannelInfoDto dto) throws Exception;

    /**
     * <p>物理删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/deleteData")
    public String deleteData(ChannelInfoDto dto) throws Exception;


    /**
     * <li>逻辑删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/deleteDataById")
    public String deleteDataById(ChannelInfoDto dto) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsPage")
    public PageInfo findDataIsPage(ChannelInfoDto dto) throws Exception;

    /**
     * <p>信息列表。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsList")
    public List<ChannelInfoDto> findDataIsList(ChannelInfoDto dto) throws Exception;


    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataById")
    public ChannelInfoDto findDataById(ChannelInfoDto dto) throws Exception;

    /**
     * @param dto
     * 信息恢复
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/recoveryData")
	public String recoveryData(ChannelInfoDto dto) throws Exception;
}