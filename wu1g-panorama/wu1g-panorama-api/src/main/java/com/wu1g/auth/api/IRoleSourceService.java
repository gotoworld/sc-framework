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
package com.wu1g.auth.api;
import java.util.List;
import java.util.Map;

import cn.com.baseos.bean.auth.AuthPerm;
import cn.com.baseos.bean.auth.AuthRole;
import cn.com.baseos.bean.org.OrgUser;
/**
 * <p>角色资源   业务处理接口类。</p>	
 * <ol>[功能概要] 
 * <li>详情检索。 
 *</ol> 
 * @author wuxiaogang
 */
public interface IRoleSourceService{
	/**	
	 * 根据用户id,判断用户是否为超级管理员,要的就是特权.
	 */	
	public int isSuperAdmin(Map dto);
	/**	
	 * <p>角色信息列表>根据用户id。</p>	
	 * <ol>[功能概要] 	
	 * <li>信息检索。	
	 * <li>列表。	
	 * </ol>	
	 */	
	public List<AuthRole> getRoleListByUId(Map dto);	
	
	/**	
	 * <p>角色权限信息列表>根据用户id。</p>	
	 * <ol>[功能概要] 	
	 * <li>信息检索。	
	 * <li>列表。	
	 * </ol>	
	 */	
	public List<AuthPerm> getPermListByUId(Map dto);	
	
	/**	
	 * <p>用户信息。</p>	
	 * <ol>[功能概要] 	
	 * <li>信息检索。	
	 * </ol>	
	 */	
	public OrgUser findUserByLoginName(String loginName);	
}