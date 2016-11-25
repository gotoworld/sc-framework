/*	
 * 权限_用户vs角色  数据库处理接口类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 isd System. - All Rights Reserved.		
 *	
 */
package com.wu1g.auth.dao;
import java.util.Map;

import cn.com.baseos.dao.daointer.IBaseDao;
/**
 * <p>权限_用户vs角色  数据库处理接口类。</p>	
 * @author easycode
 */
public interface IAuthUserVsRoleDao extends IBaseDao{
	/**根据用户id清空用户关联角色信息*/
	int deleteDataByUid(Map dto) throws Exception;
}