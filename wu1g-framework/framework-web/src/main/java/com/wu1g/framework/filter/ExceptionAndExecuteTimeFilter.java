/*
 *  类描述待补充.
 *
 * VERSION  DATE        BY              REASON
 * -------- ----------- --------------- ------------------------------------
 * 1.00     2016-3-25  wu1g119      程序.发布
 * -------- ----------- --------------- ------------------------------------
 * Copyright 2016 baseos  System. - All Rights Reserved.
 *
 */
package com.wu1g.framework.filter;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;

import cn.com.baseos.common.CommonConstant;

/**
 * <p>
 * 类功能说明待补充
 * </p>
 * <dl>
 * [功能概要]
 * <dt>功能1</dt>
 * </dl>
 */
@WebFilter(filterName = "exceptionAndExecuteTimeFilter", urlPatterns = {"/h/*","/admin/*"})
public class ExceptionAndExecuteTimeFilter implements Filter {
	private static final Log	log	= LogFactory.getLog( ExceptionAndExecuteTimeFilter.class );
	private static final String	EQUAL_SIGN			= "=";
	private static final String	PLUS_SIGN			= "+";
	private static final String	AND					= "&";
	@Override
	public void destroy() {
		log.info( "过滤器销毁" );
	}
	@Override
	public void doFilter(ServletRequest req, ServletResponse rsp, FilterChain chain) throws IOException, ServletException {
//		log.info( "执行过滤操作" );
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse)rsp;
		long start = System.currentTimeMillis();
		String path = request.getContextPath();
		String basePath = null;
		// log.info("访问端口号："+ request.getServerPort());
		
		if (request.getServerPort() != 80) {
			basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
		} else {
			basePath = request.getScheme() + "://" + request.getServerName() + path;
		}
		request.setAttribute( "path", path );
		request.setAttribute( "basePath", basePath );
		
		String remoteHost = request.getRemoteAddr(); // 获取客户端的主机名
		String requestURL = request.getRequestURL().toString(); // 获取客户端请求的URL
		Map<String, String[]> paramsMap = (Map<String, String[]>) request.getParameterMap(); // 获取所有的请求参数
		/*
		 * 获取所有参数的名值对信息的字符串表示，存储在变量paramsStr中
		 */
		StringBuilder paramsStrSb = new StringBuilder();
		if (paramsMap != null && paramsMap.size() > 0) {
			Set<Entry<String, String[]>> paramsSet = paramsMap.entrySet();
			for (Entry<String, String[]> param : paramsSet) {
				StringBuilder paramStrSb = new StringBuilder();
				String paramName = param.getKey(); // 参数的名字
				String[] paramValues = param.getValue(); // 参数的值
				if (paramValues.length == 1) { // 参数只有一个值，绝大多数情况
					paramStrSb.append( paramName ).append( EQUAL_SIGN ).append( paramValues[ 0 ] );
				} else {
					paramStrSb.append( paramName ).append( EQUAL_SIGN );
					for (String paramValue : paramValues) {
						paramStrSb.append( paramValue );
						paramStrSb.append( PLUS_SIGN );
					}
					paramStrSb.deleteCharAt( paramStrSb.length() - 1 );
				}
				paramsStrSb.append( paramStrSb ).append( AND );
			}
			paramsStrSb.deleteCharAt( paramsStrSb.length() - 1 );
		}
		String paramsStr = paramsStrSb.toString();
		// 获得Action执行的开始时间
//		long start = (Long)request.getAttribute("427d668497464195893069825e272146_startTime");
		String url_427d668497464195893069825e272146="" + remoteHost + "访问URL:" + requestURL;
		
		request.setAttribute( "url_427d668497464195893069825e272146", url_427d668497464195893069825e272146 );
		
		log.info( url_427d668497464195893069825e272146 );
		
		try {
			chain.doFilter( req, rsp );
		} catch (Throwable e) {
			log.error( "出现异常!!",e);
		}
		// 获得Action执行的结束时间
		long end = System.currentTimeMillis();
		// 如果该Action的执行时间超过了500毫秒，则日志记录下来
		final int MAX_TIME = 500;
		long executeTimeMillis = end - start;
		if (executeTimeMillis >= MAX_TIME) {
			log.error( "执行时间过长！ "+url_427d668497464195893069825e272146+"，参数：" + paramsStr+"，共用时" + executeTimeMillis + "毫秒" );
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		log.info( "...........过滤器初始化................" );
	}

}