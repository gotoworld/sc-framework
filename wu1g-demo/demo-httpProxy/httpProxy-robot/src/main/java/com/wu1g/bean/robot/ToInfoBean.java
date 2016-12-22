/*
 * 配置文件信息(json转换bean使用) BEAN CLASS
 *
 * VERSION  DATE          BY              REASON
 * -------- ------------- --------------- ------------------------------------------
 * 1.00     2013.06.18    wuxiaogang           程序・发布
 * 2.00     2013.06.18    wuxiaogang           程序・更新    a_*
 * -------- ------------- --------------- ------------------------------------------
 * Copyright 2013 robots System. - All Rights Reserved.
 *
 */
package com.wu1g.bean.robot;

import com.wu1g.bean.BaseBean;

/**
 * 配置文件信息(json转换bean使用) BEAN CLASS
 * 
 * @author {wuxiaogang}
 * 
 */
public class ToInfoBean extends BaseBean {
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1890658163437696749L;
	/** 默认构造器 */
	public ToInfoBean() {
	}
	/** 标签 */
	private String tag;
	/** 属性 */
	private String pro;
	/** 属性值 */
	private String val;
	/** 第几个 */
	private String num;
	/** 标签(内层) */
	private String a_tag;
	/** 属性(内层) */
	private String a_pro;
	/** 属性值(内层) */
	private String a_val;
	/** 第几个(内层) */
	private String a_num;
	/** 真实图片路径(属性)*/
	private String real_src;
	/** 是否抓取标签本身0否1是 */
	private String is_self;
	/** 清除html(纯文本标记) */
	private String is_html;
	/** 字符截取标记0否1是 */
	private String is_sub;
	/** (字符截取)开始字符串 */
	private String s_str;
	/** (字符截取)结束字符串 */
	private String e_str;
	/** 字符替换标记0否1是 */
	private String is_replace;
	/** 替换字符 旧 */
	private String str_old;
	/** 替换字符 新 */
	private String str_new;
	/**
	 * 标签取得
	 * @return 标签
	 */
	public String getTag() {
	    return tag;
	}
	/**
	 * 标签设定
	 * @param tag 标签
	 */
	public void setTag(String tag) {
	    this.tag = tag;
	}
	/**
	 * 属性取得
	 * @return 属性
	 */
	public String getPro() {
	    return pro;
	}
	/**
	 * 属性设定
	 * @param pro 属性
	 */
	public void setPro(String pro) {
	    this.pro = pro;
	}
	/**
	 * 属性值取得
	 * @return 属性值
	 */
	public String getVal() {
	    return val;
	}
	/**
	 * 属性值设定
	 * @param val 属性值
	 */
	public void setVal(String val) {
	    this.val = val;
	}
	/**
	 * 第几个取得
	 * @return 第几个
	 */
	public String getNum() {
	    return num;
	}
	/**
	 * 第几个设定
	 * @param num 第几个
	 */
	public void setNum(String num) {
	    this.num = num;
	}
	/**
	 * 标签(内层)取得
	 * @return 标签(内层)
	 */
	public String getA_tag() {
	    return a_tag;
	}
	/**
	 * 标签(内层)设定
	 * @param a_tag 标签(内层)
	 */
	public void setA_tag(String a_tag) {
	    this.a_tag = a_tag;
	}
	/**
	 * 属性(内层)取得
	 * @return 属性(内层)
	 */
	public String getA_pro() {
	    return a_pro;
	}
	/**
	 * 属性(内层)设定
	 * @param a_pro 属性(内层)
	 */
	public void setA_pro(String a_pro) {
	    this.a_pro = a_pro;
	}
	/**
	 * 属性值(内层)取得
	 * @return 属性值(内层)
	 */
	public String getA_val() {
	    return a_val;
	}
	/**
	 * 属性值(内层)设定
	 * @param a_val 属性值(内层)
	 */
	public void setA_val(String a_val) {
	    this.a_val = a_val;
	}
	/**
	 * 第几个(内层)取得
	 * @return 第几个(内层)
	 */
	public String getA_num() {
	    return a_num;
	}
	/**
	 * 第几个(内层)设定
	 * @param a_num 第几个(内层)
	 */
	public void setA_num(String a_num) {
	    this.a_num = a_num;
	}
	/**
	 * 真实图片路径(属性)取得
	 * @return 真实图片路径(属性)
	 */
	public String getReal_src() {
	    return real_src;
	}
	/**
	 * 真实图片路径(属性)设定
	 * @param real_src 真实图片路径(属性)
	 */
	public void setReal_src(String real_src) {
	    this.real_src = real_src;
	}
	/**
	 * 是否抓取标签本身0否1是取得
	 * @return 是否抓取标签本身0否1是
	 */
	public String getIs_self() {
	    return is_self;
	}
	/**
	 * 是否抓取标签本身0否1是设定
	 * @param is_self 是否抓取标签本身0否1是
	 */
	public void setIs_self(String is_self) {
	    this.is_self = is_self;
	}
	/**
	 * 清除html(纯文本标记)取得
	 * @return 清除html(纯文本标记)
	 */
	public String getIs_html() {
	    return is_html;
	}
	/**
	 * 清除html(纯文本标记)设定
	 * @param is_html 清除html(纯文本标记)
	 */
	public void setIs_html(String is_html) {
	    this.is_html = is_html;
	}
	/**
	 * 字符截取标记0否1是取得
	 * @return 字符截取标记0否1是
	 */
	public String getIs_sub() {
	    return is_sub;
	}
	/**
	 * 字符截取标记0否1是设定
	 * @param is_sub 字符截取标记0否1是
	 */
	public void setIs_sub(String is_sub) {
	    this.is_sub = is_sub;
	}
	/**
	 * (字符截取)开始字符串取得
	 * @return (字符截取)开始字符串
	 */
	public String getS_str() {
	    return s_str;
	}
	/**
	 * (字符截取)开始字符串设定
	 * @param s_str (字符截取)开始字符串
	 */
	public void setS_str(String s_str) {
	    this.s_str = s_str;
	}
	/**
	 * (字符截取)结束字符串取得
	 * @return (字符截取)结束字符串
	 */
	public String getE_str() {
	    return e_str;
	}
	/**
	 * (字符截取)结束字符串设定
	 * @param e_str (字符截取)结束字符串
	 */
	public void setE_str(String e_str) {
	    this.e_str = e_str;
	}
	/**
	 * 字符替换标记0否1是取得
	 * @return 字符替换标记0否1是
	 */
	public String getIs_replace() {
	    return is_replace;
	}
	/**
	 * 字符替换标记0否1是设定
	 * @param is_replace 字符替换标记0否1是
	 */
	public void setIs_replace(String is_replace) {
	    this.is_replace = is_replace;
	}
	/**
	 * 替换字符 旧取得
	 * @return 替换字符 旧
	 */
	public String getStr_old() {
	    return str_old;
	}
	/**
	 * 替换字符 旧设定
	 * @param str_old 替换字符 旧
	 */
	public void setStr_old(String str_old) {
	    this.str_old = str_old;
	}
	/**
	 * 替换字符 新取得
	 * @return 替换字符 新
	 */
	public String getStr_new() {
	    return str_new;
	}
	/**
	 * 替换字符 新设定
	 * @param str_new 替换字符 新
	 */
	public void setStr_new(String str_new) {
	    this.str_new = str_new;
	}
}
