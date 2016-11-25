/*	
 * 权限_权限信息  数据库处理接口类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 isd System. - All Rights Reserved.		
 *	
 */
package com.wu1g.auth.dao;


import com.wu1g.auth.vo.AuthPerm;
import com.wu1g.framework.IBaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
/**
 * <p>权限_权限信息  数据库处理接口类。</p>	
 * @author easycode
 */
@Mapper
public interface IAuthPermDao extends IBaseDao {
	/**角色权限信息列表>根据用户id。*/
	List getPermListByUId(Map dto) throws Exception;
	/**	
	 * <p>根据角色id获取对应的权限信息列表。</p>	
	 * <ol>[功能概要] 	
	 * <li>信息检索。	
	 * <li>列表。	
	 * </ol>	
	 */	
	public List<AuthPerm> findPermDataIsListByRoleId(Map dto) throws Exception;
}