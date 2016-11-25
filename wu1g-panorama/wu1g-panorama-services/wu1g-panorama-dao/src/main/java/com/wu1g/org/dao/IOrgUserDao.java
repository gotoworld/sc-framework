/*	
 * 组织架构_用户  数据库处理接口类	
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

import org.apache.ibatis.annotations.Param;

import cn.com.baseos.bean.org.OrgUser;
import cn.com.baseos.dao.daointer.IBaseDao;
/**
 * <p>组织架构_用户  数据库处理接口类。</p>	
 * @author easycode
 */
public interface IOrgUserDao extends IBaseDao{
	/**	
	 * <p>获取用户信息>根据用户登录名。</p>	
	 * <ol>[功能概要] 	
	 * <li>信息检索。	
	 * </ol>	
	 */	
	public OrgUser findUserByLoginName(Map dto) throws Exception;

	/**获取某一种角色所有用户*/
	List<OrgUser> getUserList(OrgUser dto) throws Exception;
	
	/**获取某一种角色所有用户(分页)*/
	List<OrgUser> getUserIsPage(OrgUser dto) throws Exception;
	
	/**<p>判断用户id是否存在</p>	*/
	public int isUidYN(@Param("userid") String uid) throws Exception;

	List<OrgUser> findTeacherDataIsList(OrgUser dto);
}