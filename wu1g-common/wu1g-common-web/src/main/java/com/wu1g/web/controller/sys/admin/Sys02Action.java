/*	
 * 系统_管理员操作日志  ACTION类	
 *		
 * VERSION      DATE          BY              REASON		
 * -------- ----------- --------------- ------------------------------------------	
 * 1.00     2015.10.01      easycode         程序.发布		
 * -------- ----------- --------------- ------------------------------------------	
 * Copyright 2015 isd System. - All Rights Reserved.		
 *	
 */
package com.wu1g.web.controller.sys.admin;

import com.github.pagehelper.PageInfo;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.framework.web.controller.BaseController;
import com.wu1g.sys.api.ISysUserLogService;
import com.wu1g.sys.vo.SysUserLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>系统_管理员操作日志  ACTION类。</p>	
 * <ol>[功能概要] 
 * <li>初始化。 
 * <li>信息列表(未删除)。 
 *</ol> 
 * @author easycode
 */
@Controller
@RequestMapping(value = "/h")
@Slf4j
public class Sys02Action extends BaseController {

	private static final long serialVersionUID = -802538749184564668L;
	/**系统_管理员操作日志 业务处理*/
	@Autowired
	private ISysUserLogService sysUserLogService;
	
	//系统_管理员操作日志 管理
	private static final String acPrefix="/sys02.";
	private static final String init = "admin/sys/sys02";
	private static final String edit = "admin/sys/sys02_01";
	private static final String infoList = "admin/sys/sys02_list";
	private static final String success = "redirect:/h"+acPrefix+"init";
	/**
	 * <p> 初始化处理。 </p>
	 * <ol>
	 * [功能概要] 
	 * <li>初始化处理。
	 * </ol>
	 * @return 转发字符串
	 */
	@RequiresPermissions("sysLog:menu")
	@RequestMapping(value=acPrefix+"init")
	public String init() {
		log.info("Sys02Action init.........");
		return init;
	}
	/**
	 * <p> 信息列表 (未删除)。 </p>
	 * <ol>
	 * [功能概要] 
	 * <li>信息列表。
	 * </ol>
	 * @return 转发字符串
	 */
	@RequiresPermissions("sysLog:menu")
	@RequestMapping(value=acPrefix+"infoList")
	public String infoList(SysUserLog bean) {
		log.info("Sys02Action infoList.........");
		if(bean==null){
			bean = new SysUserLog();
		}
		//信息列表
		PageInfo<?> page=new PageInfo<>(sysUserLogService.findDataIsPage(bean));
		request.setAttribute( "beans", page.getList() );
		//分页对象-JSP标签使用-
		request.setAttribute(CommonConstant.PAGEROW_OBJECT_KEY,page);
		return infoList;
	}
}