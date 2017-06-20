package com.vr.api.auth;

import com.vr.vo.auth.AuthPerm;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * <p>权限_权限信息   业务处理接口类。
 */
@FeignClient(name = "${feign.name}")//, fallback = TestServiceHystrix.class)
public interface IAuthPermService {
    String actPrefix = "/api/IAuthPermService";

    /**
     * <p>信息编辑。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/saveOrUpdateData")
    public String saveOrUpdateData(AuthPerm bean) throws Exception;

    /**
     * <p>物理删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/deleteData")
    public String deleteData(AuthPerm bean) throws Exception;

    /**
     * <p>恢复逻辑删除的数据。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/recoveryDataById")
    public String recoveryDataById(AuthPerm bean) throws Exception;

    /**
     * <p>逻辑删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/deleteDataById")
    public String deleteDataById(AuthPerm bean) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findDataIsPage")
    public List<AuthPerm> findDataIsPage(AuthPerm bean);

    /**
     * <p>信息列表。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findDataIsList")
    public List<AuthPerm> findDataIsList(AuthPerm bean);

    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findDataById")
    public AuthPerm findDataById(AuthPerm bean);

    /**
     * <p>信息树。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findDataTree")
    public List<AuthPerm> findDataTree(AuthPerm bean);

    /**
     * <p>根据角色id获取对应的权限信息列表。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findPermDataIsListByRoleId")
    public List<AuthPerm> findPermDataIsListByRoleId(Map dto);

}