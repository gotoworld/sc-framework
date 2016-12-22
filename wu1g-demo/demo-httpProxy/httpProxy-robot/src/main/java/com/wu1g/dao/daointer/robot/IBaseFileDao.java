/*
 * 附件信息DAO 接口类
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

import com.wu1g.bean.robot.BaseFileBean;
import com.wu1g.dao.entity.IEntity;

/**
 * 附件信息DAO 接口类
 * @author wuxiaogang
 *
 */
public interface IBaseFileDao {
	/**
     * 附件信息列表 分页
     * @param instance
     * @return
     */
	public List<BaseFileBean> findBaseFileBeanIsPage(IEntity dto) throws Exception;
	/**
     * 附件信息列表 
     * @param instance
     * @return
     */
	public List<BaseFileBean> findBaseFileBeanIsList(IEntity dto) throws Exception;
	/**
     * 附件信息列表 
     * @param instance
     * @return
     */
	public List<BaseFileBean> findBaseFileBeanIsList2(IEntity dto) throws Exception;
	/**
     * 附件信息列
     * @param instance
     * @return
     */
	public BaseFileBean findBaseFileBeanById(IEntity dto) throws Exception;
	/**
	 * <p>根据附件远程路径获取附件本地路径。</p>
	 * <ol>[功能概要] 
	 * <div>信息检索。</div>
	 * </ol>
	 * @return 处理结果
	 */
	public List<BaseFileBean> findBaseFileBeanByRemoteUrl(IEntity dto);
	
	/**
	 * 检查附件是否存在 信息id
	 * @param dto
	 * @throws Exception
	 */
	public int isBaseFileYN(IEntity dto) throws Exception;
	/**
	 * 检查附件是否存在 远程地址
	 * @param dto
	 * @throws Exception
	 */
	public int isBaseFileYN2(IEntity dto) throws Exception;
	/**
	 * 新增附件信息
	 * @param dto
	 * @throws Exception
	 */
	public void insertBaseFile(IEntity dto) throws Exception;
	/**
	 * 更新附件信息
	 * @param dto
	 * @throws Exception
	 */
	public void updateBaseFile(IEntity dto) throws Exception;
	/**
	 * 删除附件
	 * @param dto
	 * @throws Exception
	 */
	public int deleteBaseFileByInfoId(IEntity dto) throws Exception;
}
