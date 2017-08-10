package com.hsd.account.staff.api.auth;


import com.github.pagehelper.PageInfo;
import com.hsd.account.staff.dto.auth.AuthRoleDto;
import com.hsd.framework.Response;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * <p>权限_角色信息   业务处理接口类。
 */
@FeignClient(name = "${feign.name.account.staff}")//, fallback = TestServiceHystrix.class)
public interface IAuthRoleService {
    String acPrefix = "/feign/account/staff/IAuthRoleService";
    /**
     * <p>信息编辑。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/saveOrUpdateData")
    public Response saveOrUpdateData(AuthRoleDto dto) throws Exception;

    /**
     * <p>物理删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/deleteData")
    public String deleteData(AuthRoleDto dto) throws Exception;

    /**
     * <p>逻辑删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/deleteDataById")
    public String deleteDataById(AuthRoleDto dto) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsPage")
    public PageInfo findDataIsPage(AuthRoleDto dto);

    /**
     * <p>信息列表。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsList")
    public List<AuthRoleDto> findDataIsList(AuthRoleDto dto);

    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataById")
    public AuthRoleDto findDataById(AuthRoleDto dto);
}