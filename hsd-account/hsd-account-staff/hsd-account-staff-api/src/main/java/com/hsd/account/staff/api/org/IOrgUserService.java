package com.hsd.account.staff.api.org;


import com.github.pagehelper.PageInfo;
import com.hsd.account.staff.dto.auth.AuthRoleDto;
import com.hsd.account.staff.dto.org.AuthUserVsRoleDto;
import com.hsd.account.staff.dto.org.OrgInfoDto;
import com.hsd.account.staff.dto.org.OrgOrgVsUserDto;
import com.hsd.account.staff.dto.org.OrgUserDto;
import com.hsd.framework.Response;
import com.hsd.framework.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>组织架构_用户   业务处理接口类。
 */
@FeignClient(name = "${feign.name.account.staff}",configuration = FeignConfiguration.class)//, fallback = TestServiceHystrix.class)
public interface IOrgUserService {
    String acPrefix = "/feign/account/staff/IOrgUserService";

    /**
     * <p>信息编辑。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/saveOrUpdateData")
    public Response saveOrUpdateData(OrgUserDto dto) throws Exception;
    /**
     * <p>信息编辑。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/updateData")
    public String updateData(OrgUserDto dto) throws Exception;

    /**
     * <p>物理删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/deleteData")
    public String deleteData(OrgUserDto dto) throws Exception;

    /**
     * <p>恢复逻辑删除的数据 单条。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/recoveryDataById")
    public String recoveryDataById(OrgUserDto dto) throws Exception;

    /**
     * <p>逻辑删除 单条。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/deleteDataById")
    public String deleteDataById(OrgUserDto dto) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsPage")
    public PageInfo findDataIsPage(OrgUserDto dto) throws Exception;

    /**
     * <p>信息列表。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsList")
    public List<OrgUserDto> findDataIsList(OrgUserDto dto);
    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataById")
    public OrgUserDto findDataById(OrgUserDto dto);

    /**
     * <p>获取用户所在组织集合。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findOrgIsList")
    public List<OrgInfoDto> findOrgIsList(OrgUserDto dto);

    /**
     * <p>判断用户账号是否存在
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/isAccountYN")
    public String isAccountYN(@RequestParam(name ="account") String account);

    /**
     * <p>密码修改
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/updatePwd")
    public String updatePwd(OrgUserDto dto) throws Exception;

    /**
     * <p>密码重置
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/resetPwd")
    public String resetPwd(OrgUserDto dto) throws Exception;

    /**
     * <p>信息列表(精简) 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findBriefDataIsPage")
    public PageInfo findBriefDataIsPage(OrgUserDto dto) throws Exception;
    /**
     * <p>获取用户->个人角色。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findUserRoleIsList")
    public List<AuthRoleDto> findUserRoleIsList(OrgUserDto dto);
    /**
     * <p>获取用户->组织角色。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findOrgRoleIsList")
    public List<AuthRoleDto> findOrgRoleIsList(OrgUserDto dto);

    /**
     * <p>添加组织。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/addOrg")
    public Response addOrg(OrgOrgVsUserDto dto) throws Exception;
    /**
     * <p>删除组织。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/delOrg")
    public Response delOrg(OrgOrgVsUserDto dto) throws Exception;
    /**
     * <p>批量新增。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/addBatch")
    public Response addBatch(@RequestParam(name = "fileUrl") String fileUrl) throws Exception;

    /**
     * <p>添加角色。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/addRole")
    public Response addRole(AuthUserVsRoleDto dto) throws Exception;
    /**
     * <p>添加角色。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/delRole")
    public Response delRole(AuthUserVsRoleDto dto) throws Exception;
}