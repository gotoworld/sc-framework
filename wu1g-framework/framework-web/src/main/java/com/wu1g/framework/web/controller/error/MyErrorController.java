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
package com.wu1g.framework.web.controller.error;

import com.wu1g.framework.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

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
@Slf4j
public class MyErrorController{
	private static final String ERROR_PATH="/";
	@RequestMapping(value =ERROR_PATH )
	public Response handleError(ServletRequest request, ServletResponse rsp) {
		log.info("其它错误~");
//		return "error/error";
		return Response.error("未知错误!");
	}
	@RequestMapping(value ="/403" )
	public Response error403(ServletRequest request, ServletResponse rsp) {
		log.info("403错误~");
//		return "error/403";
		return Response.error(403,"403错误!");
	}
	@RequestMapping(value ="/404" )
	public Response error404(ServletRequest request, ServletResponse rsp) {
		log.info("404错误~"+((HttpServletRequest)request).getHeader("referer"));
//		return "error/404";
		return Response.error(404,"所访问的地址不存在!");
	}
	@RequestMapping(value ="/500" )
	public Response error500(ServletRequest request, ServletResponse rsp) {
		log.info("500错误~");
		return Response.error(500);
	}
	@RequestMapping(value ="/locked" )
	public Response errorlocked(ServletRequest request, ServletResponse rsp) {
		log.info("locked~");
//		return "error/locked";
		return Response.error("locked!");
	}
	@RequestMapping(value ="/noauth" )
	public Response errornoauth(ServletRequest request, ServletResponse rsp) {
		log.info("noauth~");
		return Response.error(503,"权限不足!");
	}
}
