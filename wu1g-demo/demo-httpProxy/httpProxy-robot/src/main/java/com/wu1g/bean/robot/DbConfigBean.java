/*
 * 存放配置信息 BEAN CLASS
 *
 * VERSION  DATE          BY              REASON
 * -------- ------------- --------------- ------------------------------------------
 * 1.00     2013.06.18    wuxiaogang           程序・发布
 * 2.00     2013.07.04    wuxiaogang           程序・更新 columns_a_*
 * -------- ------------- --------------- ------------------------------------------
 * Copyright 2013 robots System. - All Rights Reserved.
 *
 */
package com.wu1g.bean.robot;

import java.util.Map;

import com.wu1g.bean.BaseBean;

/**
 * 存放配置信息  BEAN CLASS
 * 
 * @author {wuxiaogang}
 * 
 */
public class DbConfigBean extends BaseBean {
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1890658163437696749L;

	/** 默认构造器 */
	public DbConfigBean() {
	}

	/** 数据库字段*/
	private String[] columns;
	/** 标签 */
	private Map<String, String> columns_tag;
	/** 属性 */
	private Map<String, String> columns_pro;
	/** 属性值 */
	private Map<String, String> columns_val;
	/** 第几个 */
	private Map<String, String> columns_num;
	/** 标签(内层) */
	private Map<String, String> columns_a_tag;
	/** 属性(内层) */
	private Map<String, String> columns_a_pro;
	/** 属性值(内层) */
	private Map<String, String> columns_a_val;
	/** 第几个(内层) */
	private Map<String, String> columns_a_num;
	/** 图片真实路径(属性) */
	private Map<String, String> columns_real_src;
	/** 是否抓取标签本身0否1是 */
	private Map<String, String> columns_is_self;
	/** 清除html(纯文本标记) */
	private Map<String, String> columns_is_html;
	/** 字符截取标记0否1是 */
	private Map<String, String> columns_is_sub;
	/** (字符截取)开始字符串 */
	private Map<String, String> columns_s_str;
	/** (字符截取)结束字符串 */
	private Map<String, String> columns_e_str;
	/** 字符替换标记0否1是 */
	private Map<String, String> columns_is_replace;
	/** 替换字符 旧 */
	private Map<String, String> columns_str_old;
	/** 替换字符 新 */
	private Map<String, String> columns_str_new;

	/**
	 * 数据库字段取得
	 * @return 数据库字段
	 */
	public String[] getColumns() {
	    return columns;
	}

	/**
	 * 数据库字段设定
	 * @param columns 数据库字段
	 */
	public void setColumns(String[] columns) {
	    this.columns = columns;
	}

	/**
	 * 标签取得
	 * @return 标签
	 */
	public Map<String,String> getColumns_tag() {
	    return columns_tag;
	}

	/**
	 * 标签设定
	 * @param columns_tag 标签
	 */
	public void setColumns_tag(Map<String,String> columns_tag) {
	    this.columns_tag = columns_tag;
	}

	/**
	 * 属性取得
	 * @return 属性
	 */
	public Map<String,String> getColumns_pro() {
	    return columns_pro;
	}

	/**
	 * 属性设定
	 * @param columns_pro 属性
	 */
	public void setColumns_pro(Map<String,String> columns_pro) {
	    this.columns_pro = columns_pro;
	}

	/**
	 * 属性值取得
	 * @return 属性值
	 */
	public Map<String,String> getColumns_val() {
	    return columns_val;
	}

	/**
	 * 属性值设定
	 * @param columns_val 属性值
	 */
	public void setColumns_val(Map<String,String> columns_val) {
	    this.columns_val = columns_val;
	}

	/**
	 * 第几个取得
	 * @return 第几个
	 */
	public Map<String,String> getColumns_num() {
	    return columns_num;
	}

	/**
	 * 第几个设定
	 * @param columns_num 第几个
	 */
	public void setColumns_num(Map<String,String> columns_num) {
	    this.columns_num = columns_num;
	}

	/**
	 * 标签(内层)取得
	 * @return 标签(内层)
	 */
	public Map<String,String> getColumns_a_tag() {
	    return columns_a_tag;
	}

	/**
	 * 标签(内层)设定
	 * @param columns_a_tag 标签(内层)
	 */
	public void setColumns_a_tag(Map<String,String> columns_a_tag) {
	    this.columns_a_tag = columns_a_tag;
	}

	/**
	 * 属性(内层)取得
	 * @return 属性(内层)
	 */
	public Map<String,String> getColumns_a_pro() {
	    return columns_a_pro;
	}

	/**
	 * 属性(内层)设定
	 * @param columns_a_pro 属性(内层)
	 */
	public void setColumns_a_pro(Map<String,String> columns_a_pro) {
	    this.columns_a_pro = columns_a_pro;
	}

	/**
	 * 属性值(内层)取得
	 * @return 属性值(内层)
	 */
	public Map<String,String> getColumns_a_val() {
	    return columns_a_val;
	}

	/**
	 * 属性值(内层)设定
	 * @param columns_a_val 属性值(内层)
	 */
	public void setColumns_a_val(Map<String,String> columns_a_val) {
	    this.columns_a_val = columns_a_val;
	}

	/**
	 * 第几个(内层)取得
	 * @return 第几个(内层)
	 */
	public Map<String,String> getColumns_a_num() {
	    return columns_a_num;
	}

	/**
	 * 第几个(内层)设定
	 * @param columns_a_num 第几个(内层)
	 */
	public void setColumns_a_num(Map<String,String> columns_a_num) {
	    this.columns_a_num = columns_a_num;
	}

	/**
	 * 图片真实路径(属性)取得
	 * @return 图片真实路径(属性)
	 */
	public Map<String,String> getColumns_real_src() {
	    return columns_real_src;
	}

	/**
	 * 图片真实路径(属性)设定
	 * @param columns_real_src 图片真实路径(属性)
	 */
	public void setColumns_real_src(Map<String,String> columns_real_src) {
	    this.columns_real_src = columns_real_src;
	}

	/**
	 * 是否抓取标签本身0否1是取得
	 * @return 是否抓取标签本身0否1是
	 */
	public Map<String,String> getColumns_is_self() {
	    return columns_is_self;
	}

	/**
	 * 是否抓取标签本身0否1是设定
	 * @param columns_is_self 是否抓取标签本身0否1是
	 */
	public void setColumns_is_self(Map<String,String> columns_is_self) {
	    this.columns_is_self = columns_is_self;
	}

	/**
	 * 清除html(纯文本标记)取得
	 * @return 清除html(纯文本标记)
	 */
	public Map<String,String> getColumns_is_html() {
	    return columns_is_html;
	}

	/**
	 * 清除html(纯文本标记)设定
	 * @param columns_is_html 清除html(纯文本标记)
	 */
	public void setColumns_is_html(Map<String,String> columns_is_html) {
	    this.columns_is_html = columns_is_html;
	}

	/**
	 * 字符截取标记0否1是取得
	 * @return 字符截取标记0否1是
	 */
	public Map<String,String> getColumns_is_sub() {
	    return columns_is_sub;
	}

	/**
	 * 字符截取标记0否1是设定
	 * @param columns_is_sub 字符截取标记0否1是
	 */
	public void setColumns_is_sub(Map<String,String> columns_is_sub) {
	    this.columns_is_sub = columns_is_sub;
	}

	/**
	 * (字符截取)开始字符串取得
	 * @return (字符截取)开始字符串
	 */
	public Map<String,String> getColumns_s_str() {
	    return columns_s_str;
	}

	/**
	 * (字符截取)开始字符串设定
	 * @param columns_s_str (字符截取)开始字符串
	 */
	public void setColumns_s_str(Map<String,String> columns_s_str) {
	    this.columns_s_str = columns_s_str;
	}

	/**
	 * (字符截取)结束字符串取得
	 * @return (字符截取)结束字符串
	 */
	public Map<String,String> getColumns_e_str() {
	    return columns_e_str;
	}

	/**
	 * (字符截取)结束字符串设定
	 * @param columns_e_str (字符截取)结束字符串
	 */
	public void setColumns_e_str(Map<String,String> columns_e_str) {
	    this.columns_e_str = columns_e_str;
	}

	/**
	 * 字符替换标记0否1是取得
	 * @return 字符替换标记0否1是
	 */
	public Map<String,String> getColumns_is_replace() {
	    return columns_is_replace;
	}

	/**
	 * 字符替换标记0否1是设定
	 * @param columns_is_replace 字符替换标记0否1是
	 */
	public void setColumns_is_replace(Map<String,String> columns_is_replace) {
	    this.columns_is_replace = columns_is_replace;
	}

	/**
	 * 替换字符 旧取得
	 * @return 替换字符 旧
	 */
	public Map<String,String> getColumns_str_old() {
	    return columns_str_old;
	}

	/**
	 * 替换字符 旧设定
	 * @param columns_str_old 替换字符 旧
	 */
	public void setColumns_str_old(Map<String,String> columns_str_old) {
	    this.columns_str_old = columns_str_old;
	}

	/**
	 * 替换字符 新取得
	 * @return 替换字符 新
	 */
	public Map<String,String> getColumns_str_new() {
	    return columns_str_new;
	}

	/**
	 * 替换字符 新设定
	 * @param columns_str_new 替换字符 新
	 */
	public void setColumns_str_new(Map<String,String> columns_str_new) {
	    this.columns_str_new = columns_str_new;
	}
	
}
