/*
 * 附件信息 BEAN CLASS
 *
 * VERSION  DATE          BY              REASON
 * -------- ------------- --------------- ------------------------------------------
 * 1.00     2012.01.06    wuxiaogang           程序・发布
 * -------- ------------- --------------- ------------------------------------------
 * Copyright 2013 robots  System. - All Rights Reserved.
 *
 */
package com.wu1g.bean.robot;

import com.wu1g.bean.BaseBean;

/**
 * 附件信息 BEAN CLASS
 * @author wuxiaogang 
 *
 */
public class BaseFileBean extends BaseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5005753904648618417L;
	/** ID */
	private String id;
	/** versions */
	private String versions;
	/** 站点ID */
	private String site_id;
	/** 信息id */
	private String info_id;
	/** 附件名称 */
	private String name;
	/** sort_num */
	private String sort_num;
	/** 附件大小 */
	private String file_size;
	/** 附件描述 */
	private String note;
	/** 附件类型 */
	private String type;
	/** 信息来源url */
	private String remote_url;
	/** 本地存储路径2(美化) */
	private String local_url2;
	/** 本地存储路径 */
	private String local_url;
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
	 * 信息id取得
	 * @return 信息id
	 */
	public String getInfo_id() {
	    return info_id;
	}
	/**
	 * 信息id设定
	 * @param info_id 信息id
	 */
	public void setInfo_id(String info_id) {
	    this.info_id = info_id;
	}
	/**
	 * 附件名称取得
	 * @return 附件名称
	 */
	public String getName() {
	    return name;
	}
	/**
	 * 附件名称设定
	 * @param name 附件名称
	 */
	public void setName(String name) {
	    this.name = name;
	}
	/**
	 * sort_num取得
	 * @return sort_num
	 */
	public String getSort_num() {
	    return sort_num;
	}
	/**
	 * sort_num设定
	 * @param sort_num sort_num
	 */
	public void setSort_num(String sort_num) {
	    this.sort_num = sort_num;
	}
	/**
	 * 附件大小取得
	 * @return 附件大小
	 */
	public String getFile_size() {
	    return file_size;
	}
	/**
	 * 附件大小设定
	 * @param file_size 附件大小
	 */
	public void setFile_size(String file_size) {
	    this.file_size = file_size;
	}
	/**
	 * 附件描述取得
	 * @return 附件描述
	 */
	public String getNote() {
	    return note;
	}
	/**
	 * 附件描述设定
	 * @param note 附件描述
	 */
	public void setNote(String note) {
	    this.note = note;
	}
	/**
	 * 附件类型取得
	 * @return 附件类型
	 */
	public String getType() {
	    return type;
	}
	/**
	 * 附件类型设定
	 * @param type 附件类型
	 */
	public void setType(String type) {
	    this.type = type;
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
	 * 本地存储路径2(美化)取得
	 * @return 本地存储路径2(美化)
	 */
	public String getLocal_url2() {
	    return local_url2;
	}
	/**
	 * 本地存储路径2(美化)设定
	 * @param local_url2 本地存储路径2(美化)
	 */
	public void setLocal_url2(String local_url2) {
	    this.local_url2 = local_url2;
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

}
