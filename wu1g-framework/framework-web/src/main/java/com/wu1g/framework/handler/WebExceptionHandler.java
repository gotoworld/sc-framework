package com.wu1g.framework.handler;

import java.io.IOException;
import java.net.ConnectException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

import cn.com.baseos.common.DateUtil;

/**
 * @ClassName: WebExceptionHandler
 * @Description: Web层异常处理器, -- 这里可以根据不同的异常，写多个方法去处理， 可以处理跳转页面请求，跳到异常指定的错误页，
 *               也可以处理Ajax请求，根据不通过异常，在页面输出不同的提示信息 operateExp : 处理普通请求
 *               operateExpAjax ： 处理Ajax请求
 */
@ControllerAdvice
public class WebExceptionHandler {
	Logger	log	= Logger.getLogger( WebExceptionHandler.class );
	/*
	 * 如果抛出UnauthorizedException，将被该异常处理器截获来显示没有权限信息
	 */
	@ExceptionHandler({ org.apache.shiro.authz.AuthorizationException.class })
	public ModelAndView unauthenticatedException(NativeWebRequest request, UnauthorizedException e) {
		ModelAndView mv = new ModelAndView();
		mv.addObject( "exception", e );
		mv.setViewName( "error/noauth" );
		log.error( "没有权限！" + request.getParameter( "url_427d668497464195893069825e272146" ) );
		return mv;
	}

	/**
	 * @Title: operateExp
	 * @Description: 全局异常控制，记录日志
	 *               任何一个方法发生异常，一定会被这个方法拦截到。然后，输出日志。封装Map并返回给页面显示错误信息：
	 *               特别注意：返回给页面错误信息只在开发时才使用，上线后，要把错误页面去掉，只打印log日志即可，防止信息外漏
	 * @param ex
	 * @param request
	 * @return: String
	 * @throws:
	 */
	@ExceptionHandler(RuntimeException.class)
	public String operateExp(RuntimeException ex, HttpServletRequest request) {
		log.error( "访问异常！" + request.getParameter( "url_427d668497464195893069825e272146" ) );
		log.error( ex.getMessage(), ex );
		log.info( "************* ------ 异常信息已记录（" + DateUtil.getDateTimeStr( new Date() ) + "） ------- ***********" );
		request.setAttribute( "errorTips", ex.getMessage() );
		request.setAttribute( "ex", ex );
		return "error/500";
	}

	@ExceptionHandler(ConnectException.class)
	public void operateExpNetException(ConnectException ex, HttpServletResponse response) throws IOException {
		log.error( ex.getMessage(), ex );
		log.info( "************* ------ 异常信息已记录（" + DateUtil.getDateTimeStr( new Date() ) + "） ------- ***********" );
		// 将Ajax异常信息回写到前台，用于页面的提示
		response.getWriter().write( "sorry,网络连接出错！！！" );
	}
}
