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
package com.hsd.api.auth;


import com.hsd.vo.auth.AuthPerm;
import com.hsd.vo.auth.AuthRole;
import com.hsd.vo.org.OrgUser;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>角色资源   业务处理接口类。
 */
@FeignClient(name = "${feign.name.account}")//, fallback = TestServiceHystrix.class)
public interface IRoleSourceService {
    String actPrefix = "/api/IRoleSourceService";
    /**
     * 根据用户id,判断用户是否为超级管理员,要的就是特权.
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/isSuperAdmin")
    public Integer isSuperAdmin(OrgUser orgUser);
    /**
     * <p>角色信息列表>根据用户id。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/getRoleListByUId")
    public List<AuthRole> getRoleListByUId(OrgUser orgUser);

    /**
     * <p>角色权限信息列表>根据用户id。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/getPermListByUId")
    public List<AuthPerm> getPermListByUId(OrgUser orgUser);

    /**
     * <p>用户信息。
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/findUserByLoginName")
    public OrgUser findUserByLoginName(@RequestParam("account") String account,@RequestParam("userType")  Integer userType);

    /**
     * 更新用户登陆信息
     */
    @RequestMapping(method = {RequestMethod.POST},value = actPrefix + "/lastLogin")
    public Integer lastLogin(OrgUser orgUser);
}