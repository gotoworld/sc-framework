/*	
 * 角色资源   业务处理接口类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.06.29      wuxiaogang         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 baseos System. - All Rights Reserved.		
 *	
 */
package com.hsd.account.staff.api.auth;


import com.hsd.account.staff.dto.auth.AuthPermDto;
import com.hsd.account.staff.dto.auth.AuthRoleDto;
import com.hsd.account.staff.dto.org.OrgStaffDto;
import com.hsd.account.staff.dto.sys.SysAppDto;
import com.hsd.framework.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>角色资源   业务处理接口类。
 */
@FeignClient(name = "${feign.name.account.staff}",configuration = FeignConfiguration.class)//, fallback = TestServiceHystrix.class)
public interface IRoleSourceService {
    String acPrefix = "/feign/account/staff/IRoleSourceService";
    /**
     * 根据员工id,判断员工是否为超级管理员,要的就是特权.
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/isSuperAdmin")
    Integer isSuperAdmin(OrgStaffDto orgStaff);
    /**
     * <p>角色信息列表>根据员工id。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/getRoleListByStaffId")
    List<AuthRoleDto> getRoleListByStaffId(OrgStaffDto orgStaff);

    /**
     * <p>角色权限信息列表>根据员工id。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/getPermListByStaffId")
    List<AuthPermDto> getPermListByStaffId(OrgStaffDto orgStaff);

    /**
     * <p>角色应用信息列表>根据员工id。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/getAppListByStaffId")
    List<SysAppDto> getAppListByStaffId(OrgStaffDto orgStaff);

    /**
     * <p>员工信息。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findStaffByAccount")
    OrgStaffDto findStaffByAccount(@RequestParam("account") String account, @RequestParam("staffType") Integer staffType);

    /**
     * 更新员工登陆信息
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/lastLogin")
    Integer lastLogin(OrgStaffDto orgStaff);
}