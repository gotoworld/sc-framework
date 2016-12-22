/*
 * json to bean BEAN CLASS
 *
 * VERSION  DATE          BY              REASON
 * -------- ------------- --------------- ------------------------------------------
 * 1.00     2013.06.18    wuxiaogang           程序・发布
 * -------- ------------- --------------- ------------------------------------------
 * Copyright 2013 robots System. - All Rights Reserved.
 *
 */
package com.wu1g.bean.robot;

import com.wu1g.bean.BaseBean;

/**
 * json to bean BEAN CLASS
 * 
 * @author {wuxiaogang}
 * 
 */
public class ConfigJsonBean extends BaseBean {
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1890658163437696749L;

	/** 默认构造器 */
	public ConfigJsonBean() {
	}

	/** 标题 */
	private ToInfoBean title;
	/** 发布时间 */
	private ToInfoBean public_date;
	/** 编辑人 */
	private ToInfoBean author;
	/** 简介 */
	private ToInfoBean brief_info;
	/** 详情 */
	private ToInfoBean detail_info;

	/**
	 * 标题取得
	 * @return 标题
	 */
	public ToInfoBean getTitle() {
	    return title;
	}

	/**
	 * 标题设定
	 * @param title 标题
	 */
	public void setTitle(ToInfoBean title) {
	    this.title = title;
	}

	/**
	 * 发布时间取得
	 * @return 发布时间
	 */
	public ToInfoBean getPublic_date() {
	    return public_date;
	}

	/**
	 * 发布时间设定
	 * @param public_date 发布时间
	 */
	public void setPublic_date(ToInfoBean public_date) {
	    this.public_date = public_date;
	}

	/**
	 * 编辑人取得
	 * @return 编辑人
	 */
	public ToInfoBean getAuthor() {
	    return author;
	}

	/**
	 * 编辑人设定
	 * @param author 编辑人
	 */
	public void setAuthor(ToInfoBean author) {
	    this.author = author;
	}

	/**
	 * 简介取得
	 * @return 简介
	 */
	public ToInfoBean getBrief_info() {
	    return brief_info;
	}

	/**
	 * 简介设定
	 * @param brief_info 简介
	 */
	public void setBrief_info(ToInfoBean brief_info) {
	    this.brief_info = brief_info;
	}

	/**
	 * 详情取得
	 * @return 详情
	 */
	public ToInfoBean getDetail_info() {
	    return detail_info;
	}

	/**
	 * 详情设定
	 * @param detail_info 详情
	 */
	public void setDetail_info(ToInfoBean detail_info) {
	    this.detail_info = detail_info;
	}

}
