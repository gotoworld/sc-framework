/*
 * 站点管理 service 接口类
 *
 * VERSION  DATE        BY              REASON
 * -------- ----------- --------------- ------------------------------------------
 * 1.00     2013.04.17  wuxiaogang      程序・发布
 * -------- ----------- --------------- ------------------------------------------
 * Copyright 2013 robots System. - All Rights Reserved.
 *
 */
package com.wu1g.service.robot;

import java.util.List;

import com.wu1g.bean.robot.BaseSiteBean;
import com.wu1g.bean.robot.DbConfigBean;


/**
 * 站点管理  service 接口类
 * @author wuxiaogang
 *
 */
public interface IBaseSiteManager {
	
	/**
	 * <p>站点信息编辑。</p>
	 * <ol>[功能概要] 
	 * <div>新增站点信息。</div>
	 * <div>修改站点信息。</div>
	 * </ol>
	 * @return 处理结果
	 */
	public String saveOrUpdateBaseSite(BaseSiteBean bean);
	/**
	 * <p>站点信息列表 分页。</p>
	 * <ol>[功能概要] 
	 * <div>信息检索。</div>
	 * <div>分页。</div>
	 * </ol>
	 * @return 处理结果
	 */
	public List<BaseSiteBean> findBaseSiteBeanIsPage(BaseSiteBean bean);
	/**
	 * <p>站点信息列表。</p>
	 * <ol>[功能概要] 
	 * <div>信息检索。</div>
	 * <div>列表。</div>
	 * </ol>
	 * @return 处理结果
	 */
	public List<BaseSiteBean> findBaseSiteBeanIsList(BaseSiteBean bean);
	/**
	 * <p>站点信息详情。</p>
	 * <ol>[功能概要] 
	 * <div>信息检索。</div>
	 * <div>详情。</div>
	 * </ol>
	 * @return 处理结果
	 */
	public BaseSiteBean findBaseSiteBeanById(BaseSiteBean bean);
	/**
	 * <p>配置信息。</p>
	 * <ol>[功能概要] 
	 * <div>获取配置信息。</div>
	 * </ol>
	 * @return 处理结果
	 */
	public String getJsonConfig(DbConfigBean dbConfigBean);
}
