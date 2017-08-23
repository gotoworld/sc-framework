package com.hsd.account.actor.api.identity;

import com.github.pagehelper.PageInfo;
import com.hsd.account.actor.dto.identity.IdentityLogDto;
import com.hsd.framework.Response;
import com.hsd.framework.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
/**
 * <p>用户实名认证日志 业务处理接口类。
 */
@FeignClient(value = "${feign.name.account.actor}",configuration = FeignConfiguration.class)//, fallback = TestServiceHystrix.class)
public interface IIdentityLogService {
    String acPrefix = "/feign/account/actor/IIdentityLogService";
    /**
     * <p>信息编辑。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/saveOrUpdateData")
    public Response saveOrUpdateData(IdentityLogDto dto) throws Exception;

    /**
     * <p>物理删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/deleteData")
    public String deleteData(IdentityLogDto dto) throws Exception;



    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsPage")
    public PageInfo findDataIsPage(IdentityLogDto dto) throws Exception;

    /**
     * <p>信息列表。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsList")
    public List<IdentityLogDto> findDataIsList(IdentityLogDto dto) throws Exception;


    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataById")
    public IdentityLogDto findDataById(IdentityLogDto dto) throws Exception;
}