/*
 * 采集结果管理 service 接口类
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

import com.wu1g.bean.robot.BaseInfoBean;


/**
 * 采集结果管理  service 接口类
 * @author wuxiaogang
 *
 */
public interface IBaseInfoManager {
	
	/**
	 * <p>采集结果信息编辑。</p>
	 * <ol>[功能概要] 
	 * <div>新增采集结果信息。</div>
	 * <div>修改采集结果信息。</div>
	 * </ol>
	 * @return 处理结果
	 */
	public String saveOrUpdateBaseInfo(BaseInfoBean bean);
	/**
	 * <p>采集结果信息编辑。</p>
	 * <ol>[功能概要] 
	 * <div>新增采集结果信息。</div>
	 * </ol>
	 * @return 处理结果
	 */
	public String saveBaseInfo(BaseInfoBean bean);
	/**
	 * <p>采集结果信息编辑。</p>
	 * <ol>[功能概要] 
	 * <div>物理删除。</div>
	 * </ol>
	 * @return 处理结果
	 */
	public String xxDelBaseInfo(BaseInfoBean bean) throws Exception;
	/**
	 * <p>采集结果信息列表 分页。</p>
	 * <ol>[功能概要] 
	 * <div>信息检索。</div>
	 * <div>分页。</div>
	 * </ol>
	 * @return 处理结果
	 */
	public List<BaseInfoBean> findBaseInfoBeanIsPage(BaseInfoBean bean);
	/**
	 * <p>采集结果信息列表 分页。</p>
	 * <ol>[功能概要] 
	 * <div>信息检索。</div>
	 * <div>分页。</div>
	 * </ol>
	 * @return 处理结果
	 */
	public List<BaseInfoBean> findBaseInfoBeanIsPage2(BaseInfoBean bean);
	/**
	 * <p>采集结果信息列表。</p>
	 * <ol>[功能概要] 
	 * <div>信息检索。</div>
	 * <div>列表。</div>
	 * </ol>
	 * @return 处理结果
	 */
	public List<BaseInfoBean> findBaseInfoBeanIsList(BaseInfoBean bean);
	/**
	 * <p>采集结果信息详情。</p>
	 * <ol>[功能概要] 
	 * <div>信息检索。</div>
	 * <div>详情。</div>
	 * </ol>
	 * @return 处理结果
	 */
	public BaseInfoBean findBaseInfoBeanById(BaseInfoBean bean);
}
