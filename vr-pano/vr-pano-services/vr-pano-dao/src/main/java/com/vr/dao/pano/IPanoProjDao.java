/*	
 * 全景_项目 数据库处理接口类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ---------------------------	
 * 1.00     2016.10.02      easycode         程序.发布		
 * -------- ----------- --------------- ---------------------------	
 * Copyright 2016 pano System. - All Rights Reserved.
 *	
 */

package com.vr.dao.pano;


import com.vr.framework.IBaseDao;
import com.vr.framework.IEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>全景_项目 数据库处理接口类
 * @author easycode
 */
@Mapper
public interface IPanoProjDao  extends IBaseDao {
	int updateXmlData(IEntity dto) throws Exception;
	
	int thumbsUpNum(IEntity dto) throws Exception;
	
	int pvNum(IEntity dto) throws Exception;
}