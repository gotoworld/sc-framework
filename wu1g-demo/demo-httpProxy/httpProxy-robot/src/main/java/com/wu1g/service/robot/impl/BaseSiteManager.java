/*
 * 站点管理 service 接口实现类
 *
 * VERSION  DATE        BY              REASON
 * -------- ----------- --------------- ------------------------------------------
 * 1.00     2013.04.17  wuxiaogang      程序・发布
 * 2.00     2013.06.18  wuxiaogang      程序・更新  saveOrUpdateBaseSite
 * -------- ----------- --------------- ------------------------------------------
 * Copyright 2013 robots System. - All Rights Reserved.
 *
 */
package com.wu1g.service.robot.impl;

import java.util.List;
import java.util.Map;

import com.wu1g.bean.robot.DbConfigBean;
import com.wu1g.service.robot.IBaseSiteManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wu1g.bean.robot.BaseSiteBean;
import com.wu1g.common.IdUtil;
import com.wu1g.common.ValidatorUtil;
import com.wu1g.dao.daointer.robot.IBaseSiteDao;
import com.wu1g.dao.entity.robot.BaseSite;
import com.wu1g.service.BaseManager;

/**
 * <p>
 * 站点管理 service类。
 * </p>
 * <ol>
 * [功能概要] <div>信息编辑。</div> <div>信息检索。</div>
 * </ol>
 * 
 * @author wuxiaogang
 */
public class BaseSiteManager extends BaseManager implements IBaseSiteManager {
	/** 日志 */
	private static final transient Logger	log	= LogManager.getLogger( BaseSiteManager.class );

	/** 站点信息DAO 接口类 */
	private IBaseSiteDao					baseSiteDao;

	/**
	 * 站点信息DAO 接口类取得
	 * 
	 * @return 站点信息DAO 接口类
	 */
	public IBaseSiteDao getBaseSiteDao() {
		return baseSiteDao;
	}

	/**
	 * 站点信息DAO 接口类设定
	 * 
	 * @param baseSiteDao
	 *            站点信息DAO 接口类
	 */
	public void setBaseSiteDao(IBaseSiteDao baseSiteDao) {
		this.baseSiteDao = baseSiteDao;
	}

	/**
	 * <p>
	 * 站点信息编辑。
	 * </p>
	 * <ol>
	 * [功能概要] <div>新增站点信息。</div> <div>修改站点信息。</div>
	 * </ol>
	 * 
	 * @return 处理结果
	 */
	public String saveOrUpdateBaseSite(BaseSiteBean bean) {
		String msg = "1";
		if (bean != null) {
			try {
				BaseSite dto = new BaseSite();
				dto.setId( bean.getId() );// ID
				dto.setVersions( bean.getVersions() );// versions
				dto.setTitle( bean.getTitle() );// 站点名称
				dto.setKeyworld( bean.getKeyworld() );// 关键字
				dto.setUrl( bean.getUrl() );// url
				dto.setConfig( bean.getConfig() );// 采集内容配置
				dto.setCharset( bean.getCharset() );// 站点编码
				dto.setType( bean.getType() );// 类型(0:RSS,1,采集)
				dto.setWeb_depth( bean.getWeb_depth() );// 检索深度
				dto.setS_time( bean.getS_time() );// 采集开始时间
				dto.setAuto_run_num( bean.getAuto_run_num() );// 自动采集数量
				dto.setInfo_type( bean.getInfo_type() );// 站点信息分类
				dto.setThread_sleep( bean.getThread_sleep() );// 线程执行间隔
				dto.setThread_flag( bean.getThread_flag() );// 线程执行标记
				dto.setStatus( bean.getStatus() );// 状态(0停用1启用)
				dto.setDel_flag( bean.getDel_flag() );// 是否删除 0:未删除 1:已删除
				dto.setNote( bean.getNote() );// 备注
				dto.setDate_created( bean.getDate_created() );// 数据输入日期
				dto.setCreate_id( bean.getCreate_id() );// 建立者id
				dto.setCreate_ip( bean.getCreate_ip() );// 建立者ip
				dto.setLast_updated( bean.getLast_updated() );// 资料更新日期
				dto.setUpdate_id( bean.getUpdate_id() );// 修改者id
				dto.setUpdate_ip( bean.getUpdate_ip() );// 修改者ip
				// 是否存在
				if (baseSiteDao.isBaseSiteYN( dto ) > 0) {
					// 存在--> 更新
					baseSiteDao.updateBaseSite( dto );
				} else {
					// 不存在--> 新增
					dto.setId( IdUtil.createUUID( 32 ) );
					//
					baseSiteDao.insertBaseSite( dto );
				}
			} catch (Exception e) {
				msg = "信息保存失败,数据库处理错误!";
				log.error( msg, e );
			}
		}
		return msg;
	}

	/**
	 * <p>
	 * 站点信息列表 分页。
	 * </p>
	 * <ol>
	 * [功能概要] <div>信息检索。</div> <div>分页。</div>
	 * </ol>
	 * 
	 * @return 处理结果
	 */
	public List<BaseSiteBean> findBaseSiteBeanIsPage(BaseSiteBean bean) {
		List<BaseSiteBean> beans = null;
		try {
			BaseSite dto = new BaseSite();
			if (bean != null) {
				dto.setId( bean.getId() );// ID
				dto.setTitle( bean.getTitle() );// 站点名称
				dto.setKeyworld( bean.getKeyworld() );// 关键字
				dto.setUrl( bean.getUrl() );// url
				dto.setType( bean.getType() );// 类型(0:RSS,1,采集)
				dto.setWeb_depth( bean.getWeb_depth() );// 检索深度
				dto.setStatus( bean.getStatus() );// 状态(0停用1启用)
				dto.setDel_flag( bean.getDel_flag() );// 是否删除 0:未删除 1:已删除
//				dto.setPageInfo( bean.getPageInfo() );// 分页对象
			} else {
//				PageInfo pageInfo = new PageInfo();
//				//
//				pageInfo.setCurrOffset( 0 );
//				//
//				pageInfo.setPageRowCount( 15 );
//				//
//				dto.setPageInfo( pageInfo );// 分页对象
			}
			beans = baseSiteDao.findBaseSiteBeanIsPage( dto );
		} catch (Exception e) {
			log.error( "信息查询失败!", e );
		}
		return beans;
	}

	/**
	 * <p>
	 * 站点信息列表。
	 * </p>
	 * <ol>
	 * [功能概要] <div>信息检索。</div> <div>列表。</div>
	 * </ol>
	 * 
	 * @return 处理结果
	 */
	public List<BaseSiteBean> findBaseSiteBeanIsList(BaseSiteBean bean) {
		List<BaseSiteBean> beans = null;
		try {
			BaseSite dto = new BaseSite();
			if (bean != null) {
				dto.setId( bean.getId() );// ID
				dto.setTitle( bean.getTitle() );// 站点名称
				dto.setKeyworld( bean.getKeyworld() );// 关键字
				dto.setUrl( bean.getUrl() );// url
				dto.setType( bean.getType() );// 类型(0:RSS,1,采集)
				dto.setWeb_depth( bean.getWeb_depth() );// 检索深度
				dto.setStatus( bean.getStatus() );// 状态(0停用1启用)
				dto.setDel_flag( bean.getDel_flag() );// 是否删除 0:未删除 1:已删除
			}
			beans = baseSiteDao.findBaseSiteBeanIsList( dto );
		} catch (Exception e) {
			log.error( "信息查询失败!", e );
		}
		return beans;
	}

	/**
	 * <p>
	 * 站点信息详情。
	 * </p>
	 * <ol>
	 * [功能概要] <div>信息检索。</div> <div>详情。</div>
	 * </ol>
	 * 
	 * @return 处理结果
	 */
	public BaseSiteBean findBaseSiteBeanById(BaseSiteBean bean) {
		BaseSiteBean bean1 = null;
		try {
			BaseSite dto = new BaseSite();
			if (bean != null) {
				dto.setId( bean.getId() );// ID
				dto.setTitle( bean.getTitle() );// 站点名称
				dto.setKeyworld( bean.getKeyworld() );// 关键字
				dto.setUrl( bean.getUrl() );// url
				dto.setType( bean.getType() );// 类型(0:RSS,1,采集)
				dto.setWeb_depth( bean.getWeb_depth() );// 检索深度
				dto.setStatus( bean.getStatus() );// 状态(0停用1启用)
				dto.setDel_flag( bean.getDel_flag() );// 是否删除 0:未删除 1:已删除

				//
				bean1 = baseSiteDao.findBaseSiteBeanById( dto );
			}
		} catch (Exception e) {
			log.error( "信息查询失败!", e );
		}
		return bean1;
	}

	/**
	 * <p>
	 * 配置信息。
	 * </p>
	 * <ol>
	 * [功能概要] <div>获取配置信息。</div>
	 * </ol>
	 * 
	 * @return 处理结果
	 */
	public String getJsonConfig(DbConfigBean dbConfigBean) {
		StringBuffer json = new StringBuffer( "{" );
		// 数据库字段
		String[] columns = dbConfigBean.getColumns();
		// 标签
		Map<String, String> columns_tag = dbConfigBean.getColumns_tag();
		// 属性
		Map<String, String> columns_pro = dbConfigBean.getColumns_pro();
		// 属性值
		Map<String, String> columns_val = dbConfigBean.getColumns_val();
		// 第几个
		Map<String, String> columns_num = dbConfigBean.getColumns_num();
		// 标签 (内层)
		Map<String, String> columns_a_tag = dbConfigBean.getColumns_a_tag();
		// 属性 (内层)
		Map<String, String> columns_a_pro = dbConfigBean.getColumns_a_pro();
		// 属性值 (内层)
		Map<String, String> columns_a_val = dbConfigBean.getColumns_a_val();
		// 第几个 (内层)
		Map<String, String> columns_a_num = dbConfigBean.getColumns_a_num();
		// 图片真实路径(属性)
		Map<String, String> columns_real_src = dbConfigBean.getColumns_real_src();
		// 是否抓取标签本身0否1是
		Map<String, String> columns_is_self = dbConfigBean.getColumns_is_self();
		// 纯文本标记
		Map<String, String> columns_is_html = dbConfigBean.getColumns_is_html();
		// 字符截取标记0否1是
		Map<String, String> columns_is_sub = dbConfigBean.getColumns_is_sub();
		// (字符截取)开始字符串
		Map<String, String> columns_s_str = dbConfigBean.getColumns_s_str();
		// (字符截取)结束字符串
		Map<String, String> columns_e_str = dbConfigBean.getColumns_e_str();
		// 字符替换标记0否1是
		Map<String, String> columns_is_replace = dbConfigBean.getColumns_is_replace();
		// 替换字符 旧
		Map<String, String> columns_str_old = dbConfigBean.getColumns_str_old();
		// 替换字符 新
		Map<String, String> columns_str_new = dbConfigBean.getColumns_str_new();
		/*************************** begin *************************************************/
		if (columns != null) {
			for (int i = 0; i < columns.length; i++) {
				// ---------- 数据库字段---------
				json.append( "\"" + columns[ i ] + "\":{" );
				if (ValidatorUtil.notEmpty( columns_tag.get( columns[ i ] ) )) {
					// ---------- 标签 ---------
					json.append( "\"tag\":\"" + columns_tag.get( columns[ i ] ) + "\"," );
					// ---------- 属性 ---------
					json.append( "\"pro\":\"" + columns_pro.get( columns[ i ] ) + "\"," );
					// ---------- 属性值 ---------
					json.append( "\"val\":\"" + columns_val.get( columns[ i ] ) + "\"," );
					// ---------- 标签 (内层)---------
					json.append( "\"a_tag\":\"" + columns_a_tag.get( columns[ i ] ) + "\"," );
					// ---------- 属性 (内层) ---------
					json.append( "\"a_pro\":\"" + columns_a_pro.get( columns[ i ] ) + "\"," );
					// ---------- 属性值 (内层) ---------
					json.append( "\"a_val\":\"" + columns_a_val.get( columns[ i ] ) + "\"," );
					// ---------- 图片真实路径(属性) ---------
					if (ValidatorUtil.notEmpty( columns_real_src.get( columns[ i ] ) )) {
						json.append( "\"real_src\":\"" + columns_real_src.get( columns[ i ] ) + "\"," );
					}
					// ---------- 是否抓取标签本身0否1是 ---------
					if (columns_is_self.get( columns[ i ] ) != null) {
						json.append( "\"is_self\":\"1\"," );
					}
					// ---------- 纯文本标记 ---------
					if (columns_is_html.get( columns[ i ] ) != null) {
						json.append( "\"is_html\":\"1\"," );
					}
					// ---------- 字符截取标记0否1是 ---------
					if (columns_is_sub.get( columns[ i ] ) != null) {
						json.append( "\"is_sub\":\"1\"," );
						// ---------- (字符截取)开始字符串 ---------
						json.append( "\"s_str\":\"" + columns_s_str.get( columns[ i ] ) + "\"," );
						// ---------- (字符截取)结束字符串 ---------
						json.append( "\"e_str\":\"" + columns_e_str.get( columns[ i ] ) + "\"," );
					}
					// ---------- 字符替换标记0否1是 ---------
					if (columns_is_replace.get( columns[ i ] ) != null) {
						json.append( "\"is_replace\":\"1\"," );
						// ---------- 替换字符 旧 ---------
						json.append( "\"str_old\":\"" + columns_str_old.get( columns[ i ] ) + "\"," );
						// ---------- 替换字符 新 ---------
						json.append( "\"str_new\":\"" + columns_str_new.get( columns[ i ] ) + "\"," );
					}
					// ---------- 第几个 ---------
					json.append( "\"num\":\"" + columns_num.get( columns[ i ] ) + "\"," );
					// ---------- 第几个 (内层)---------
					json.append( "\"a_num\":\"" + columns_a_num.get( columns[ i ] ) + "\"" );
				}
				//
				json.append( "}" );
				if (i < columns.length - 1) {
					json.append( "," );
				}
			}
		}
		/**************************** end **************************************************/
		json.append( "}" );
		return json.toString();
	}
}
