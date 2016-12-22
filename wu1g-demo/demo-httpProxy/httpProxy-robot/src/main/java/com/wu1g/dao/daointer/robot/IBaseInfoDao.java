/*
 * 采集结果信息DAO 接口类
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

import com.wu1g.bean.robot.BaseInfoBean;
import com.wu1g.dao.entity.IEntity;

/**
 * 采集结果信息DAO 接口类
 * @author wuxiaogang
 *
 */
public interface IBaseInfoDao {
	/**
     * 采集结果信息列表 分页
     * @param instance
     * @return
     */
	public List<BaseInfoBean> findBaseInfoBeanIsPage(IEntity dto) throws Exception;
	/**
     * 采集结果信息列表 
     * @param instance
     * @return
     */
	public List<BaseInfoBean> findBaseInfoBeanIsList(IEntity dto) throws Exception;
	/**
     * 采集结果信息列
     * @param instance
     * @return
     */
	public BaseInfoBean findBaseInfoBeanById(IEntity dto) throws Exception;
	/**
	 * 检查采集结果是否存在(信息id)
	 * @param dto
	 * @throws Exception
	 */
	public int isBaseInfoYN(IEntity dto) throws Exception;
	/**
	 * 检查采集结果是否存在(远程地址)
	 * @param dto
	 * @throws Exception
	 */
	public int isBaseInfoYN2(IEntity dto) throws Exception;
	/**
	 * 物理删除
	 * @param dto
	 * @throws Exception
	 */
	public int deleteBaseInfo(IEntity dto) throws Exception;
	/**
	 * 新增采集结果信息
	 * @param dto
	 * @throws Exception
	 */
	public void insertBaseInfo(IEntity dto) throws Exception;
	/**
	 * 更新采集结果信息
	 * @param dto
	 * @throws Exception
	 */
	public void updateBaseInfo(IEntity dto) throws Exception;
}
