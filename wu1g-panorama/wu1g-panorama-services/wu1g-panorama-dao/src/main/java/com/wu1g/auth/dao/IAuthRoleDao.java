/*	
 * 权限_角色信息  数据库处理接口类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 isd System. - All Rights Reserved.		
 *	
 */
package com.wu1g.auth.dao;
import java.util.List;
import java.util.Map;

import cn.com.baseos.bean.auth.AuthRole;
import cn.com.baseos.dao.daointer.IBaseDao;
/**
 * <p>权限_角色信息  数据库处理接口类。</p>	
 * @author easycode
 */
public interface IAuthRoleDao extends IBaseDao{
	/**角色信息列表>根据用户id。*/
	List<AuthRole> getRoleListByUId(Map dto) throws Exception;
	/**	
	 * 根据用户id,判断用户是否为超级管理员,要的就是特权.
	 */	
	int isSuperAdmin(Map dto) throws Exception;
}