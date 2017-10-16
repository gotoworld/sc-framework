package com.hsd.account.staff.api.org;


import com.github.pagehelper.PageInfo;
import com.hsd.account.staff.dto.auth.AuthRoleDto;
import com.hsd.account.staff.dto.org.AuthStaffVsRoleDto;
import com.hsd.account.staff.dto.org.OrgInfoDto;
import com.hsd.account.staff.dto.org.OrgOrgVsStaffDto;
import com.hsd.account.staff.dto.org.OrgStaffDto;
import com.hsd.framework.Response;
import com.hsd.framework.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>组织架构_员工   业务处理接口类。
 */
@FeignClient(name = "${feign.name.account.staff}",configuration = FeignConfiguration.class)//, fallback = TestServiceHystrix.class)
public interface IOrgStaffService {
    String acPrefix = "/feign/account/staff/IOrgStaffService";

    /**
     * <p>信息编辑。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/saveOrUpdateData")
    public Response saveOrUpdateData(OrgStaffDto dto) throws Exception;
    /**
     * <p>信息编辑。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/updateData")
    public String updateData(OrgStaffDto dto) throws Exception;

    /**
     * <p>物理删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/deleteData")
    public String deleteData(OrgStaffDto dto) throws Exception;

    /**
     * <p>恢复逻辑删除的数据 单条。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/recoveryDataById")
    public String recoveryDataById(OrgStaffDto dto) throws Exception;

    /**
     * <p>逻辑删除 单条。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/deleteDataById")
    public String deleteDataById(OrgStaffDto dto) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsPage")
    public PageInfo findDataIsPage(OrgStaffDto dto) throws Exception;

    /**
     * <p>信息列表。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsList")
    public List<OrgStaffDto> findDataIsList(OrgStaffDto dto);
    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataById")
    public OrgStaffDto findDataById(OrgStaffDto dto);

    /**
     * <p>获取员工所在组织集合。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findOrgIsList")
    public List<OrgInfoDto> findOrgIsList(OrgStaffDto dto);

    /**
     * <p>判断员工账号是否存在
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/isAccountYN")
    public String isAccountYN(@RequestParam(name ="account") String account);

    /**
     * <p>密码修改
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/updatePwd")
    public String updatePwd(OrgStaffDto dto) throws Exception;

    /**
     * <p>密码重置
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/resetPwd")
    public String resetPwd(OrgStaffDto dto) throws Exception;

    /**
     * <p>信息列表(精简) 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findBriefDataIsPage")
    public PageInfo findBriefDataIsPage(OrgStaffDto dto) throws Exception;
    /**
     * <p>获取员工->个人角色。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findStaffRoleIsList")
    public List<AuthRoleDto> findStaffRoleIsList(OrgStaffDto dto);
    /**
     * <p>获取员工->组织角色。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findOrgRoleIsList")
    public List<AuthRoleDto> findOrgRoleIsList(OrgStaffDto dto);
    /**
     * <p>添加组织。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/addOrg")
    public Response addOrg(OrgOrgVsStaffDto dto) throws Exception;

    /**
     * <p>删除组织。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/delOrg")
    public Response delOrg(OrgOrgVsStaffDto dto) throws Exception;
    /**
     * <p>批量新增。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/addBatch")
    public Response addBatch(@RequestParam(name = "fileUrl") String fileUrl) throws Exception;
    /**
     * <p>添加角色。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/addRole")
    public Response addRole(AuthStaffVsRoleDto dto) throws Exception;

    /**
     * <p>添加角色。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/delRole")
    public Response delRole(AuthStaffVsRoleDto dto) throws Exception;
    /**
     * <p>根据角色-获取员工列表。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findStaffByRoleIsList")
    public List<OrgStaffDto> findStaffByRoleIsList(AuthStaffVsRoleDto dto);
    /**
     * <p>设置上级领导。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/setLeadership")
    public Response setLeadership(OrgStaffDto dto) throws Exception;
}