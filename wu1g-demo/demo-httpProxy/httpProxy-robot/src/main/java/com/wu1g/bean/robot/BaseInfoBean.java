/*
 * 搜索结果集信息 BEAN CLASS
 *
 * VERSION  DATE          BY              REASON
 * -------- ------------- --------------- ------------------------------------------
 * 1.00     2013.04.12    wuxiaogang           程序・发布
 * 2.00     2013.05.31    wuxiaogang       程序・更新   新增景点爬取字段a_* 4个
 * 3.00     2013.06.21    wuxiaogang       程序・更新   新增景点爬取字段a_* 22个
 * -------- ------------- --------------- ------------------------------------------
 * Copyright 2013 robots  System. - All Rights Reserved.
 *
 */
package com.wu1g.bean.robot;

import java.util.ArrayList;
import java.util.List;

import com.wu1g.bean.BaseBean;

/**
 * 搜索结果集信息 BEAN CLASS
 * 
 * @author wuxiaogang
 */
public class BaseInfoBean extends BaseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5005753904648618417L;

	/** ID */
	private String id;
	/** versions */
	private String versions;
	/** 类型(0:RSS,1,采集) */
	private String type;
	/** 关键字 */
	private String keyworld;
	/** 信息来源url */
	private String remote_url;
	/** 本地存储路径 */
	private String local_url;
	/** 发布日期 */
	private String public_date;
	/** 站点ID */
	private String site_id;
	/** 状态(0存储1追踪) */
	private String status;
	/** 是否删除 0:未删除 1:已删除 */
	private String del_flag;
	/** 备注 */
	private String note;
	/** 数据输入日期 */
	private String date_created;
	/** 建立者id */
	private String create_id;
	/** 建立者ip */
	private String create_ip;
	/** 资料更新日期 */
	private String last_updated;
	/** 修改者id */
	private String update_id;
	/** 修改者ip */
	private String update_ip;
	/** 信息集合 */
	private List<BaseInfoBean> items = new ArrayList<BaseInfoBean>();
	/** 开始时间 */
	private String date1;
	/** 结束时间 */
	private String date2;
	/** 图片数量 */
	private String img_count;
	/** */
	private List<BaseFileBean> fileBeans;
	/** 站点名称 */
	private String site_title;
	/** 站点地址 */
	private String site_url;
	/** 更新标记 */
	private boolean update_flag = false;
	/** 标题 */
	private String title;
	/** 编辑人 */
	private String author;
	/** 简介 */
	private String brief_info;
	/** 详情 */
	private String detail_info;
	/** 详情(去除html) */
	private String detail_info_no_html;
	/** 信息分类*/
	private String info_type;
	/** 经度*/
	private String a_longitude;
	/** 纬度*/
	private String a_latitude;
	/** 景区地址 */
	private String a_address;
	/** 景区价格 */
	private String a_price;
	/** 推荐出游时间 */
	private String a_advice_travel_time;
	/** 游客满意度 */
	private String a_satisfaction;
	/** 已售数量 */
	private String a_sales_num;
	/** 评论数量 */
	private String a_comments_num;
	/** 景区图集 */
	private String a_scenic_pics;
	/** 景区亮点 */
	private String a_scenic_spot;
	/** 预订须知 */
	private String a_booking_info;
	/** 问题咨询 */
	private String a_consult_info;
	/** 景区评级 */
	private String a_level;
	/** 所在城市/地区 */
	private String a_city;
	/** 景区主题 */
	private String a_theme;
	/** 门票面值 */
	private String a_ticket_price;
	/** 开放时间 */
	private String a_business_hours;
	/** 景区电话 */
	private String a_tel;
	/** 导览图 */
	private String a_navigation_pics;
	/** 地图 */
	private String a_map;
	/** 推荐信息 */
	private String a_recommend_info;
	/** 住宿信息 */
	private String a_lodging_info;
	/** 交通信息 */
	private String a_traffic_info;
	/** 购物信息 */
	private String a_shopping_info;
	/** 攻略 */
	private String a_strategy_info;
	/** 景区点评 */
	private String a_review_info;
	/**
	 * ID取得
	 * @return ID
	 */
	public String getId() {
	    return id;
	}
	/**
	 * ID设定
	 * @param id ID
	 */
	public void setId(String id) {
	    this.id = id;
	}
	/**
	 * versions取得
	 * @return versions
	 */
	public String getVersions() {
	    return versions;
	}
	/**
	 * versions设定
	 * @param versions versions
	 */
	public void setVersions(String versions) {
	    this.versions = versions;
	}
	/**
	 * 类型(0:RSS,1,采集)取得
	 * @return 类型(0:RSS,1,采集)
	 */
	public String getType() {
	    return type;
	}
	/**
	 * 类型(0:RSS,1,采集)设定
	 * @param type 类型(0:RSS,1,采集)
	 */
	public void setType(String type) {
	    this.type = type;
	}
	/**
	 * 关键字取得
	 * @return 关键字
	 */
	public String getKeyworld() {
	    return keyworld;
	}
	/**
	 * 关键字设定
	 * @param keyworld 关键字
	 */
	public void setKeyworld(String keyworld) {
	    this.keyworld = keyworld;
	}
	/**
	 * 信息来源url取得
	 * @return 信息来源url
	 */
	public String getRemote_url() {
	    return remote_url;
	}
	/**
	 * 信息来源url设定
	 * @param remote_url 信息来源url
	 */
	public void setRemote_url(String remote_url) {
	    this.remote_url = remote_url;
	}
	/**
	 * 本地存储路径取得
	 * @return 本地存储路径
	 */
	public String getLocal_url() {
	    return local_url;
	}
	/**
	 * 本地存储路径设定
	 * @param local_url 本地存储路径
	 */
	public void setLocal_url(String local_url) {
	    this.local_url = local_url;
	}
	/**
	 * 发布日期取得
	 * @return 发布日期
	 */
	public String getPublic_date() {
	    return public_date;
	}
	/**
	 * 发布日期设定
	 * @param public_date 发布日期
	 */
	public void setPublic_date(String public_date) {
	    this.public_date = public_date;
	}
	/**
	 * 站点ID取得
	 * @return 站点ID
	 */
	public String getSite_id() {
	    return site_id;
	}
	/**
	 * 站点ID设定
	 * @param site_id 站点ID
	 */
	public void setSite_id(String site_id) {
	    this.site_id = site_id;
	}
	/**
	 * 状态(0存储1追踪)取得
	 * @return 状态(0存储1追踪)
	 */
	public String getStatus() {
	    return status;
	}
	/**
	 * 状态(0存储1追踪)设定
	 * @param status 状态(0存储1追踪)
	 */
	public void setStatus(String status) {
	    this.status = status;
	}
	/**
	 * 是否删除 0:未删除 1:已删除取得
	 * @return 是否删除 0:未删除 1:已删除
	 */
	public String getDel_flag() {
	    return del_flag;
	}
	/**
	 * 是否删除 0:未删除 1:已删除设定
	 * @param del_flag 是否删除 0:未删除 1:已删除
	 */
	public void setDel_flag(String del_flag) {
	    this.del_flag = del_flag;
	}
	/**
	 * 备注取得
	 * @return 备注
	 */
	public String getNote() {
	    return note;
	}
	/**
	 * 备注设定
	 * @param note 备注
	 */
	public void setNote(String note) {
	    this.note = note;
	}
	/**
	 * 数据输入日期取得
	 * @return 数据输入日期
	 */
	public String getDate_created() {
	    return date_created;
	}
	/**
	 * 数据输入日期设定
	 * @param date_created 数据输入日期
	 */
	public void setDate_created(String date_created) {
	    this.date_created = date_created;
	}
	/**
	 * 建立者id取得
	 * @return 建立者id
	 */
	public String getCreate_id() {
	    return create_id;
	}
	/**
	 * 建立者id设定
	 * @param create_id 建立者id
	 */
	public void setCreate_id(String create_id) {
	    this.create_id = create_id;
	}
	/**
	 * 建立者ip取得
	 * @return 建立者ip
	 */
	public String getCreate_ip() {
	    return create_ip;
	}
	/**
	 * 建立者ip设定
	 * @param create_ip 建立者ip
	 */
	public void setCreate_ip(String create_ip) {
	    this.create_ip = create_ip;
	}
	/**
	 * 资料更新日期取得
	 * @return 资料更新日期
	 */
	public String getLast_updated() {
	    return last_updated;
	}
	/**
	 * 资料更新日期设定
	 * @param last_updated 资料更新日期
	 */
	public void setLast_updated(String last_updated) {
	    this.last_updated = last_updated;
	}
	/**
	 * 修改者id取得
	 * @return 修改者id
	 */
	public String getUpdate_id() {
	    return update_id;
	}
	/**
	 * 修改者id设定
	 * @param update_id 修改者id
	 */
	public void setUpdate_id(String update_id) {
	    this.update_id = update_id;
	}
	/**
	 * 修改者ip取得
	 * @return 修改者ip
	 */
	public String getUpdate_ip() {
	    return update_ip;
	}
	/**
	 * 修改者ip设定
	 * @param update_ip 修改者ip
	 */
	public void setUpdate_ip(String update_ip) {
	    this.update_ip = update_ip;
	}
	/**
	 * 信息集合取得
	 * @return 信息集合
	 */
	public List<BaseInfoBean> getItems() {
	    return items;
	}
	/**
	 * 信息集合设定
	 * @param items 信息集合
	 */
	public void setItems(List<BaseInfoBean> items) {
	    this.items = items;
	}
	/**
	 * 开始时间取得
	 * @return 开始时间
	 */
	public String getDate1() {
	    return date1;
	}
	/**
	 * 开始时间设定
	 * @param date1 开始时间
	 */
	public void setDate1(String date1) {
	    this.date1 = date1;
	}
	/**
	 * 结束时间取得
	 * @return 结束时间
	 */
	public String getDate2() {
	    return date2;
	}
	/**
	 * 结束时间设定
	 * @param date2 结束时间
	 */
	public void setDate2(String date2) {
	    this.date2 = date2;
	}
	/**
	 * 图片数量取得
	 * @return 图片数量
	 */
	public String getImg_count() {
	    return img_count;
	}
	/**
	 * 图片数量设定
	 * @param img_count 图片数量
	 */
	public void setImg_count(String img_count) {
	    this.img_count = img_count;
	}
	/**
	 * fileBeans取得
	 * @return fileBeans
	 */
	public List<BaseFileBean> getFileBeans() {
	    return fileBeans;
	}
	/**
	 * fileBeans设定
	 * @param fileBeans fileBeans
	 */
	public void setFileBeans(List<BaseFileBean> fileBeans) {
	    this.fileBeans = fileBeans;
	}
	/**
	 * 站点名称取得
	 * @return 站点名称
	 */
	public String getSite_title() {
	    return site_title;
	}
	/**
	 * 站点名称设定
	 * @param site_title 站点名称
	 */
	public void setSite_title(String site_title) {
	    this.site_title = site_title;
	}
	/**
	 * 站点地址取得
	 * @return 站点地址
	 */
	public String getSite_url() {
	    return site_url;
	}
	/**
	 * 站点地址设定
	 * @param site_url 站点地址
	 */
	public void setSite_url(String site_url) {
	    this.site_url = site_url;
	}
	/**
	 * 更新标记取得
	 * @return 更新标记
	 */
	public boolean isUpdate_flag() {
	    return update_flag;
	}
	/**
	 * 更新标记设定
	 * @param update_flag 更新标记
	 */
	public void setUpdate_flag(boolean update_flag) {
	    this.update_flag = update_flag;
	}
	/**
	 * 标题取得
	 * @return 标题
	 */
	public String getTitle() {
	    return title;
	}
	/**
	 * 标题设定
	 * @param title 标题
	 */
	public void setTitle(String title) {
	    this.title = title;
	}
	/**
	 * 编辑人取得
	 * @return 编辑人
	 */
	public String getAuthor() {
	    return author;
	}
	/**
	 * 编辑人设定
	 * @param author 编辑人
	 */
	public void setAuthor(String author) {
	    this.author = author;
	}
	/**
	 * 简介取得
	 * @return 简介
	 */
	public String getBrief_info() {
	    return brief_info;
	}
	/**
	 * 简介设定
	 * @param brief_info 简介
	 */
	public void setBrief_info(String brief_info) {
	    this.brief_info = brief_info;
	}
	/**
	 * 详情取得
	 * @return 详情
	 */
	public String getDetail_info() {
	    return detail_info;
	}
	/**
	 * 详情设定
	 * @param detail_info 详情
	 */
	public void setDetail_info(String detail_info) {
	    this.detail_info = detail_info;
	}
	/**
	 * 详情(去除html)取得
	 * @return 详情(去除html)
	 */
	public String getDetail_info_no_html() {
	    return detail_info_no_html;
	}
	/**
	 * 详情(去除html)设定
	 * @param detail_info_no_html 详情(去除html)
	 */
	public void setDetail_info_no_html(String detail_info_no_html) {
	    this.detail_info_no_html = detail_info_no_html;
	}
	/**
	 * 信息分类取得
	 * @return 信息分类
	 */
	public String getInfo_type() {
	    return info_type;
	}
	/**
	 * 信息分类设定
	 * @param info_type 信息分类
	 */
	public void setInfo_type(String info_type) {
	    this.info_type = info_type;
	}
	/**
	 * 经度取得
	 * @return 经度
	 */
	public String getA_longitude() {
	    return a_longitude;
	}
	/**
	 * 经度设定
	 * @param a_longitude 经度
	 */
	public void setA_longitude(String a_longitude) {
	    this.a_longitude = a_longitude;
	}
	/**
	 * 纬度取得
	 * @return 纬度
	 */
	public String getA_latitude() {
	    return a_latitude;
	}
	/**
	 * 纬度设定
	 * @param a_latitude 纬度
	 */
	public void setA_latitude(String a_latitude) {
	    this.a_latitude = a_latitude;
	}
	/**
	 * 景区地址取得
	 * @return 景区地址
	 */
	public String getA_address() {
	    return a_address;
	}
	/**
	 * 景区地址设定
	 * @param a_address 景区地址
	 */
	public void setA_address(String a_address) {
	    this.a_address = a_address;
	}
	/**
	 * 景区价格取得
	 * @return 景区价格
	 */
	public String getA_price() {
	    return a_price;
	}
	/**
	 * 景区价格设定
	 * @param a_price 景区价格
	 */
	public void setA_price(String a_price) {
	    this.a_price = a_price;
	}
	/**
	 * 推荐出游时间取得
	 * @return 推荐出游时间
	 */
	public String getA_advice_travel_time() {
	    return a_advice_travel_time;
	}
	/**
	 * 推荐出游时间设定
	 * @param a_advice_travel_time 推荐出游时间
	 */
	public void setA_advice_travel_time(String a_advice_travel_time) {
	    this.a_advice_travel_time = a_advice_travel_time;
	}
	/**
	 * 游客满意度取得
	 * @return 游客满意度
	 */
	public String getA_satisfaction() {
	    return a_satisfaction;
	}
	/**
	 * 游客满意度设定
	 * @param a_satisfaction 游客满意度
	 */
	public void setA_satisfaction(String a_satisfaction) {
	    this.a_satisfaction = a_satisfaction;
	}
	/**
	 * 已售数量取得
	 * @return 已售数量
	 */
	public String getA_sales_num() {
	    return a_sales_num;
	}
	/**
	 * 已售数量设定
	 * @param a_sales_num 已售数量
	 */
	public void setA_sales_num(String a_sales_num) {
	    this.a_sales_num = a_sales_num;
	}
	/**
	 * 评论数量取得
	 * @return 评论数量
	 */
	public String getA_comments_num() {
	    return a_comments_num;
	}
	/**
	 * 评论数量设定
	 * @param a_comments_num 评论数量
	 */
	public void setA_comments_num(String a_comments_num) {
	    this.a_comments_num = a_comments_num;
	}
	/**
	 * 景区图集取得
	 * @return 景区图集
	 */
	public String getA_scenic_pics() {
	    return a_scenic_pics;
	}
	/**
	 * 景区图集设定
	 * @param a_scenic_pics 景区图集
	 */
	public void setA_scenic_pics(String a_scenic_pics) {
	    this.a_scenic_pics = a_scenic_pics;
	}
	/**
	 * 景区亮点取得
	 * @return 景区亮点
	 */
	public String getA_scenic_spot() {
	    return a_scenic_spot;
	}
	/**
	 * 景区亮点设定
	 * @param a_scenic_spot 景区亮点
	 */
	public void setA_scenic_spot(String a_scenic_spot) {
	    this.a_scenic_spot = a_scenic_spot;
	}
	/**
	 * 预订须知取得
	 * @return 预订须知
	 */
	public String getA_booking_info() {
	    return a_booking_info;
	}
	/**
	 * 预订须知设定
	 * @param a_booking_info 预订须知
	 */
	public void setA_booking_info(String a_booking_info) {
	    this.a_booking_info = a_booking_info;
	}
	/**
	 * 问题咨询取得
	 * @return 问题咨询
	 */
	public String getA_consult_info() {
	    return a_consult_info;
	}
	/**
	 * 问题咨询设定
	 * @param a_consult_info 问题咨询
	 */
	public void setA_consult_info(String a_consult_info) {
	    this.a_consult_info = a_consult_info;
	}
	/**
	 * 景区评级取得
	 * @return 景区评级
	 */
	public String getA_level() {
	    return a_level;
	}
	/**
	 * 景区评级设定
	 * @param a_level 景区评级
	 */
	public void setA_level(String a_level) {
	    this.a_level = a_level;
	}
	/**
	 * 所在城市/地区取得
	 * @return 所在城市/地区
	 */
	public String getA_city() {
	    return a_city;
	}
	/**
	 * 所在城市/地区设定
	 * @param a_city 所在城市/地区
	 */
	public void setA_city(String a_city) {
	    this.a_city = a_city;
	}
	/**
	 * 景区主题取得
	 * @return 景区主题
	 */
	public String getA_theme() {
	    return a_theme;
	}
	/**
	 * 景区主题设定
	 * @param a_theme 景区主题
	 */
	public void setA_theme(String a_theme) {
	    this.a_theme = a_theme;
	}
	/**
	 * 门票面值取得
	 * @return 门票面值
	 */
	public String getA_ticket_price() {
	    return a_ticket_price;
	}
	/**
	 * 门票面值设定
	 * @param a_ticket_price 门票面值
	 */
	public void setA_ticket_price(String a_ticket_price) {
	    this.a_ticket_price = a_ticket_price;
	}
	/**
	 * 开放时间取得
	 * @return 开放时间
	 */
	public String getA_business_hours() {
	    return a_business_hours;
	}
	/**
	 * 开放时间设定
	 * @param a_business_hours 开放时间
	 */
	public void setA_business_hours(String a_business_hours) {
	    this.a_business_hours = a_business_hours;
	}
	/**
	 * 景区电话取得
	 * @return 景区电话
	 */
	public String getA_tel() {
	    return a_tel;
	}
	/**
	 * 景区电话设定
	 * @param a_tel 景区电话
	 */
	public void setA_tel(String a_tel) {
	    this.a_tel = a_tel;
	}
	/**
	 * 导览图取得
	 * @return 导览图
	 */
	public String getA_navigation_pics() {
	    return a_navigation_pics;
	}
	/**
	 * 导览图设定
	 * @param a_navigation_pics 导览图
	 */
	public void setA_navigation_pics(String a_navigation_pics) {
	    this.a_navigation_pics = a_navigation_pics;
	}
	/**
	 * 地图取得
	 * @return 地图
	 */
	public String getA_map() {
	    return a_map;
	}
	/**
	 * 地图设定
	 * @param a_map 地图
	 */
	public void setA_map(String a_map) {
	    this.a_map = a_map;
	}
	/**
	 * 推荐信息取得
	 * @return 推荐信息
	 */
	public String getA_recommend_info() {
	    return a_recommend_info;
	}
	/**
	 * 推荐信息设定
	 * @param a_recommend_info 推荐信息
	 */
	public void setA_recommend_info(String a_recommend_info) {
	    this.a_recommend_info = a_recommend_info;
	}
	/**
	 * 住宿信息取得
	 * @return 住宿信息
	 */
	public String getA_lodging_info() {
	    return a_lodging_info;
	}
	/**
	 * 住宿信息设定
	 * @param a_lodging_info 住宿信息
	 */
	public void setA_lodging_info(String a_lodging_info) {
	    this.a_lodging_info = a_lodging_info;
	}
	/**
	 * 交通信息取得
	 * @return 交通信息
	 */
	public String getA_traffic_info() {
	    return a_traffic_info;
	}
	/**
	 * 交通信息设定
	 * @param a_traffic_info 交通信息
	 */
	public void setA_traffic_info(String a_traffic_info) {
	    this.a_traffic_info = a_traffic_info;
	}
	/**
	 * 购物信息取得
	 * @return 购物信息
	 */
	public String getA_shopping_info() {
	    return a_shopping_info;
	}
	/**
	 * 购物信息设定
	 * @param a_shopping_info 购物信息
	 */
	public void setA_shopping_info(String a_shopping_info) {
	    this.a_shopping_info = a_shopping_info;
	}
	/**
	 * 攻略取得
	 * @return 攻略
	 */
	public String getA_strategy_info() {
	    return a_strategy_info;
	}
	/**
	 * 攻略设定
	 * @param a_strategy_info 攻略
	 */
	public void setA_strategy_info(String a_strategy_info) {
	    this.a_strategy_info = a_strategy_info;
	}
	/**
	 * 景区点评取得
	 * @return 景区点评
	 */
	public String getA_review_info() {
	    return a_review_info;
	}
	/**
	 * 景区点评设定
	 * @param a_review_info 景区点评
	 */
	public void setA_review_info(String a_review_info) {
	    this.a_review_info = a_review_info;
	}
}
