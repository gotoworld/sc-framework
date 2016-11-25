/*	
 * 组织架构_部门  数据库处理接口类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 isd System. - All Rights Reserved.		
 *	
 */
package com.wu1g.org.dao;
import java.util.List;
import java.util.Map;

import cn.com.baseos.bean.org.OrgDepartment;
import cn.com.baseos.dao.daointer.IBaseDao;
/**
 * <p>组织架构_部门  数据库处理接口类。</p>	
 * @author easycode
 */
public interface IOrgDepartmentDao extends IBaseDao{
	/**获取用户所在部门集合>根据用户id*/
	List<OrgDepartment> getDeptListByUId(Map dto) throws Exception;
}