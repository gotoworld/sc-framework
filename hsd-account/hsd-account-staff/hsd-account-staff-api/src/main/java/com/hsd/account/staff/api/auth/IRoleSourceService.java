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
import com.hsd.account.staff.dto.org.OrgUserDto;
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
     * 根据用户id,判断用户是否为超级管理员,要的就是特权.
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/isSuperAdmin")
    public Integer isSuperAdmin(OrgUserDto orgUser);
    /**
     * <p>角色信息列表>根据用户id。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/getRoleListByUId")
    public List<AuthRoleDto> getRoleListByUId(OrgUserDto orgUser);

    /**
     * <p>角色权限信息列表>根据用户id。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/getPermListByUId")
    public List<AuthPermDto> getPermListByUId(OrgUserDto orgUser);

    /**
     * <p>用户信息。
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/findUserByLoginName")
    public OrgUserDto findUserByLoginName(@RequestParam("account") String account, @RequestParam("userType")  Integer userType);

    /**
     * 更新用户登陆信息
     */
    @RequestMapping(method = {RequestMethod.POST},value = acPrefix + "/lastLogin")
    public Integer lastLogin(OrgUserDto orgUser);
}