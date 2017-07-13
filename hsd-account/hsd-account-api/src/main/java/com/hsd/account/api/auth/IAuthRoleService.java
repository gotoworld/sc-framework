package com.hsd.account.api.auth;


import com.hsd.account.vo.auth.AuthRole;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * <p>权限_角色信息   业务处理接口类。
 */
@FeignClient(name = "${feign.name.account}")//, fallback = TestServiceHystrix.class)
public interface IAuthRoleService {
    String actPrefix = "/api/IAuthRoleService";
    /**
     * <p>信息编辑。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/saveOrUpdateData")
    public String saveOrUpdateData(AuthRole bean) throws Exception;

    /**
     * <p>物理删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/deleteData")
    public String deleteData(AuthRole bean) throws Exception;

    /**
     * <p>恢复逻辑删除的数据。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/recoveryDataById")
    public String recoveryDataById(AuthRole bean) throws Exception;

    /**
     * <p>逻辑删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/deleteDataById")
    public String deleteDataById(AuthRole bean) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findDataIsPage")
    public List<AuthRole> findDataIsPage(AuthRole bean);

    /**
     * <p>信息列表。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findDataIsList")
    public List<AuthRole> findDataIsList(AuthRole bean);

    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findDataById")
    public AuthRole findDataById(AuthRole bean);
}