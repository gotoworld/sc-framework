package com.hsd.account.api.auth;

import com.hsd.account.dto.auth.AuthPermDto;
import com.hsd.framework.Response;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * <p>权限_权限信息   业务处理接口类。
 */
@FeignClient(name = "${feign.name.account}")//, fallback = TestServiceHystrix.class)
public interface IAuthPermService {
    String actPrefix = "/feign/IAuthPermService";

    /**
     * <p>信息编辑。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/saveOrUpdateData")
    public Response saveOrUpdateData(AuthPermDto dto) throws Exception;

    /**
     * <p>物理删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/deleteData")
    public String deleteData(AuthPermDto dto) throws Exception;

    /**
     * <p>恢复逻辑删除的数据。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/recoveryDataById")
    public String recoveryDataById(AuthPermDto dto) throws Exception;

    /**
     * <p>逻辑删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/deleteDataById")
    public String deleteDataById(AuthPermDto dto) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findDataIsPage")
    public List<AuthPermDto> findDataIsPage(AuthPermDto dto);

    /**
     * <p>信息列表。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findDataIsList")
    public List<AuthPermDto> findDataIsList(AuthPermDto dto);

    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findDataById")
    public AuthPermDto findDataById(AuthPermDto dto);

    /**
     * <p>信息树。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findDataIsTree")
    public List<AuthPermDto> findDataIsTree(AuthPermDto dto);

    /**
     * <p>根据角色id获取对应的权限信息列表。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findPermDataIsListByRoleId")
    public List<AuthPermDto> findPermDataIsListByRoleId(Map dto);

}