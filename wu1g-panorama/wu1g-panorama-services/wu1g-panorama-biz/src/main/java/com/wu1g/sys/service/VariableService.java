/*	
 * 数据字典   业务处理实现类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.12.14      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 baseos wxqy demo  System. - All Rights Reserved.		
 *	
 */
package com.wu1g.sys.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.baseos.bean.sys.SysVariable;
import cn.com.baseos.common.CommonConstant;
import cn.com.baseos.common.IdUtil;
import cn.com.baseos.common.ValidatorUtil;
import cn.com.baseos.dao.daointer.sys.ISysVariableDao;
import cn.com.baseos.service.sys.IVariableService;

/**
 * <p>
 * 数据字典 业务处理实现类。
 * </p>
 * <ol>
 * [功能概要] <li>编辑(新增or修改)。 <li>详情检索。 <li>分页检索。
 * <li>列表检索。 <li>逻辑删除。 <li>物理删除。 <li>恢复逻辑删除。
 * </ol>
 * 
 * @author easycode
 */
@Service
public class VariableService implements IVariableService {

	private static final transient Logger	log	= Logger.getLogger( VariableService.class );
	/** 数据字典 Dao接口类 */
	@Autowired
	private ISysVariableDao					SysVariableDao;

	/**
	 * <p>
	 * 信息编辑。
	 * </p>
	 * <ol>
	 * [功能概要] <li>新增信息。 <li>修改信息。
	 * </ol>
	 * 
	 * @return 处理结果
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = { Exception.class, RuntimeException.class })
	public String saveOrUpdateData(SysVariable bean) throws Exception {
		String msg = "1";
		if (bean != null) {
			try {
				// 判断数据是否存在
				if (SysVariableDao.isDataYN( bean ) != 0) {
					// 数据存在
					SysVariableDao.updateByPrimaryKeySelective( bean );
				} else {
					// 新增
					if (ValidatorUtil.isEmpty( bean.getId() )) {
						bean.setId( IdUtil.createUUID( 32 ) );
					}
					SysVariableDao.insert( bean );
				}
			} catch (Exception e) {
				msg = "信息保存失败,数据库处理错误!";
				log.error( msg, e );
				throw new Exception( msg );
			}
		}
		return msg;
	}

	/**
	 * <p>
	 * 信息编辑。
	 * </p>
	 * <ol>
	 * [功能概要] <li>物理删除。
	 * </ol>
	 * 
	 * @return 处理结果
	 */
	public String deleteData(SysVariable bean) throws Exception {
		String msg = "1";
		if (bean != null) {
			try {
				SysVariableDao.deleteByPrimaryKey( bean );
			} catch (Exception e) {
				msg = "信息删除失败,数据库处理错误!";
				log.error( msg, e );
			}
		}
		return msg;
	}

	/**
	 * <p>
	 * 信息 单条。
	 * </p>
	 * <ol>
	 * [功能概要] <li>逻辑删除。
	 * </ol>
	 * 
	 * @return 处理结果
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = { Exception.class, RuntimeException.class })
	public String deleteDataById(SysVariable bean) throws Exception {
		String msg = "1";
		if (bean != null) {
			try {
				SysVariableDao.deleteById( bean );
			} catch (Exception e) {
				msg = "信息删除失败,数据库处理错误!";
				log.error( msg, e );
				throw new Exception( msg );
			}
		}
		return msg;
	}

	/**
	 * <p>
	 * 信息列表 分页。
	 * </p>
	 * <ol>
	 * [功能概要] <li>信息检索。 <li>分页。
	 * </ol>
	 * 
	 * @return 处理结果
	 */
	public List<SysVariable> findDataIsPage(SysVariable bean) {
		List<SysVariable> beans = null;
		try {
			beans = (List<SysVariable>) SysVariableDao.findDataIsPage( bean );
		} catch (Exception e) {
			log.error( "信息查询失败,数据库错误!", e );
		}
		return beans;
	}

	/**
	 * <p>
	 * 信息列表。
	 * </p>
	 * <ol>
	 * [功能概要] <li>信息检索。 <li>列表。
	 * </ol>
	 * 
	 * @return 处理结果
	 */
	public List<SysVariable> findDataIsList(SysVariable bean) {
		List<SysVariable> beans = null;
		try {
			beans = (List<SysVariable>) SysVariableDao.findDataIsList( bean );
		} catch (Exception e) {
			log.error( "信息查询失败,数据库错误!", e );
		}
		return beans;
	}

	/**
	 * <p>
	 * 信息详情。
	 * </p>
	 * <ol>
	 * [功能概要] <li>信息检索。 <li>详情。
	 * </ol>
	 * 
	 * @return 处理结果
	 */
	public SysVariable findDataById(SysVariable bean) {
		SysVariable bean1 = null;
		try {
			bean1 = (SysVariable) SysVariableDao.selectByPrimaryKey( bean );
			// if(bean1!=null && ValidatorUtil.notEmpty(bean1.getDetailInfo())){
			// bean1.setDetailInfo(IOHelper.readHtml(bean1.getDetailInfo()));
			// }
		} catch (Exception e) {
			log.error( "信息详情查询失败,数据库错误!", e );
		}
		return bean1;
	}

	/**
	 * <p>
	 * 信息 单条。
	 * </p>
	 * <ol>
	 * [功能概要] <li>恢复逻辑删除的数据。
	 * </ol>
	 * 
	 * @return 处理结果
	 */
	public String recoveryDataById(SysVariable bean) throws Exception {
		String msg = "1";
		if (bean != null) {
			try {
				SysVariableDao.recoveryDataById( bean );
			} catch (Exception e) {
				msg = "信息恢复失败,数据库处理错误!";
				log.error( msg, e );
				throw new Exception( msg );
			}
		}
		return msg;
	}

	/**
	 * 信息DAO 接口类取得
	 * 
	 * @return 信息DAO 接口类
	 */
	public ISysVariableDao getSysVariableDao() {
		return SysVariableDao;
	}

	/**
	 * 信息DAO 接口类设定
	 * 
	 * @param SysVariableDao
	 *            信息DAO 接口类
	 */
	public void setSysVariableDao(ISysVariableDao SysVariableDao) {
		this.SysVariableDao = SysVariableDao;
	}
}