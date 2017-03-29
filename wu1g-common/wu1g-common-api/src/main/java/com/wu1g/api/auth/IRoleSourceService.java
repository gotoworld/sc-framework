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
package com.wu1g.api.auth;


import com.wu1g.vo.auth.AuthPerm;
import com.wu1g.vo.auth.AuthRole;
import com.wu1g.vo.org.OrgUser;

import java.util.List;
import java.util.Map;

/**
 * <p>角色资源   业务处理接口类。
 */
public interface IRoleSourceService {
    /**
     * 根据用户id,判断用户是否为超级管理员,要的就是特权.
     */
    public int isSuperAdmin(Map dto);

    /**
     * <p>角色信息列表>根据用户id。
     */
    public List<AuthRole> getRoleListByUId(Map dto);

    /**
     * <p>角色权限信息列表>根据用户id。
     */
    public List<AuthPerm> getPermListByUId(Map dto);

    /**
     * <p>用户信息。
     */
    public OrgUser findUserByLoginName(String accid,Integer userType);
}