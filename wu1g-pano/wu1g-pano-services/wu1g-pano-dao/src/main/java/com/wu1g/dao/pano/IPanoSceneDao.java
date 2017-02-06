/*	
 * 全景_场景 数据库处理接口类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ---------------------------	
 * 1.00     2016.10.02      easycode         程序.发布		
 * -------- ----------- --------------- ---------------------------	
 * Copyright 2016 pano System. - All Rights Reserved.
 *	
 */

package com.wu1g.dao.pano;


import com.wu1g.framework.IBaseDao;
import com.wu1g.framework.IEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>全景_场景 数据库处理接口类
 * @author easycode
 */
@Mapper
public interface IPanoSceneDao  extends IBaseDao {
	/**根据项目id删除*/
	int deleteByProjId(IEntity dto) throws Exception;
}