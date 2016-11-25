/*	
 * 组织架构_用户vs部门  数据库处理接口类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 isd System. - All Rights Reserved.		
 *	
 */
package com.wu1g.org.dao;

import java.util.Map;

import cn.com.baseos.dao.daointer.IBaseDao;

/**
 * <p>
 * 组织架构_用户vs部门 数据库处理接口类。
 * </p>
 * 
 * @author easycode
 */
public interface IOrgUserVsDepartmentDao extends IBaseDao {
	/** 根据用户id清空用户关联部门信息 */
	int deleteDataByUid(Map dto) throws Exception;
}