package com.wu1g.api.auth;

import com.wu1g.vo.auth.AuthPerm;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * <p>权限_权限信息   业务处理接口类。
 */
@FeignClient(name = "${spring.application.name}")//, fallback = TestServiceHystrix.class)
public interface IAuthPermService {

    /**
     * <p>信息编辑。
     */
    @RequestMapping(value = "/saveOrUpdateData")
    public String saveOrUpdateData(AuthPerm bean) throws Exception;

    /**
     * <p>物理删除。
     */
    @RequestMapping(value = "/deleteData")
    public String deleteData(AuthPerm bean) throws Exception;

    /**
     * <p>恢复逻辑删除的数据。
     */
    @RequestMapping(value = "/recoveryDataById")
    public String recoveryDataById(AuthPerm bean) throws Exception;

    /**
     * <p>逻辑删除。
     */
    @RequestMapping(value = "/deleteDataById")
    public String deleteDataById(AuthPerm bean) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(value = "/findDataIsPage")
    public List<AuthPerm> findDataIsPage(AuthPerm bean);

    /**
     * <p>信息列表。
     */
    @RequestMapping(value = "/findDataIsList")
    public List<AuthPerm> findDataIsList(AuthPerm bean);

    /**
     * <p>信息详情。
     */
    @RequestMapping(value = "/findDataById")
    public AuthPerm findDataById(AuthPerm bean);

    /**
     * <p>信息树。
     */
    @RequestMapping(value = "/findDataTree")
    public List<AuthPerm> findDataTree(AuthPerm bean);

    /**
     * <p>根据角色id获取对应的权限信息列表。
     */
    @RequestMapping(value = "/findPermDataIsListByRoleId")
    public List<AuthPerm> findPermDataIsListByRoleId(Map dto);

}