package com.hsd.account.staff.api.org;


import com.github.pagehelper.PageInfo;
import com.hsd.account.staff.dto.auth.AuthRoleDto;
import com.hsd.account.staff.dto.org.OrgInfoDto;
import com.hsd.account.staff.dto.org.OrgOrgVsRoleDto;
import com.hsd.account.staff.dto.org.OrgOrgVsStaffDto;
import com.hsd.account.staff.dto.org.OrgStaffDto;
import com.hsd.framework.Response;
import com.hsd.framework.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * <p>组织架构   业务处理接口类。
 */
@FeignClient(name = "${feign.name.account.staff}",configuration = FeignConfiguration.class)//, fallback = TestServiceHystrix.class)
public interface IOrgInfoService {
    String acPrefix = "/feign/account/staff/IOrgInfoService";

    /**
     * <p>信息编辑。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/saveOrUpdateData")
    Response saveOrUpdateData(OrgInfoDto dto) throws Exception;
    /**
     * <p>物理删除。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/deleteData")
    String deleteData(OrgInfoDto dto) throws Exception;

    /**
     * <p>恢复逻辑删除的数据 单条。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/recoveryDataById")
    String recoveryDataById(OrgInfoDto dto) throws Exception;

    /**
     * <p>逻辑删除 单条。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/deleteDataById")
    String deleteDataById(OrgInfoDto dto) throws Exception;

    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsPage")
    PageInfo findDataIsPage(OrgInfoDto dto) throws Exception;
    /**
     * <p>信息列表 分页。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findBriefDataIsPage")
    PageInfo findBriefDataIsPage(OrgInfoDto dto) throws Exception;

    /**
     * <p>信息列表。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsList")
    List<OrgInfoDto> findDataIsList(OrgInfoDto dto);

    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataById")
    OrgInfoDto findDataById(OrgInfoDto dto);
    /**
     * <p>信息详情。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataByCode")
    OrgInfoDto findDataByCode(OrgInfoDto dto);

    /**
     * <p>信息树。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findDataIsTree")
    List<OrgInfoDto> findDataIsTree(OrgInfoDto dto);


    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findStaffByIdIsList")
    List<OrgStaffDto> findOrgStaffIsList(OrgOrgVsStaffDto dto);
    /**
     * <p>添加人员。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/addStaff")
    Response addStaff(OrgOrgVsStaffDto dto) throws Exception;

    /**
     * <p>删除人员。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/delStaff")
    Response delStaff(OrgOrgVsStaffDto dto) throws Exception;

    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findOrgRoleIsList")
    List<AuthRoleDto> findOrgRoleIsList(OrgInfoDto dto);

    /**
     * <p>添加角色。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/addRole")
    Response addRole(OrgOrgVsRoleDto dto) throws Exception;
    /**
     * <p>删除角色。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/delRole")
    Response delRole(OrgOrgVsRoleDto dto) throws Exception;
    /**
     * <p>设置部门负责人。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/setManager")
    Response setManager(OrgInfoDto dto) throws Exception;
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/getManager")
    Response getManager(OrgInfoDto dto) throws Exception;

    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findOrgStaffByCodeIsList")
    List<OrgStaffDto> findOrgStaffByCodeIsList(OrgOrgVsStaffDto dto);
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findOrgLevel")
    String findOrgLevel(OrgOrgVsStaffDto dto);

    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findChildDataIsList")
    List<OrgInfoDto> findChildDataIsList(OrgInfoDto dto);
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findOrgChildStaffIsList")
    List<OrgInfoDto> findOrgChildStaffIsList(OrgInfoDto dto);
}