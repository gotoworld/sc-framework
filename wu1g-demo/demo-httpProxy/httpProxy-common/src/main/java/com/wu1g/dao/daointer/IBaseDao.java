/*
 * 基础DAO 接口类
 *
 * VERSION  DATE        BY              REASON
 * -------- ----------- --------------- ------------------------------------------
 * 1.00     2013.05.30  wuxiaogang      程序・发布
 * -------- ----------- --------------- ------------------------------------------
 * Copyright 2014 yysh  system. - All Rights Reserved.
 *
 */
package com.wu1g.dao.daointer;

import java.util.List;

import com.wu1g.dao.entity.IEntity;

/**
 * 基础DAO 接口类
 */
public interface IBaseDao {
	/**
     * 根据主键 物理删除
     */
    int deleteByPrimaryKey(IEntity dto) throws Exception;
    /**
     * 新增
     */
    int insert(IEntity dtos) throws Exception;
    /**
     * 新增 批量
     */
    int insertBatch(List<?> dtos) throws Exception;
    /**
     * 详情
     */
    Object selectByPrimaryKey(IEntity dto) throws Exception;
    /**
     * 更新
     */
    int updateByPrimaryKeySelective(IEntity dto) throws Exception;
    /**
     * 判断是否存在
     */
    int isDataYN(IEntity dto) throws Exception;
    /**
     * 逻辑删除
     */
    int deleteById(IEntity dto) throws Exception;
    /**
     * 恢复逻辑删除的数据
     */
    int recoveryDataById(IEntity dto) throws Exception;
    /**
	 * <p>信息列表 分页。</p>
	 * <ol>[功能概要] 
	 * <div>信息检索。</div>
	 * <div>分页。</div>
	 * </ol>
	 * @return 处理结果
	 */
	public List<?> findDataIsPage(IEntity dto) throws Exception;
	/**
	 * <p>信息列表。</p>
	 * <ol>[功能概要] 
	 * <div>信息检索。</div>
	 * <div>列表。</div>
	 * </ol>
	 * @return 处理结果
	 */
	public List<?> findDataIsList(IEntity dto) throws Exception;
    
}
