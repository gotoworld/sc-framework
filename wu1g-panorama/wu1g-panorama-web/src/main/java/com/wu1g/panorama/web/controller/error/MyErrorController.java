/*
 *  类描述待补充.
 *
 * VERSION  DATE        BY              REASON
 * -------- ----------- --------------- ------------------------------------
 * 1.00     2016-3-26  wu1g119      程序.发布
 * -------- ----------- --------------- ------------------------------------
 * Copyright 2016 baseos  System. - All Rights Reserved.
 *
 */
package com.wu1g.panorama.web.controller.error;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.baseos.handler.WebExceptionHandler;

/**
 * <p>
 * 类功能说明待补充
 * </p>
 * <dl>
 * [功能概要]
 * <dt>功能1</dt>
 * </dl>
 */
@Controller
@RequestMapping("/error")
public class MyErrorController{
	private static final Logger	log	= Logger.getLogger( WebExceptionHandler.class );
	private static final String ERROR_PATH="/";
	@RequestMapping(value =ERROR_PATH )
	public String handleError(ServletRequest request, ServletResponse rsp) {
		log.info("其它错误~");
		return "error/error";
	}
	@RequestMapping(value ="/403" )
	public String error403(ServletRequest request, ServletResponse rsp) {
		log.info("403错误~");
		return "error/403";
	}
	@RequestMapping(value ="/404" )
	public String error404(ServletRequest request, ServletResponse rsp) {
		log.info("404错误~"+((HttpServletRequest)request).getHeader("referer"));
		return "error/404";
	}
	@RequestMapping(value ="/500" )
	public String error500(ServletRequest request, ServletResponse rsp) {
		log.info("500错误~");
		return "error/500";
	}
	@RequestMapping(value ="/locked" )
	public String errorlocked(ServletRequest request, ServletResponse rsp) {
		log.info("locked~");
		return "error/locked";
	}
	@RequestMapping(value ="/noauth" )
	public String errornoauth(ServletRequest request, ServletResponse rsp) {
		log.info("noauth~");
		return "error/noauth";
	}
}
