/*
 * 订阅站点信息 BEAN CLASS
 *
 * VERSION  DATE          BY              REASON
 * -------- ------------- --------------- ------------------------------------------
 * 1.00     2013.04.12    wuxiaogang      程序・发布
 * 2.00     2013.06.18    wuxiaogang      程序・更新   坑爹的新需求,增加信息的爬取[执行/停止]条件
 * 3.00     2013.06.18    wuxiaogang      程序・更新   为适应频繁增加的字段爬取筛选条件-[废弃当初的字段采集配置方法]-新增config配置信息字段json格式
 * -------- ------------- --------------- ------------------------------------------
 * Copyright 2013 robots  System. - All Rights Reserved.
 *
 */
package com.wu1g.bean.robot;

import com.wu1g.bean.BaseBean;

/**
 * 订阅站点信息 BEAN CLASS
 * 
 * @author wuxiaogang
 * 
 */
public class BaseSiteBean extends BaseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5005753904648618417L;
	/** ID */
	private String id;
	/** versions */
	private String versions;
	/** 站点名称 */
	private String title;
	/** 关键字 */
	private String keyworld;
	/** url */
	private String url;
	/** 类型(0:RSS,1,采集) */
	private String type;
	/** 检索深度 */
	private String web_depth;
	/** 状态(0停用1启用) */
	private String status;
	/** 是否删除 0:未删除 1:已删除 */
	private String del_flag;
	/** 备注 */
	private String note;
	/** 线程执行间隔 (毫秒) */
	private String thread_sleep;
	/** 线程执行标记 0 不执行 */
	private String thread_flag;
	/** 站点编码 */
	private String charset;
	/** 采集内容配置(json) */
	private String config;
	/** 采集开始时间 */
	private String s_time;
	/** 自动采集数量(-1 无限)  */
	private String auto_run_num;
	/** 站点信息分类  */
	private String info_type;
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
	/** json配置信息转换后的bean*/
	private ConfigJsonBean configJsonBean;
	/** 站点执行标记 */
	private String run_flag;
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
	 * 站点名称取得
	 * @return 站点名称
	 */
	public String getTitle() {
	    return title;
	}
	/**
	 * 站点名称设定
	 * @param title 站点名称
	 */
	public void setTitle(String title) {
	    this.title = title;
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
	 * url取得
	 * @return url
	 */
	public String getUrl() {
	    return url;
	}
	/**
	 * url设定
	 * @param url url
	 */
	public void setUrl(String url) {
	    this.url = url;
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
	 * 检索深度取得
	 * @return 检索深度
	 */
	public String getWeb_depth() {
	    return web_depth;
	}
	/**
	 * 检索深度设定
	 * @param web_depth 检索深度
	 */
	public void setWeb_depth(String web_depth) {
	    this.web_depth = web_depth;
	}
	/**
	 * 状态(0停用1启用)取得
	 * @return 状态(0停用1启用)
	 */
	public String getStatus() {
	    return status;
	}
	/**
	 * 状态(0停用1启用)设定
	 * @param status 状态(0停用1启用)
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
	 * 线程执行间隔 (毫秒)取得
	 * @return 线程执行间隔 (毫秒)
	 */
	public String getThread_sleep() {
	    return thread_sleep;
	}
	/**
	 * 线程执行间隔 (毫秒)设定
	 * @param thread_sleep 线程执行间隔 (毫秒)
	 */
	public void setThread_sleep(String thread_sleep) {
	    this.thread_sleep = thread_sleep;
	}
	/**
	 * 线程执行标记 0 不执行取得
	 * @return 线程执行标记 0 不执行
	 */
	public String getThread_flag() {
	    return thread_flag;
	}
	/**
	 * 线程执行标记 0 不执行设定
	 * @param thread_flag 线程执行标记 0 不执行
	 */
	public void setThread_flag(String thread_flag) {
	    this.thread_flag = thread_flag;
	}
	/**
	 * 站点编码取得
	 * @return 站点编码
	 */
	public String getCharset() {
	    return charset;
	}
	/**
	 * 站点编码设定
	 * @param charset 站点编码
	 */
	public void setCharset(String charset) {
	    this.charset = charset;
	}
	/**
	 * 采集内容配置(json)取得
	 * @return 采集内容配置(json)
	 */
	public String getConfig() {
	    return config;
	}
	/**
	 * 采集内容配置(json)设定
	 * @param config 采集内容配置(json)
	 */
	public void setConfig(String config) {
	    this.config = config;
	}
	/**
	 * 采集开始时间取得
	 * @return 采集开始时间
	 */
	public String getS_time() {
	    return s_time;
	}
	/**
	 * 采集开始时间设定
	 * @param s_time 采集开始时间
	 */
	public void setS_time(String s_time) {
	    this.s_time = s_time;
	}
	/**
	 * 自动采集数量(-1 无限)取得
	 * @return 自动采集数量(-1 无限)
	 */
	public String getAuto_run_num() {
	    return auto_run_num;
	}
	/**
	 * 自动采集数量(-1 无限)设定
	 * @param auto_run_num 自动采集数量(-1 无限)
	 */
	public void setAuto_run_num(String auto_run_num) {
	    this.auto_run_num = auto_run_num;
	}
	/**
	 * 站点信息分类取得
	 * @return 站点信息分类
	 */
	public String getInfo_type() {
	    return info_type;
	}
	/**
	 * 站点信息分类设定
	 * @param info_type 站点信息分类
	 */
	public void setInfo_type(String info_type) {
	    this.info_type = info_type;
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
	 * json配置信息转换后的bean取得
	 * @return json配置信息转换后的bean
	 */
	public ConfigJsonBean getConfigJsonBean() {
	    return configJsonBean;
	}
	/**
	 * json配置信息转换后的bean设定
	 * @param configJsonBean json配置信息转换后的bean
	 */
	public void setConfigJsonBean(ConfigJsonBean configJsonBean) {
	    this.configJsonBean = configJsonBean;
	}
	/**
	 * 站点执行标记取得
	 * @return 站点执行标记
	 */
	public String getRun_flag() {
	    return run_flag;
	}
	/**
	 * 站点执行标记设定
	 * @param run_flag 站点执行标记
	 */
	public void setRun_flag(String run_flag) {
	    this.run_flag = run_flag;
	}
	
}
