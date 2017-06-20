/*	
 * 系统_管理员操作日志  ACTION类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 System. - All Rights Reserved.
 *	
 */
package com.vr.web.controller.admin.sys;

import com.github.pagehelper.PageInfo;
import com.vr.api.sys.ISysUserLogService;
import com.vr.framework.util.CommonConstant;
import com.vr.vo.sys.SysUserLog;
import com.vr.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>系统_管理员操作日志  ACTION类。
 */
@Controller
@RequestMapping(value = "/h")
@Slf4j
public class SysUserLogController extends BaseController {
	@Autowired
	private ISysUserLogService sysUserLogService;
	
	//系统_管理员操作日志 管理
	private static final String acPrefix="/sys/alog/";
	private static final String init = "admin/sys/sys_alog";
	private static final String list = "admin/sys/sys_alog_list";
	/**
	 * <p> 初始化处理。
	 */
	@RequiresPermissions("sysLog:menu")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"init")
	public String init() {
		log.info("SysUserLogController init.........");
		return init;
	}
	/**
	 * <p> 信息列表 (未删除)。
	 */
	@RequiresPermissions("sysLog:menu")
	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value=acPrefix+"list")
	public String list(SysUserLog bean) {
		log.info("SysUserLogController list.........");
		if(bean==null){
			bean = new SysUserLog();
		}
		//信息列表
		PageInfo<?> page=new PageInfo<>(sysUserLogService.findDataIsPage(bean));
		request.setAttribute( "beans", page.getList() );
		//分页对象
		request.setAttribute(CommonConstant.PAGEROW_OBJECT_KEY,page);
		return list;
	}
}