/*
 * 采集结果管理 service 接口实现类
 *
 * VERSION  DATE        BY              REASON
 * -------- ----------- --------------- ------------------------------------------
 * 1.00     2013.04.17  wuxiaogang      程序・发布
 * -------- ----------- --------------- ------------------------------------------
 * Copyright 2013 robots System. - All Rights Reserved.
 *
 */
package com.wu1g.service.robot.impl;

import java.util.List;

import com.wu1g.common.IdUtil;
import com.wu1g.dao.daointer.robot.IBaseFileDao;
import com.wu1g.dao.daointer.robot.IBaseInfoDao;
import com.wu1g.dao.entity.robot.BaseFile;
import com.wu1g.dao.entity.robot.BaseInfo;
import com.wu1g.service.BaseManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wu1g.bean.robot.BaseInfoBean;
import com.wu1g.common.StrUtil;
import com.wu1g.service.robot.IBaseInfoManager;

/**
 * <p>
 * 采集结果管理 service类。
 * </p>
 * <ol>
 * [功能概要] <div>信息编辑。</div> <div>信息检索。</div>
 * </ol>
 * 
 * @author wuxiaogang
 */
public class BaseInfoManager extends BaseManager implements IBaseInfoManager {
	/** 日志 */
	private static final transient Logger	log	= LogManager.getLogger( BaseInfoManager.class );

	/** 采集结果信息DAO 接口类 */
	private IBaseInfoDao baseInfoDao;
	/** 附件信息DAO 接口类 */
	private IBaseFileDao baseFileDao;

	/**
	 * 采集结果信息DAO 接口类取得
	 * 
	 * @return 采集结果信息DAO 接口类
	 */
	public IBaseInfoDao getBaseInfoDao() {
		return baseInfoDao;
	}

	/**
	 * 采集结果信息DAO 接口类设定
	 * 
	 * @param baseInfoDao
	 *            采集结果信息DAO 接口类
	 */
	public void setBaseInfoDao(IBaseInfoDao baseInfoDao) {
		this.baseInfoDao = baseInfoDao;
	}

	/**
	 * <p>
	 * 采集结果信息编辑。
	 * </p>
	 * <ol>
	 * [功能概要] <div>新增采集结果信息。</div> <div>修改采集结果信息。</div>
	 * </ol>
	 * 
	 * @return 处理结果
	 */
	public String saveOrUpdateBaseInfo(BaseInfoBean bean) {
		String msg = "1";
		if (bean != null) {
			try {
				BaseInfo dto = new BaseInfo();
				dto.setId( bean.getId() );// ID
				dto.setVersions( bean.getVersions() );// versions
				dto.setType( bean.getType() );// 类型(0:RSS,1,采集)
				dto.setTitle( StrUtil.subLeft( StrUtil.clearHtml( bean.getTitle() ), 255, null ) );// 标题
				dto.setAuthor( bean.getAuthor() );// 编辑人
				dto.setBrief_info( bean.getBrief_info() );// 简介
				dto.setDetail_info( bean.getDetail_info() );// 详情
				// 古语说得好 忍忍更健康 这里虽然花了时间处理 但是查询的时候就happy了
				dto.setDetail_info_no_html( StrUtil.subLeft( StrUtil.clearHtml( bean.getDetail_info() ), 255, null ) );// 详情(去除html)
				dto.setKeyworld( bean.getKeyworld() );// 关键字
				dto.setRemote_url( bean.getRemote_url() );// 信息来源url
				dto.setLocal_url( bean.getLocal_url() );// 本地存储路径
				dto.setPublic_date( bean.getPublic_date() );// 发布日期
				dto.setSite_id( bean.getSite_id() );// 站点ID
				dto.setStatus( bean.getStatus() );// 状态(0存储1追踪)
				dto.setDel_flag( bean.getDel_flag() );// 是否删除 0:未删除 1:已删除
				dto.setNote( bean.getNote() );// 备注
				dto.setDate_created( bean.getDate_created() );// 数据输入日期
				dto.setCreate_id( bean.getCreate_id() );// 建立者id
				dto.setCreate_ip( bean.getCreate_ip() );// 建立者ip
				dto.setLast_updated( bean.getLast_updated() );// 资料更新日期
				dto.setUpdate_id( bean.getUpdate_id() );// 修改者id
				dto.setUpdate_ip( bean.getUpdate_ip() );// 修改者ip
				// ------景区扩展-------
				dto.setA_address( bean.getA_address() );// 景区地址
				dto.setA_advice_travel_time( bean.getA_advice_travel_time() );// 景区价格
				dto.setA_price( bean.getA_price() );// 推荐出游时间
				dto.setA_satisfaction( bean.getA_satisfaction() );// 游客满意度
				// ----2次扩展---------
				dto.setInfo_type( bean.getInfo_type() );// 信息分类
				dto.setA_longitude( bean.getA_longitude() );// 经度
				dto.setA_latitude( bean.getA_latitude() );// 纬度
				dto.setA_sales_num( bean.getA_sales_num() );// 已售数量
				dto.setA_comments_num( bean.getA_comments_num() );// 评论数量
				dto.setA_scenic_pics( bean.getA_scenic_pics() );// 景区图集
				dto.setA_scenic_spot( bean.getA_scenic_spot() );// 景区亮点
				dto.setA_booking_info( bean.getA_booking_info() );// 预订须知
				dto.setA_consult_info( bean.getA_consult_info() );// 问题咨询
				dto.setA_level( bean.getA_level() );// 景区评级
				dto.setA_city( bean.getA_city() );// 所在城市/地区
				dto.setA_theme( bean.getA_theme() );// 景区主题
				dto.setA_ticket_price( bean.getA_ticket_price() );// 门票面值
				dto.setA_business_hours( bean.getA_business_hours() );// 开放时间
				dto.setA_tel( bean.getA_tel() );// 景区电话
				dto.setA_navigation_pics( bean.getA_navigation_pics() );// 导览图
				dto.setA_map( bean.getA_map() );// 地图
				dto.setA_recommend_info( bean.getA_recommend_info() );// 推荐信息
				dto.setA_lodging_info( bean.getA_lodging_info() );// 住宿信息
				dto.setA_traffic_info( bean.getA_traffic_info() );// 交通信息
				dto.setA_shopping_info( bean.getA_shopping_info() );// 购物信息
				dto.setA_strategy_info( bean.getA_strategy_info() );// 攻略
				dto.setA_review_info( bean.getA_review_info() );// 景区点评
				// 是否存在
				if (baseInfoDao.isBaseInfoYN( dto ) > 0) {
					// 存在--> 更新
					baseInfoDao.updateBaseInfo( dto );
				} else {
					// 不是更新
					if (!bean.isUpdate_flag()) {
						// 不存在--> 新增
						dto.setId( IdUtil.createUUID( 32 ) );
						//
						baseInfoDao.insertBaseInfo( dto );
					}
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
	 * 采集结果信息编辑。
	 * </p>
	 * <ol>
	 * [功能概要] <div>新增采集结果信息。</div>
	 * </ol>
	 * 
	 * @return 处理结果
	 */
	public String saveBaseInfo(BaseInfoBean bean) {
		String msg = "1";
		if (bean != null) {
			try {
				BaseInfo dto = new BaseInfo();
				dto.setId( bean.getId() );// ID
				dto.setVersions( bean.getVersions() );// versions
				dto.setType( bean.getType() );// 类型(0:RSS,1,采集)
				dto.setTitle( StrUtil.subLeft( StrUtil.clearHtml( bean.getTitle() ), 255, null ) );// 标题
				dto.setAuthor( bean.getAuthor() );// 编辑人
				dto.setBrief_info( bean.getBrief_info() );// 简介
				dto.setDetail_info( bean.getDetail_info() );// 详情
				// 古语说得好 忍忍更健康 这里虽然花了时间处理 但是查询的时候就happy了
				dto.setDetail_info_no_html( StrUtil.subLeft( StrUtil.clearHtml( bean.getDetail_info() ), 255, null ) );// 详情(去除html)
				dto.setKeyworld( bean.getKeyworld() );// 关键字
				dto.setRemote_url( bean.getRemote_url() );// 信息来源url
				dto.setLocal_url( bean.getLocal_url() );// 本地存储路径
				dto.setPublic_date( bean.getPublic_date() );// 发布日期
				dto.setSite_id( bean.getSite_id() );// 站点ID
				dto.setStatus( bean.getStatus() );// 状态(0存储1追踪)
				dto.setDel_flag( bean.getDel_flag() );// 是否删除 0:未删除 1:已删除
				dto.setNote( bean.getNote() );// 备注
				dto.setDate_created( bean.getDate_created() );// 数据输入日期
				dto.setCreate_id( bean.getCreate_id() );// 建立者id
				dto.setCreate_ip( bean.getCreate_ip() );// 建立者ip
				dto.setLast_updated( bean.getLast_updated() );// 资料更新日期
				dto.setUpdate_id( bean.getUpdate_id() );// 修改者id
				dto.setUpdate_ip( bean.getUpdate_ip() );// 修改者ip
				// ------景区扩展-------
				dto.setA_address( bean.getA_address() );// 景区地址
				dto.setA_advice_travel_time( bean.getA_advice_travel_time() );// 景区价格
				dto.setA_price( bean.getA_price() );// 推荐出游时间
				dto.setA_satisfaction( bean.getA_satisfaction() );// 游客满意度
				// ----2次扩展---------
				dto.setInfo_type( bean.getInfo_type() );// 信息分类
				dto.setA_longitude( bean.getA_longitude() );// 经度
				dto.setA_latitude( bean.getA_latitude() );// 纬度
				dto.setA_sales_num( bean.getA_sales_num() );// 已售数量
				dto.setA_comments_num( bean.getA_comments_num() );// 评论数量
				dto.setA_scenic_pics( bean.getA_scenic_pics() );// 景区图集
				dto.setA_scenic_spot( bean.getA_scenic_spot() );// 景区亮点
				dto.setA_booking_info( bean.getA_booking_info() );// 预订须知
				dto.setA_consult_info( bean.getA_consult_info() );// 问题咨询
				dto.setA_level( bean.getA_level() );// 景区评级
				dto.setA_city( bean.getA_city() );// 所在城市/地区
				dto.setA_theme( bean.getA_theme() );// 景区主题
				dto.setA_ticket_price( bean.getA_ticket_price() );// 门票面值
				dto.setA_business_hours( bean.getA_business_hours() );// 开放时间
				dto.setA_tel( bean.getA_tel() );// 景区电话
				dto.setA_navigation_pics( bean.getA_navigation_pics() );// 导览图
				dto.setA_map( bean.getA_map() );// 地图
				dto.setA_recommend_info( bean.getA_recommend_info() );// 推荐信息
				dto.setA_lodging_info( bean.getA_lodging_info() );// 住宿信息
				dto.setA_traffic_info( bean.getA_traffic_info() );// 交通信息
				dto.setA_shopping_info( bean.getA_shopping_info() );// 购物信息
				dto.setA_strategy_info( bean.getA_strategy_info() );// 攻略
				dto.setA_review_info( bean.getA_review_info() );// 景区点评
				// 新数据否
				if (baseInfoDao.isBaseInfoYN2( dto ) == 0) {
					// 不存在--> 新增
					msg = IdUtil.createUUID( 32 );
					dto.setId( msg );
					// 新数据
					baseInfoDao.insertBaseInfo( dto );
				} else {
					log.debug( "网址已找过!不存储!" );
				}
			} catch (Exception e) {
				msg = "信息保存失败,数据库处理错误!";
				log.error( "信息保存失败,数据库处理错误!", e );
			}
		}
		return msg;
	}

	/**
	 * <p>
	 * 采集结果信息编辑。
	 * </p>
	 * <ol>
	 * [功能概要] <div>物理删除。</div>
	 * </ol>
	 * 
	 * @return 处理结果
	 */
//	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = CommonConstant.DB_DEFAULT_TIMEOUT, rollbackFor = { Exception.class, RuntimeException.class })
	public String xxDelBaseInfo(BaseInfoBean bean) throws Exception {
		String msg = "1";
		if (bean != null) {
			BaseInfo dto = new BaseInfo();
			dto.setId( bean.getId() );// ID
			// 物理删除
			baseInfoDao.deleteBaseInfo( dto );

			BaseFile dto2 = new BaseFile();
			// 信息id
			dto2.setInfo_id( bean.getId() );
			baseFileDao.deleteBaseFileByInfoId( dto2 );
		}
		return msg;
	}

	/**
	 * <p>
	 * 采集结果信息列表 分页。
	 * </p>
	 * <ol>
	 * [功能概要] <div>信息检索。</div> <div>分页。</div>
	 * </ol>
	 * 
	 * @return 处理结果
	 */
	public List<BaseInfoBean> findBaseInfoBeanIsPage(BaseInfoBean bean) {
		List<BaseInfoBean> beans = null;
		try {
			BaseInfo dto = new BaseInfo();
			if (bean != null) {
				dto.setId( bean.getId() );// ID
				// dto.setVersions(bean.getVersions());//versions
				dto.setType( bean.getType() );// 类型(0:RSS,1,采集)
				dto.setTitle( bean.getTitle() );// 标题
				dto.setAuthor( bean.getAuthor() );// 编辑人
				// dto.setBrief_info(bean.getBrief_info());//简介
				// dto.setDetail_info(bean.getDetail_info());//详情
				dto.setKeyworld( bean.getKeyworld() );// 关键字
				dto.setRemote_url( bean.getRemote_url() );// 信息来源url
				// dto.setLocal_url(bean.getLocal_url());//本地存储路径
				dto.setPublic_date( bean.getPublic_date() );// 发布日期
				dto.setSite_id( bean.getSite_id() );// 站点ID
				dto.setStatus( bean.getStatus() );// 状态(0存储1追踪)
				dto.setDel_flag( bean.getDel_flag() );// 是否删除 0:未删除 1:已删除
				// dto.setNote(bean.getNote());//备注
				// dto.setDate_created(bean.getDate_created());//数据输入日期
				// dto.setCreate_id(bean.getCreate_id());//建立者id
				// dto.setCreate_ip(bean.getCreate_ip());//建立者ip
				// dto.setLast_updated(bean.getLast_updated());//资料更新日期
				// dto.setUpdate_id(bean.getUpdate_id());//修改者id
				// dto.setUpdate_ip(bean.getUpdate_ip());//修改者ip
//				dto.setPageInfo( bean.getPageInfo() );// 分页对象
				dto.setDate1( bean.getDate1() );// 开始时间
				dto.setDate2( bean.getDate2() );// 结束时间
				dto.setSite_title( bean.getSite_title() );// 站点名称
				// //------景区扩展-------
				// dto.setA_address(bean.getA_address());//景区地址
				// dto.setA_advice_travel_time(bean.getA_advice_travel_time());//景区价格
				// dto.setA_price(bean.getA_price());//推荐出游时间
				// dto.setA_satisfaction(bean.getA_satisfaction());//游客满意度
				// //----2次扩展---------
				// dto.setInfo_type(bean.getInfo_type());//信息分类
				// dto.setA_longitude(bean.getA_longitude());//经度
				// dto.setA_latitude(bean.getA_latitude());//纬度
				// dto.setA_sales_num(bean.getA_sales_num());//已售数量
				// dto.setA_comments_num(bean.getA_comments_num());//评论数量
				// dto.setA_scenic_pics(bean.getA_scenic_pics());//景区图集
				// dto.setA_scenic_spot(bean.getA_scenic_spot());//景区亮点
				// dto.setA_booking_info(bean.getA_booking_info());//预订须知
				// dto.setA_consult_info(bean.getA_consult_info());//问题咨询
				// dto.setA_level(bean.getA_level());//景区评级
				// dto.setA_city(bean.getA_city());//所在城市/地区
				// dto.setA_theme(bean.getA_theme());//景区主题
				// dto.setA_ticket_price(bean.getA_ticket_price());//门票面值
				// dto.setA_business_hours(bean.getA_business_hours());//开放时间
				// dto.setA_tel(bean.getA_tel());//景区电话
				// dto.setA_navigation_pics(bean.getA_navigation_pics());//导览图
				// dto.setA_map(bean.getA_map());//地图
				// dto.setA_recommend_info(bean.getA_recommend_info());//推荐信息
				// dto.setA_lodging_info(bean.getA_lodging_info());//住宿信息
				// dto.setA_traffic_info(bean.getA_traffic_info());//交通信息
				// dto.setA_shopping_info(bean.getA_shopping_info());//购物信息
				// dto.setA_strategy_info(bean.getA_strategy_info());//攻略
				// dto.setA_review_info(bean.getA_review_info());//景区点评
			} else {
//				PageInfo pageInfo = new PageInfo();
//				//
//				pageInfo.setCurrOffset( 0 );
//				//
//				pageInfo.setPageRowCount( 15 );
//				//
//				dto.setPageInfo( pageInfo );// 分页对象
			}
			beans = baseInfoDao.findBaseInfoBeanIsPage( dto );
		} catch (Exception e) {
			log.error( "信息查询失败!", e );
		}
		return beans;
	}

	/**
	 * <p>
	 * 采集结果信息列表 分页。
	 * </p>
	 * <ol>
	 * [功能概要] <div>信息检索。</div> <div>分页。</div>
	 * </ol>
	 * 
	 * @return 处理结果
	 */
	public List<BaseInfoBean> findBaseInfoBeanIsPage2(BaseInfoBean bean) {
		List<BaseInfoBean> beans = null;
		try {
			beans = findBaseInfoBeanIsPage( bean );
			if (beans != null) {
				BaseFile dto2 = null;
				for (BaseInfoBean bean1 : beans) {
					if (bean1 != null && !"0".equals( bean1.getImg_count() )) {
						dto2 = new BaseFile();
						dto2.setInfo_id( bean1.getId() );
						bean1.setFileBeans( baseFileDao.findBaseFileBeanIsList2( dto2 ) );
					}
				}
			}
		} catch (Exception e) {
			log.error( "信息查询失败!", e );
		}
		return beans;
	}

	/**
	 * <p>
	 * 采集结果信息列表。
	 * </p>
	 * <ol>
	 * [功能概要] <div>信息检索。</div> <div>列表。</div>
	 * </ol>
	 * 
	 * @return 处理结果
	 */
	public List<BaseInfoBean> findBaseInfoBeanIsList(BaseInfoBean bean) {
		List<BaseInfoBean> beans = null;
		try {
			BaseInfo dto = new BaseInfo();
			if (bean != null) {
				dto.setId( bean.getId() );// ID
				// dto.setVersions(bean.getVersions());//versions
				dto.setType( bean.getType() );// 类型(0:RSS,1,采集)
				dto.setTitle( bean.getTitle() );// 标题
				// dto.setAuthor(bean.getAuthor());//编辑人
				// dto.setBrief_info(bean.getBrief_info());//简介
				// dto.setDetail_info(bean.getDetail_info());//详情
				dto.setKeyworld( bean.getKeyworld() );// 关键字
				// dto.setRemote_url(bean.getRemote_url());//信息来源url
				// dto.setLocal_url(bean.getLocal_url());//本地存储路径
				dto.setPublic_date( bean.getPublic_date() );// 发布日期
				dto.setSite_id( bean.getSite_id() );// 站点ID
				dto.setStatus( bean.getStatus() );// 状态(0存储1追踪)
				dto.setDel_flag( bean.getDel_flag() );// 是否删除 0:未删除 1:已删除
				// dto.setNote(bean.getNote());//备注
				// dto.setDate_created(bean.getDate_created());//数据输入日期
				// dto.setCreate_id(bean.getCreate_id());//建立者id
				// dto.setCreate_ip(bean.getCreate_ip());//建立者ip
				// dto.setLast_updated(bean.getLast_updated());//资料更新日期
				// dto.setUpdate_id(bean.getUpdate_id());//修改者id
				// dto.setUpdate_ip(bean.getUpdate_ip());//修改者ip
				// dto.setDate1(bean.getDate1());//开始时间
				// dto.setDate2(bean.getDate2());//结束时间
				// //------景区扩展-------
				// dto.setA_address(bean.getA_address());//景区地址
				// dto.setA_advice_travel_time(bean.getA_advice_travel_time());//景区价格
				// dto.setA_price(bean.getA_price());//推荐出游时间
				// dto.setA_satisfaction(bean.getA_satisfaction());//游客满意度
				// //----2次扩展---------
				// dto.setInfo_type(bean.getInfo_type());//信息分类
				// dto.setA_longitude(bean.getA_longitude());//经度
				// dto.setA_latitude(bean.getA_latitude());//纬度
				// dto.setA_sales_num(bean.getA_sales_num());//已售数量
				// dto.setA_comments_num(bean.getA_comments_num());//评论数量
				// dto.setA_scenic_pics(bean.getA_scenic_pics());//景区图集
				// dto.setA_scenic_spot(bean.getA_scenic_spot());//景区亮点
				// dto.setA_booking_info(bean.getA_booking_info());//预订须知
				// dto.setA_consult_info(bean.getA_consult_info());//问题咨询
				// dto.setA_level(bean.getA_level());//景区评级
				// dto.setA_city(bean.getA_city());//所在城市/地区
				// dto.setA_theme(bean.getA_theme());//景区主题
				// dto.setA_ticket_price(bean.getA_ticket_price());//门票面值
				// dto.setA_business_hours(bean.getA_business_hours());//开放时间
				// dto.setA_tel(bean.getA_tel());//景区电话
				// dto.setA_navigation_pics(bean.getA_navigation_pics());//导览图
				// dto.setA_map(bean.getA_map());//地图
				// dto.setA_recommend_info(bean.getA_recommend_info());//推荐信息
				// dto.setA_lodging_info(bean.getA_lodging_info());//住宿信息
				// dto.setA_traffic_info(bean.getA_traffic_info());//交通信息
				// dto.setA_shopping_info(bean.getA_shopping_info());//购物信息
				// dto.setA_strategy_info(bean.getA_strategy_info());//攻略
				// dto.setA_review_info(bean.getA_review_info());//景区点评
			}
			beans = baseInfoDao.findBaseInfoBeanIsList( dto );
		} catch (Exception e) {
			log.error( "信息查询失败!", e );
		}
		return beans;
	}

	/**
	 * <p>
	 * 采集结果信息详情。
	 * </p>
	 * <ol>
	 * [功能概要] <div>信息检索。</div> <div>详情。</div>
	 * </ol>
	 * 
	 * @return 处理结果
	 */
	public BaseInfoBean findBaseInfoBeanById(BaseInfoBean bean) {
		BaseInfoBean bean1 = null;
		try {
			BaseInfo dto = new BaseInfo();
			if (bean != null) {
				dto.setId( bean.getId() );// ID
				// dto.setVersions(bean.getVersions());//versions
				// dto.setType(bean.getType());//类型(0:RSS,1,采集)
				// dto.setTitle(bean.getTitle());//标题
				// dto.setAuthor(bean.getAuthor());//编辑人
				// dto.setBrief_info(bean.getBrief_info());//简介
				// dto.setDetail_info(bean.getDetail_info());//详情
				// dto.setKeyworld(bean.getKeyworld());//关键字
				// dto.setRemote_url(bean.getRemote_url());//信息来源url
				// dto.setLocal_url(bean.getLocal_url());//本地存储路径
				// dto.setPublic_date(bean.getPublic_date());//发布日期
				// dto.setSite_id(bean.getSite_id());//站点ID
				// dto.setStatus(bean.getStatus());//状态(0存储1追踪)
				// dto.setDel_flag(bean.getDel_flag());//是否删除 0:未删除 1:已删除
				// dto.setNote(bean.getNote());//备注
				// dto.setDate_created(bean.getDate_created());//数据输入日期
				// dto.setCreate_id(bean.getCreate_id());//建立者id
				// dto.setCreate_ip(bean.getCreate_ip());//建立者ip
				// dto.setLast_updated(bean.getLast_updated());//资料更新日期
				// dto.setUpdate_id(bean.getUpdate_id());//修改者id
				// dto.setUpdate_ip(bean.getUpdate_ip());//修改者ip
				// dto.setDate1(bean.getDate1());//开始时间
				// dto.setDate2(bean.getDate2());//结束时间
				// //------景区扩展-------
				// dto.setA_address(bean.getA_address());//景区地址
				// dto.setA_advice_travel_time(bean.getA_advice_travel_time());//景区价格
				// dto.setA_price(bean.getA_price());//推荐出游时间
				// dto.setA_satisfaction(bean.getA_satisfaction());//游客满意度
				// //----2次扩展---------
				// dto.setInfo_type(bean.getInfo_type());//信息分类
				// dto.setA_longitude(bean.getA_longitude());//经度
				// dto.setA_latitude(bean.getA_latitude());//纬度
				// dto.setA_sales_num(bean.getA_sales_num());//已售数量
				// dto.setA_comments_num(bean.getA_comments_num());//评论数量
				// dto.setA_scenic_pics(bean.getA_scenic_pics());//景区图集
				// dto.setA_scenic_spot(bean.getA_scenic_spot());//景区亮点
				// dto.setA_booking_info(bean.getA_booking_info());//预订须知
				// dto.setA_consult_info(bean.getA_consult_info());//问题咨询
				// dto.setA_level(bean.getA_level());//景区评级
				// dto.setA_city(bean.getA_city());//所在城市/地区
				// dto.setA_theme(bean.getA_theme());//景区主题
				// dto.setA_ticket_price(bean.getA_ticket_price());//门票面值
				// dto.setA_business_hours(bean.getA_business_hours());//开放时间
				// dto.setA_tel(bean.getA_tel());//景区电话
				// dto.setA_navigation_pics(bean.getA_navigation_pics());//导览图
				// dto.setA_map(bean.getA_map());//地图
				// dto.setA_recommend_info(bean.getA_recommend_info());//推荐信息
				// dto.setA_lodging_info(bean.getA_lodging_info());//住宿信息
				// dto.setA_traffic_info(bean.getA_traffic_info());//交通信息
				// dto.setA_shopping_info(bean.getA_shopping_info());//购物信息
				// dto.setA_strategy_info(bean.getA_strategy_info());//攻略
				// dto.setA_review_info(bean.getA_review_info());//景区点评
				//
				bean1 = baseInfoDao.findBaseInfoBeanById( dto );
			}
		} catch (Exception e) {
			log.error( "信息查询失败!", e );
		}
		return bean1;
	}

	/**
	 * 附件信息DAO 接口类取得
	 * 
	 * @return 附件信息DAO 接口类
	 */
	public IBaseFileDao getBaseFileDao() {
		return baseFileDao;
	}

	/**
	 * 附件信息DAO 接口类设定
	 * 
	 * @param baseFileDao
	 *            附件信息DAO 接口类
	 */
	public void setBaseFileDao(IBaseFileDao baseFileDao) {
		this.baseFileDao = baseFileDao;
	}
}
