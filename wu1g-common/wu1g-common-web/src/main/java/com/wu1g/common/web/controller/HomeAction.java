/*
 * 后台页面首页
 *
 * VERSION  DATE        BY              REASON
 * -------- ----------- --------------- ------------------------------------------
 * 1.00     2015.01.16  wuxiaogang      程序・发布
 * -------- ----------- --------------- ------------------------------------------
 * Copyright 2015 baseos System. - All Rights Reserved.
 *
 */
package com.wu1g.common.web.controller;

import com.wu1g.framework.web.controller.BaseController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>后台页面首页 action</p>
 * <ol>[提供机能]
 * <li>--<li>
 * </ol>
 */
@Controller
@RequestMapping(value = "/h")
public class HomeAction  extends BaseController {

	private static final long serialVersionUID = -6103432072290645133L;
	private static final transient Logger log = Logger.getLogger("admin");

	/** 默认的构造函数 */
	public HomeAction() {
		log.info("HomeAction constructed");
	}
	/**
	 * <p>初始化
	 */
	@RequestMapping(value = "/index")
	public String init() throws Exception {
		log.info("HomeAction init");
		return "admin/index";
	}
	/**
	 * <p>首页
	 */
	@RequestMapping(value = "/home")
	public String home() throws Exception {
		log.info("HomeAction home");
		return "admin/home";
	}
}
