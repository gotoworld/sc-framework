package com.hsd.account.actor.api.identity;

import com.github.pagehelper.PageInfo;
import com.hsd.account.actor.dto.identity.IdentityDto;
import com.hsd.framework.Response;
import com.hsd.framework.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
/**
 * <p>实名认证表 业务处理接口类。
 */
@FeignClient(value = "${feign.name.account.actor}",configuration = FeignConfiguration.class)//, fallback = TestServiceHystrix.class)
public interface IIdentityService {
    String acPrefix = "/feign/account/actor/IIdentityService";
    /**
     * <p>信息编辑。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/saveOrUpdateData")
    public Response saveOrUpdateData(IdentityDto dto) throws Exception;

    /**
     * <p>物理删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/deleteData")
    public String deleteData(IdentityDto dto) throws Exception;



    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsPage")
    public PageInfo findDataIsPage(IdentityDto dto) throws Exception;

    /**
     * <p>信息列表。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsList")
    public List<IdentityDto> findDataIsList(IdentityDto dto) throws Exception;


    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataById")
    public IdentityDto findDataById(IdentityDto dto) throws Exception;
    /**
     * <p>判断用户是否已实名认证。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/isUserInentityYN")
    public Integer isUserInentityYN(IdentityDto dto) throws Exception;
}