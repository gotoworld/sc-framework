/*
 * 基础BEAN类
 *
 * VERSION  DATE        BY              REASON
 * -------- ----------- --------------- ------------------------------------------
 * 1.00     2015.01.13  wuxiaogang      程序.发布
 * -------- ----------- --------------- ------------------------------------------
 * Copyright 2015 baseos  System. - All Rights Reserved.
 *
 */
package com.wu1g.bean;

import java.io.Serializable;

/**
 * <p>基础BEAN类</p>
 */
public class BaseBean implements Serializable,IBean,Cloneable {
	private static final long serialVersionUID = -7279166967099470479L;
	/**默认构造器*/
	public BaseBean(){}
	/** 开始时间 */
	private String date1;
	/** 结束时间 */
	private String date2;
	/** 错误代码*/
	private String errcode;
	/** 错误信息 */
	private String errmsg;
	/** 人工分页begin */
	private Integer limit1;
	/** 人工分页end */
	private Integer limit2;
	/** 分页偏移量 */
	private Integer offset;
	/**错误或者正确信息*/
	private String msg;
	/** 页码 */
	private Integer pageNum;
	/** 每页显示条数 */
	private Integer pageSize;
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
	 * 错误代码取得
	 * @return 错误代码
	 */
	public String getErrcode() {
	    return errcode;
	}
	/**
	 * 错误代码设定
	 * @param errcode 错误代码
	 */
	public void setErrcode(String errcode) {
	    this.errcode = errcode;
	}
	/**
	 * 错误信息取得
	 * @return 错误信息
	 */
	public String getErrmsg() {
	    return errmsg;
	}
	/**
	 * 错误信息设定
	 * @param errmsg 错误信息
	 */
	public void setErrmsg(String errmsg) {
	    this.errmsg = errmsg;
	}
	/**
	 * 人工分页begin取得
	 * @return 人工分页begin
	 */
	public Integer getLimit1() {
	    return limit1;
	}
	/**
	 * 人工分页begin设定
	 * @param limit1 人工分页begin
	 */
	public void setLimit1(Integer limit1) {
	    this.limit1 = limit1;
	}
	/**
	 * 人工分页end取得
	 * @return 人工分页end
	 */
	public Integer getLimit2() {
	    return limit2;
	}
	/**
	 * 人工分页end设定
	 * @param limit2 人工分页end
	 */
	public void setLimit2(Integer limit2) {
	    this.limit2 = limit2;
	}
	/**
	 * 分页偏移量取得
	 * @return 分页偏移量
	 */
	public Integer getOffset() {
	    return offset;
	}
	/**
	 * 分页偏移量设定
	 * @param offset 分页偏移量
	 */
	public void setOffset(Integer offset) {
	    this.offset = offset;
	}
	/**
	 * 错误或者正确信息取得
	 * @return 错误或者正确信息
	 */
	public String getMsg() {
	    return msg;
	}
	/**
	 * 错误或者正确信息设定
	 * @param msg 错误或者正确信息
	 */
	public void setMsg(String msg) {
	    this.msg = msg;
	}
	/**
	 * 页码取得
	 * @return 页码
	 */
	public Integer getPageNum() {
	    return pageNum;
	}
	/**
	 * 页码设定
	 * @param pageNum 页码
	 */
	public void setPageNum(Integer pageNum) {
	    this.pageNum = pageNum;
	}
	/**
	 * 每页显示条数取得
	 * @return 每页显示条数
	 */
	public Integer getPageSize() {
	    return pageSize;
	}
	/**
	 * 每页显示条数设定
	 * @param pageSize 每页显示条数
	 */
	public void setPageSize(Integer pageSize) {
	    this.pageSize = pageSize;
	}
}
