/*	
 * 全景_导览图 数据库处理接口类	
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
 * <p>全景_导览图 数据库处理接口类
 * @author easycode
 */
@Mapper
public interface IPanoMapDao  extends IBaseDao {
	/**根据项目id删除*/
	int deleteByProjId(IEntity dto) throws Exception;
}