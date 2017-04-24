package com.wu1g.api.auth;


import com.wu1g.vo.auth.AuthRole;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <p>权限_角色信息   业务处理接口类。
 */
@FeignClient(name = "wu1g-service-server")//, fallback = TestServiceHystrix.class)
public interface IAuthRoleService {

    /**
     * <p>信息编辑。
     */
    @RequestMapping(value = "/saveOrUpdateData")
    public String saveOrUpdateData(AuthRole bean) throws Exception;

    /**
     * <p>物理删除。
     */
    @RequestMapping(value = "/deleteData")
    public String deleteData(AuthRole bean) throws Exception;

    /**
     * <p>恢复逻辑删除的数据。
     */
    @RequestMapping(value = "/recoveryDataById")
    public String recoveryDataById(AuthRole bean) throws Exception;

    /**
     * <p>逻辑删除。
     */
    @RequestMapping(value = "/deleteDataById")
    public String deleteDataById(AuthRole bean) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(value = "/findDataIsPage")
    public List<AuthRole> findDataIsPage(AuthRole bean);

    /**
     * <p>信息列表。
     */
    @RequestMapping(value = "/findDataIsList")
    public List<AuthRole> findDataIsList(AuthRole bean);

    /**
     * <p>信息详情。
     */
    @RequestMapping(value = "/findDataById")
    public AuthRole findDataById(AuthRole bean);
}