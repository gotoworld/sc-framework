/*
 * action 基类 放置action通用资源或公共方法
 *
 * VERSION  DATE        BY              REASON
 * -------- ----------- --------------- ------------------------------------------
 * 1.00     2015.01.13  wuxiaogang      程序・发布
 * -------- ----------- --------------- ------------------------------------------
 * Copyright 2013 baseos System. - All Rights Reserved.
 *
 */
package com.wu1g.panorama.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import cn.com.baseos.common.IpUtils;
import cn.com.baseos.service.sys.ISysUserLogService;
import cn.com.baseos.service.sys.IVariableService;


/**
 * <p>
 * controller 基类 放置action通用资源或公共方法
 * </p>
 * <ol>
 * [提供机能]
 * <li>request</li>
 * <li>response</li>
 * <li>servletContext</li>
 * <li>taskExecutor 线程池</li>
 * <li>jedisHelper 缓存服务</li>
 * <li>getSession() 获取session</li>
 * <li>getBackPath()返回上一次访问链接</li>
 * <li>getWriter() 输出</li>
 * <li>getIpAddr() 获取用户ip</li>
 * </ol>
 */
public class BaseAction implements Serializable {
    private static final long serialVersionUID = -6103432072290645133L;
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

    /** 线程池 */
    protected TaskExecutor taskExecutor;

    // /**缓存服务 */
    /** 系统_管理员操作日志 业务处理 */
    @Qualifier("sysUserLogService")
    protected ISysUserLogService alog;

    /** 数据字典管理 service 业务处理 */
    @Autowired
    protected IVariableService variableService;

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
        this.request = request;
        this.response = response;
        this.session = session;
    }
    /**前缀绑定*/
    @InitBinder("bean")
    public void initBinderBean(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("bean.");    
    }
    /***
     * 返回上次访问链接
     *
     * @return
     */
    public String getBackPath() {
        return request.getHeader("referer");
    }

    /***
     * 输出
     *
     * @return
     * @throws IOException
     */
    public PrintWriter getWriter() throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        return response.getWriter();
    }

    /**
     * 获取用户IP
     *
     * @return
     */
    public String getIpAddr() {
        return IpUtils.getIpAddr(request);
    }

    /**
     * 权限验证框架取得
     *
     * @return 权限验证框架
     */
    public Subject getAuth() {
        return SecurityUtils.getSubject();
    }
    
    public String getBasePath(){
		String path = request.getContextPath();
		String basePath = null;
		// log.info("访问端口号："+ request.getServerPort());
		
		if (request.getServerPort() != 80) {
			basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
		} else {
			basePath = request.getScheme() + "://" + request.getServerName() + path;
		}
		return basePath;
    }
}
