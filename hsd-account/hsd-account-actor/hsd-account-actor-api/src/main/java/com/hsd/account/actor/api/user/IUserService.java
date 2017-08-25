package com.hsd.account.actor.api.user;

import com.github.pagehelper.PageInfo;
import com.hsd.account.actor.dto.user.UserDto;
import com.hsd.framework.Response;
import com.hsd.framework.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
/**
 * <p>客户表 业务处理接口类。
 */
@FeignClient(value = "${feign.name.account.actor}",configuration = FeignConfiguration.class)//, fallback = TestServiceHystrix.class)
public interface IUserService {
    String acPrefix = "/feign/account/actor/IUserService";
    /**
     * <p>信息编辑。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/saveOrUpdateData")
    public Response saveOrUpdateData(UserDto dto) throws Exception;
    /**
     * <p>物理删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/deleteData")
    public String deleteData(UserDto dto) throws Exception;



    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsPage")
    public PageInfo findDataIsPage(UserDto dto) throws Exception;

    /**
     * <p>信息列表。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsList")
    public List<UserDto> findDataIsList(UserDto dto) throws Exception;


    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataById")
    public UserDto findDataById(UserDto dto) throws Exception;

    /**
     * <p>设置标签。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/setTags")
    public Response setTags(UserDto dto) throws Exception;
    /**
     * <p>设置黑名单。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/setBlacklist")
    public Response setBlacklist(UserDto dto) throws Exception;
    /**
     * <p>移除黑名单。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/delBlacklist")
    public Response delBlacklist(UserDto dto) throws Exception;
}