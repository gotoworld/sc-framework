/*	
 * 系统_管理员操作日志   业务处理实现类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 isd System. - All Rights Reserved.		
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

import com.github.pagehelper.PageHelper;

import cn.com.baseos.bean.sys.SysUserLog;
import cn.com.baseos.common.CommonConstant;
import cn.com.baseos.common.IdUtil;
import cn.com.baseos.common.ValidatorUtil;
import cn.com.baseos.dao.daointer.sys.ISysUserLogDao;
import cn.com.baseos.service.sys.ISysUserLogService;

/**
 * <p>
 * 系统_管理员操作日志 业务处理实现类。
 * </p>
 * <ol>
 * [功能概要] <li>编辑(新增or修改)。 
 * <li>详情检索。
 *  <li>分页检索。
 * <li>列表检索。 
 * <li>逻辑删除。 
 * <li>物理删除。
 *  <li>恢复逻辑删除。
 * </ol>
 * 
 * @author easycode
 */
@Service("sysUserLogService")
public class SysUserLogService implements ISysUserLogService {

	private static final transient Logger	log	= Logger.getLogger( SysUserLogService.class );
	/** 系统_管理员操作日志 Dao接口类 */
	@Autowired
	private ISysUserLogDao					sysUserLogDao;

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
	public String saveOrUpdateData(SysUserLog bean) throws Exception {
		String msg = "1";
		if (bean != null) {
			try {
				// 判断数据是否存在
				if (sysUserLogDao.isDataYN( bean ) != 0) {
					// 数据存在
					sysUserLogDao.updateByPrimaryKeySelective( bean );
				} else {
					// 新增
					if (ValidatorUtil.isEmpty( bean.getId() )) {
						bean.setId( IdUtil.createUUID( 32 ) );
					}
					sysUserLogDao.insert( bean );
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
	 * 信息列表 分页。
	 * </p>
	 * <ol>
	 * [功能概要] <li>信息检索。 <li>分页。
	 * </ol>
	 * 
	 * @return 处理结果
	 */
	public List<SysUserLog> findDataIsPage(SysUserLog bean) {
		List<SysUserLog> beans = null;
		try {
			PageHelper.startPage( bean.getPageNum(), bean.getPageSize() );
			beans = (List<SysUserLog>) sysUserLogDao.findDataIsPage( bean );
		} catch (Exception e) {
			log.error( "信息查询失败,数据库错误!", e );
		}
		return beans;
	}

	/**
	 * 信息DAO 接口类取得
	 * 
	 * @return 信息DAO 接口类
	 */
	public ISysUserLogDao getSysUserLogDao() {
		return sysUserLogDao;
	}

	/**
	 * 信息DAO 接口类设定
	 * 
	 * @param sysUserLogDao
	 *            信息DAO 接口类
	 */
	public void setSysUserLogDao(ISysUserLogDao sysUserLogDao) {
		this.sysUserLogDao = sysUserLogDao;
	}

	/**
	 * 管理员操作日志记录。
	 * 
	 * @param type
	 *            操作类型
	 * @param memo
	 *            具体描述
	 * @param userId
	 *            用户id
	 * @param ip
	 *            用户ip
	 * @return
	 */
	public void info(String type, String memo, String userId, String ip) {
		try {
			SysUserLog dto = new SysUserLog();
			dto.setId( IdUtil.createUUID( 32 ) );
			dto.setType( type );// 操作类型(a增d删u改q查)
			dto.setDescription( memo );// 具体描述
			dto.setCreateId( userId );// 建立者ID
			dto.setCreateIp( ip );// 建立者IP
			// 新增
			sysUserLogDao.insert( dto );
		} catch (Exception e) {
			log.error( "操作日志信息保存失败,数据库处理错误!", e );
		}
	}
}