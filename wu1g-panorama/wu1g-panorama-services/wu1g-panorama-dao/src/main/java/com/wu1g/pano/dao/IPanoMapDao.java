/*	
 * 全景_导览图 数据库处理接口类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ---------------------------	
 * 1.00     2016.10.02      easycode         程序.发布		
 * -------- ----------- --------------- ---------------------------	
 * Copyright 2016 baseos System. - All Rights Reserved.
 *	
 */

package com.wu1g.pano.dao;


import cn.com.baseos.dao.daointer.IBaseDao;
import cn.com.baseos.dao.entity.IEntity;
/**
 * <p>全景_导览图 数据库处理接口类
 * @author easycode
 */
public interface IPanoMapDao  extends IBaseDao{
	/**根据项目id删除*/
	int deleteByProjId(IEntity dto) throws Exception;
}