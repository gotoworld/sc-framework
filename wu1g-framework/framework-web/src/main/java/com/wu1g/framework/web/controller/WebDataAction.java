/*
 * http 数据接口 服务
 *
 * VERSION  DATE        BY              REASON
 * -------- ----------- --------------- ------------------------------------------
 * 1.00     2015.01.13  wuxiaogang      程序・发布
 * -------- ----------- --------------- ------------------------------------------
 * Copyright 2015 baseos System. - All Rights Reserved.
 *
 */
package com.wu1g.framework.web.controller;

import org.apache.log4j.Logger;
/**
 * <p>http 数据接口 服务 controller</p>
 * <ol>[提供机能]
 * <li>获取信息</li>
 * <li>修改信息</li>
 * <li>保存信息</li>
 * <li>合并信息</li>
 * <li>删除信息</li>
 * </ol>
 */
public class WebDataAction  extends BaseController {
	private static final long serialVersionUID = -6103432072290645133L;
	private static final transient Logger log = Logger.getLogger("http");

	/** 默认的构造函数 */
	public WebDataAction() throws Exception {
		log.info("WebXmlAction constructed");
	}
	/**
	 * <li>
	 * 	<li>获取信息</li>
	 * 
	 * @param info
	 * @return info
	 * @throws Exception 
	 */
	public String getInfo() throws Exception {
//		 getWriter().print(webServiceService.getInfo(request.getParameter("info"),getIpAddr()));
		 return null;
	}
	/**
	 * <li>
	 * 	<li>修改信息</li>
	 * 
	 * @param info
	 * @return info
	 */
	public String modifyInfo() throws Exception {
//		getWriter().print(webServiceService.modifyInfo(request.getParameter("info"),getIpAddr()));
		return null;
	}
	/**
	 * <li>
	 * 	<li>保存信息</li>
	 * 
	 * @param info
	 * @return info
	 */
	public String saveInfo() throws Exception {
//		getWriter().print(webServiceService.saveInfo(request.getParameter("info"),getIpAddr()));
		return null;
	}
	/**
	 * <li>
	 * 	<li>合并信息</li>
	 * 
	 * @param info
	 * @return info
	 */
	public String mergerInfo() throws Exception {
//		getWriter().print(webServiceService.mergerInfo(request.getParameter("info"),getIpAddr()));
		return null;
	}
	/**
	 * <li>
	 * 	<li>删除信息</li>
	 * 
	 * @param info
	 * @return info
	 */
	public String delInfo() throws Exception {
		return null;
	}
}
