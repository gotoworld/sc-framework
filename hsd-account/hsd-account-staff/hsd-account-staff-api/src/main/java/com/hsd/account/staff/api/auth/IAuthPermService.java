package com.hsd.account.staff.api.auth;

import com.github.pagehelper.PageInfo;
import com.hsd.account.staff.dto.auth.AuthPermDto;
import com.hsd.framework.Response;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * <p>权限_权限信息   业务处理接口类。
 */
@FeignClient(name = "${feign.name.account.staff}")//, fallback = TestServiceHystrix.class)
public interface IAuthPermService {
    String acPrefix = "/feign/account/staff/IAuthPermService";

    /**
     * <p>信息编辑。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/saveOrUpdateData")
    public Response saveOrUpdateData(AuthPermDto dto) throws Exception;

    /**
     * <p>物理删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/deleteData")
    public String deleteData(AuthPermDto dto) throws Exception;

    /**
     * <p>恢复逻辑删除的数据。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/recoveryDataById")
    public String recoveryDataById(AuthPermDto dto) throws Exception;

    /**
     * <p>逻辑删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/deleteDataById")
    public String deleteDataById(AuthPermDto dto) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsPage")
    public PageInfo findDataIsPage(AuthPermDto dto) throws Exception;

    /**
     * <p>信息列表。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsList")
    public List<AuthPermDto> findDataIsList(AuthPermDto dto);

    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataById")
    public AuthPermDto findDataById(AuthPermDto dto);

    /**
     * <p>信息树。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsTree")
    public List<AuthPermDto> findDataIsTree(AuthPermDto dto);

    /**
     * <p>根据角色id获取对应的权限信息列表。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findPermDataIsListByRoleId")
    public List<AuthPermDto> findPermDataIsListByRoleId(Map dto);

}