package com.hsd.account.api.org;


import com.hsd.account.dto.org.OrgInfoDto;
import com.hsd.account.dto.auth.AuthRoleDto;
import com.hsd.account.dto.org.OrgUserDto;
import com.hsd.framework.Response;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * <p>组织架构_用户   业务处理接口类。
 */
@FeignClient(name = "${feign.name.account}")//, fallback = TestServiceHystrix.class)
public interface IOrgUserService {
    String actPrefix = "/feign/IOrgUserService";

    /**
     * <p>信息编辑。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/saveOrUpdateData")
    public Response saveOrUpdateData(OrgUserDto dto) throws Exception;

    /**
     * <p>信息编辑。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/updateData")
    public String updateData(OrgUserDto dto) throws Exception;

    /**
     * <p>物理删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/deleteData")
    public String deleteData(OrgUserDto dto) throws Exception;

    /**
     * <p>恢复逻辑删除的数据 单条。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/recoveryDataById")
    public String recoveryDataById(OrgUserDto dto) throws Exception;

    /**
     * <p>逻辑删除 单条。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/deleteDataById")
    public String deleteDataById(OrgUserDto dto) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findDataIsPage")
    public List<OrgUserDto> findDataIsPage(OrgUserDto dto);

    /**
     * <p>信息列表。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findDataIsList")
    public List<OrgUserDto> findDataIsList(OrgUserDto dto);

    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findDataById")
    public OrgUserDto findDataById(OrgUserDto dto);

    /**
     * <p>获取用户角色集合。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findRoleDataIsList")
    public List<AuthRoleDto> findRoleDataIsList(OrgUserDto dto);

    /**
     * <p>获取用户所在部门集合。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findDeptDataIsList")
    public List<OrgInfoDto> findDeptDataIsList(OrgUserDto dto);

    /**
     * <p>某一种角色所有用户。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findUserList")
    public List<OrgUserDto> findUserList(OrgUserDto orgUserBean);

    /**
     * <p>某一种角色所有用户。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findUserIsPage")
    public List<OrgUserDto> findUserIsPage(OrgUserDto orgUserBean);

    /**
     * <p>判断用户id是否存在
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/isUidYN")
    public String isUidYN(String uid);
    /**
     * <p>密码修改
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/updatePwd")
    public String updatePwd(OrgUserDto dto) throws Exception;
}