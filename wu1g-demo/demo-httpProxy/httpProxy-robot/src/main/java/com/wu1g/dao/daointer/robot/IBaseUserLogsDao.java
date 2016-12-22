/*
 *  管理员操作日志表DAO 接口类
 *
 * VERSION  		DATE       			 BY              REASON
 * -------- ----------- --------------- ------------------------------------------
 * 1.00     	    2013.04.12  	 	wuxiaogang       程序・发布                 
 * -------- ----------- --------------- ------------------------------------------
 * Copyright 2013 robots System. - All Rights Reserved.
 *
 */
package com.wu1g.dao.daointer.robot;

import java.util.List;

import com.wu1g.bean.robot.BaseUserLogsBean;
import com.wu1g.dao.entity.IEntity;

/**
 * 管理员操作日志表DAO 接口类
 * @author wuxiaogang
 */
public interface IBaseUserLogsDao{
	//需要缓存的DAO类上添加@CacheNamespace(implementation=MybatisRedisCache.class )
	/**
	 * 保存管理员操作日志
	 * 
	 * @param bean
	 * @return
	 */
	public void insertUserLogs(IEntity dto) throws Exception;

	/**
	 * 删除管理员操作日志
	 * 
	 * @param bean
	 * @return
	 */
	public void deleteUserLogs(IEntity dto) throws Exception;

	/**
	 * 分页展示管理员操作日志
	 * 
	 * @param bean
	 * @return
	 */
	public List<BaseUserLogsBean> findBaseUserLogsbeanIsPage(IEntity dto) throws Exception;

	/**
	 * 管理员操作日志 详情
	 * 
	 * @param bean
	 * @return
	 */
	public BaseUserLogsBean findBaseUserLogsbeanById(IEntity dto) throws Exception;
}
