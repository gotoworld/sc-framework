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
    Response saveOrUpdateData(OrgStaffDto dto) throws Exception;
    /**
     * <p>信息新增。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/insert")
    Response insert(OrgStaffDto dto) throws Exception;
    /**
     * <p>信息编辑。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/updateData")
    String updateData(OrgStaffDto dto) throws Exception;

    /**
     * <p>物理删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/deleteData")
    String deleteData(OrgStaffDto dto) throws Exception;

    /**
     * <p>恢复逻辑删除的数据 单条。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/recoveryDataById")
    String recoveryDataById(OrgStaffDto dto) throws Exception;

    /**
     * <p>逻辑删除 单条。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/deleteDataById")
    String deleteDataById(OrgStaffDto dto) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsPage")
    PageInfo findDataIsPage(OrgStaffDto dto) throws Exception;

    /**
     * <p>信息列表。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsList")
    List<OrgStaffDto> findDataIsList(OrgStaffDto dto);

    /**
     * <p>获取用户及用户所在组织。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findStaffAndOrgDataIsList")
    List<OrgStaffDto> findStaffAndOrgDataIsList(OrgStaffDto dto);
    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataById")
    OrgStaffDto findDataById(OrgStaffDto dto);
    /**
     * <p>获取员工信息-根据账号。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataByAccount")
    OrgStaffDto findDataByAccount(OrgStaffDto dto);
    /**
     * <p>获取员工所在组织集合。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findOrgIsList")
    List<OrgInfoDto> findOrgIsList(OrgStaffDto dto);

    /**
     * <p>判断员工账号是否存在
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/isAccountYN")
    String isAccountYN(@RequestParam(name = "account") String account);

    /**
     * <p>密码修改
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/updatePwd")
    String updatePwd(OrgStaffDto dto) throws Exception;

    /**
     * <p>密码重置
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/resetPwd")
    String resetPwd(OrgStaffDto dto) throws Exception;

    /**
     * <p>信息列表(精简) 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findBriefDataIsPage")
    PageInfo findBriefDataIsPage(OrgStaffDto dto) throws Exception;

    /**
     * <p>获取员工->个人角色。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findStaffRoleIsList")
    List<AuthRoleDto> findStaffRoleIsList(OrgStaffDto dto);
    /**
     * <p>获取员工->组织角色。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findOrgRoleIsList")
    List<AuthRoleDto> findOrgRoleIsList(OrgStaffDto dto);
    /**
     * <p>添加组织。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/addOrg")
    Response addOrg(OrgOrgVsStaffDto dto) throws Exception;
    /**
     * <p>删除组织。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/delOrg")
    Response delOrg(OrgOrgVsStaffDto dto) throws Exception;

    /**
     * <p>批量新增。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/addBatch")
    Response addBatch(@RequestParam(name = "fileUrl") String fileUrl) throws Exception;
    /**
     * <p>添加角色。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/addRole")
    Response addRole(AuthStaffVsRoleDto dto) throws Exception;
    /**
     * <p>添加角色。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/delRole")
    Response delRole(AuthStaffVsRoleDto dto) throws Exception;

    /**
     * <p>根据角色-获取员工列表。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findStaffByRoleIsList")
    List<OrgStaffDto> findStaffByRoleIsList(AuthStaffVsRoleDto dto);
    /**
     * <p>设置上级领导。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/setLeadership")
    Response setLeadership(OrgStaffDto dto) throws Exception;
    /**
     * <p>获取上级领导。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/getLeadership")
    Response getLeadership(OrgStaffDto dto) throws Exception;
    /**
     * <p>获取员工-根据员工和上级级别。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/getStaffByStaffIdAndleadershipLevel")
    OrgStaffDto getStaffByStaffIdAndleadershipLevel(OrgStaffDto dto);
    /**
     * <p>获取员工-所有上级。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/getStaffLeadershipAll")
    List<OrgStaffDto> getStaffLeadershipAll(OrgStaffDto dto);

    /**
     * 获取最大员工号
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/getDataByMaxJobNo")
    List<String> getMaxJobNo()throws Exception;
}