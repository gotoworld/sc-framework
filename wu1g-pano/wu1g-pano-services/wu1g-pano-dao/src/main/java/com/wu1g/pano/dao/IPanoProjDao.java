/*	
 * 全景_项目 数据库处理接口类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ---------------------------	
 * 1.00     2016.10.02      easycode         程序.发布		
 * -------- ----------- --------------- ---------------------------	
 * Copyright 2016 baseos System. - All Rights Reserved.
 *	
 */

package com.wu1g.pano.dao;


import com.wu1g.framework.IBaseDao;
import com.wu1g.framework.IEntity;

/**
 * <p>全景_项目 数据库处理接口类
 * @author easycode
 */
public interface IPanoProjDao  extends IBaseDao {
	int updateXmlData(IEntity dto) throws Exception;
	
	int thumbsUpNum(IEntity dto) throws Exception;
	
	int pvNum(IEntity dto) throws Exception;
}