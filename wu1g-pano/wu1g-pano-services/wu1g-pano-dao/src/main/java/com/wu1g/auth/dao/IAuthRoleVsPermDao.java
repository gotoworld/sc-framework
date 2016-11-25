/*	
 * 权限_角色vs权限  数据库处理接口类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 isd System. - All Rights Reserved.		
 *	
 */
package com.wu1g.auth.dao;
import com.wu1g.framework.IBaseDao;

import java.util.Map;

/**
 * <p>权限_角色vs权限  数据库处理接口类。</p>	
 * @author easycode
 */
public interface IAuthRoleVsPermDao extends IBaseDao {
	/**根据角色id清空角色关联权限信息*/
	int deleteDataByRid(Map dto) throws Exception;
}