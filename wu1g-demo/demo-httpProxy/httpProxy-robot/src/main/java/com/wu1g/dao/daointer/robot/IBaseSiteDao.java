/*
 * 站点信息DAO 接口类
 *
 * VERSION  DATE        BY              REASON
 * -------- ----------- --------------- ------------------------------------------
 * 1.00     2013.04.17  wuxiaogang      程序・发布
 * -------- ----------- --------------- ------------------------------------------
 * Copyright 2013 robots  tem. - All Rights Reserved.
 *
 */
package com.wu1g.dao.daointer.robot;

import java.util.List;

import com.wu1g.bean.robot.BaseSiteBean;
import com.wu1g.dao.entity.IEntity;

/**
 * 站点信息DAO 接口类
 * @author wuxiaogang
 *
 */
public interface IBaseSiteDao {
	/**
     * 站点信息列表 分页
     * @param instance
     * @return
     */
	public List<BaseSiteBean> findBaseSiteBeanIsPage(IEntity dto) throws Exception;
	/**
     * 站点信息列表 
     * @param instance
     * @return
     */
	public List<BaseSiteBean> findBaseSiteBeanIsList(IEntity dto) throws Exception;
	/**
     * 站点信息列
     * @param instance
     * @return
     */
	public BaseSiteBean findBaseSiteBeanById(IEntity dto) throws Exception;
	/**
	 * 检查站点是否存在
	 * @param dto
	 * @throws Exception
	 */
	public int isBaseSiteYN(IEntity dto) throws Exception;
	/**
	 * 新增站点信息
	 * @param dto
	 * @throws Exception
	 */
	public void insertBaseSite(IEntity dto) throws Exception;
	/**
	 * 更新站点信息
	 * @param dto
	 * @throws Exception
	 */
	public void updateBaseSite(IEntity dto) throws Exception;
}
